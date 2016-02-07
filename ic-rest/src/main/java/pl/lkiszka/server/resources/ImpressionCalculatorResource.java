package pl.lkiszka.server.resources;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import pl.lkiszka.core.algorithm.ImpressionCalculatorAlgorithm;
import pl.lkiszka.core.algorithm.exception.ImpressionCalculatorException;
import pl.lkiszka.core.model.InputImpression;
import pl.lkiszka.core.model.OutputImpression;
import pl.lkiszka.server.constants.Constants;
import pl.lkiszka.server.request.util.CacheUtil;
import pl.lkiszka.server.request.util.CalculationResult;
import pl.lkiszka.server.request.util.CalculationResultFactory;
import pl.lkiszka.server.request.util.RequestUtil;

@Path(value = "/impressionCalculator")
public class ImpressionCalculatorResource {

	private static Logger LOGGER = Logger.getLogger(ImpressionCalculatorResource.class);

	@Context
	private UriInfo uriInfo;

	private ImpressionCalculatorAlgorithm impressionCalculatorAlgorithm;

	@Inject
	public ImpressionCalculatorResource(
			@Named(Constants.INJECT_ALGORITHM_NAME) ImpressionCalculatorAlgorithm impressionCalculatorAlgorithm) {
		LOGGER.info("Inject impressionCalculatorAlgorithm: " + (impressionCalculatorAlgorithm != null ? true : false));
		this.impressionCalculatorAlgorithm = impressionCalculatorAlgorithm;
	}

	@POST
	@Path("/calculateAsync")
	@Consumes(MediaType.APPLICATION_JSON)
	public void calculate(@Suspended final AsyncResponse response, @Valid InputImpression inputImpression) {

		LOGGER.info("START - calculate");

		String requestId = null;

		try {

			requestId = RequestUtil.genereateUniqueNumber().toString();

			new Thread(calculateTask(requestId, inputImpression)).start();

			CacheUtil.results.put(requestId, CalculationResultFactory.createAccepted());

			response.resume(Response.status(Response.Status.ACCEPTED)
					.contentLocation(RequestUtil.createUri(uriInfo, requestId)).build());
			LOGGER.info("END - calculate");

		} catch (Exception e) {

			CacheUtil.results.invalidate(requestId);
			response.resume(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.contentLocation(RequestUtil.createUri(uriInfo, requestId)).build());

		}

	}

	@GET
	@Path("/calculateAsync/{" + Constants.GET_CALCULATE_RESULTS_PATH_PARAM + "}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCalculateResults(@PathParam(Constants.GET_CALCULATE_RESULTS_PATH_PARAM) String requestId) {

		LOGGER.info("START getCalculateResults for key: " + requestId);

		CalculationResult calculationResult = CacheUtil.results.getIfPresent(requestId);

		LOGGER.info("Return output: " + calculationResult);

		if (calculationResult != null) {

			if (Response.Status.OK.getStatusCode() == calculationResult.getHttpStatus()) {
				LOGGER.info("return status OK");
				CacheUtil.results.invalidate(requestId);
				return Response.ok().entity(calculationResult.getOutputImpression()).build();
			} else

			if (Response.Status.ACCEPTED.getStatusCode() == calculationResult.getHttpStatus()) {
				LOGGER.info("return status ACCEPTED");
				return Response.accepted().contentLocation(uriInfo.getAbsolutePath()).build();
			} else

			if (Response.Status.REQUEST_TIMEOUT.getStatusCode() == calculationResult.getHttpStatus()) {
				LOGGER.info("return status REQUEST_TIMEOUT");
				CacheUtil.results.invalidate(requestId);
				return Response.status(408).build();
			} else {

				CacheUtil.results.invalidate(requestId);
				return Response.serverError().entity(calculationResult).build();
			}

		}

		LOGGER.info("return status NO_CONTENT");
		return Response.noContent().build();

	}

	private Runnable calculateTask(String requestId, InputImpression inputImpression) {

		return new Runnable() {

			@Override
			public void run() {

				LOGGER.info("START calculate task for key:" + requestId);

				ExecutorService executor = Executors.newFixedThreadPool(5);

				@SuppressWarnings("unchecked")
				Future<Void> future = (Future<Void>) executor.submit(() -> {

					try {

						OutputImpression outputImpression = impressionCalculatorAlgorithm.run(inputImpression);
						CacheUtil.results.put(requestId, CalculationResultFactory.createOk(outputImpression));

					} catch (ImpressionCalculatorException ice) {
						CacheUtil.results.put(requestId, CalculationResultFactory.createInternalServerError());
						LOGGER.error("Exception in calculate for key:" + requestId + ", Error message:", ice);
					}
				});

				try {
					future.get(Constants.CALCULATE_THREAD_TIMEOUT, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					CacheUtil.results.put(requestId, CalculationResultFactory.createInternalServerError());
					LOGGER.error("Exception in calculate for key:" + requestId + ", Error message: ", e);
				} catch (ExecutionException e) {
					CacheUtil.results.put(requestId, CalculationResultFactory.createInternalServerError());
					LOGGER.error("Exception in calculate for key:" + requestId + ", Error message:", e);
				} catch (TimeoutException e) {
					CacheUtil.results.put(requestId, CalculationResultFactory.createTimeout());
					LOGGER.error("Exception in calculate for key:" + requestId + ", Error message:", e);
				} finally {
					future.cancel(true);
					executor.shutdownNow();

				}

				LOGGER.info("END calculate task for key:" + requestId);
			}
		};

	};

}
