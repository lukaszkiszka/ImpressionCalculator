package pl.lkiszka.server;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pl.lkiszka.server.constants.Constants;
import pl.lkiszka.server.health.CacheHealthCheck;

public class ImpressionCalculatorService extends Application<ImpressionCalculatorConfiguration> {

	public static void main(final String[] args) throws Exception {
		new ImpressionCalculatorService().run(args);
	}

	@Override
	public String getName() {
		return Constants.APP_NAME;
	}

	@Override
	public void initialize(final Bootstrap<ImpressionCalculatorConfiguration> bootstrap) {

		GuiceBundle<ImpressionCalculatorConfiguration> guiceBundle = GuiceBundle
				.<ImpressionCalculatorConfiguration> newBuilder().addModule(new AlgorithmModule())
				.enableAutoConfig(getClass().getPackage().getName())
				.setConfigClass(ImpressionCalculatorConfiguration.class).build();

		bootstrap.addBundle(guiceBundle);

	}

	@Override
	public void run(final ImpressionCalculatorConfiguration configuration, final Environment environment) {

		environment.healthChecks().register(Constants.CACHE_HEALTH_CHECK_NAME, new CacheHealthCheck());

	}

}
