package pl.lkiszka.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import pl.lkiszka.core.algorithm.ImpressionCalculatorAlgorithm;
import pl.lkiszka.core.algorithm.impl.MaximizationImpressionProfit;
import pl.lkiszka.server.constants.Constants;

/**
 * Created by lukas on 11.01.2016.
 */
public class AlgorithmModule extends AbstractModule {

	@Override
	protected void configure() {

	}

	@Provides
	@Named(Constants.INJECT_ALGORITHM_NAME)
	public ImpressionCalculatorAlgorithm getImpressionCalculatorAlgorithm() {
		return new MaximizationImpressionProfit();
	}

}
