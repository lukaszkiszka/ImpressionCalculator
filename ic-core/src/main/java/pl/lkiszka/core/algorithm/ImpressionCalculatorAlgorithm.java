package pl.lkiszka.core.algorithm;

import pl.lkiszka.core.algorithm.exception.ImpressionCalculatorException;
import pl.lkiszka.core.model.InputImpression;
import pl.lkiszka.core.model.OutputImpression;

public interface ImpressionCalculatorAlgorithm {

	public OutputImpression run(InputImpression inputImpression) throws ImpressionCalculatorException;
}
