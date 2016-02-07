package pl.lkiszka.server.request.util;

import javax.ws.rs.core.Response;

import pl.lkiszka.core.model.OutputImpression;

public class CalculationResultFactory {

	public static CalculationResult createInternalServerError() {

		CalculationResult calculationResult = new CalculationResult();
		calculationResult.setHttpStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		return calculationResult;

	}

	public static CalculationResult createAccepted() {

		CalculationResult calculationResult = new CalculationResult();
		calculationResult.setHttpStatus(Response.Status.ACCEPTED.getStatusCode());
		return calculationResult;

	}

	public static CalculationResult createOk(OutputImpression outputImpression) {

		CalculationResult calculationResult = new CalculationResult();
		calculationResult.setOutputImpression(outputImpression);
		calculationResult.setHttpStatus(Response.Status.OK.getStatusCode());
		return calculationResult;

	}

	public static CalculationResult createTimeout() {

		CalculationResult calculationResult = new CalculationResult();
		calculationResult.setHttpStatus(Response.Status.REQUEST_TIMEOUT.getStatusCode());
		return calculationResult;

	}

}
