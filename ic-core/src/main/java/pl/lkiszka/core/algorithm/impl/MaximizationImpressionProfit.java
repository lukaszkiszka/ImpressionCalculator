package pl.lkiszka.core.algorithm.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lkiszka.core.algorithm.ImpressionCalculatorAlgorithm;
import pl.lkiszka.core.algorithm.exception.ImpressionCalculatorException;
import pl.lkiszka.core.model.InputImpression;
import pl.lkiszka.core.model.InputImpressionData;
import pl.lkiszka.core.model.OutputImpression;
import pl.lkiszka.core.model.OutputImpressionData;

/**
 * 
 * Algorithm solve knapsack problem with returning. Reduce input data with find
 * common divisor for size of knapsack and item.
 * 
 */
public class MaximizationImpressionProfit implements ImpressionCalculatorAlgorithm {

	private static Logger LOGGER = LogManager.getLogger(MaximizationImpressionProfit.class.getName());

	private int impressionDataNumber;
	private int maxImpressionInMonth;
	private InputImpression inputImpression;

	public OutputImpression run(InputImpression input) throws ImpressionCalculatorException {

		try {

			LOGGER.info("START");

			long startTime = System.currentTimeMillis();

			inputImpression = input;

			impressionDataNumber = inputImpression.getInputImpressionDatas().size();
			maxImpressionInMonth = inputImpression.getMounthImpressionQuantity();

			Collections.sort(inputImpression.getInputImpressionDatas(),
					(InputImpressionData i1, InputImpressionData i2) -> (i2.getImpressionInCampaignQuantity()
							.compareTo(i1.getImpressionInCampaignQuantity())));

			int minCommonDivision = calculateReduceCommonDivisior();
			reduceInputDataSet(minCommonDivision);

			int[] results = new int[maxImpressionInMonth + 1];
			int[] lastItem = new int[maxImpressionInMonth + 1];

			Arrays.fill(lastItem, -1);

			for (int i = 0; i < inputImpression.getInputImpressionDatas().size(); i++) {

				InputImpressionData inputImpressionData = inputImpression.getInputImpressionDatas().get(i);

				for (int j = inputImpressionData.getImpressionInCampaignQuantity(); j <= maxImpressionInMonth; j++) {

					int partResult = results[j - inputImpressionData.getImpressionInCampaignQuantity()]
							+ inputImpressionData.getCampaignPrice();

					if (results[j] < partResult) {
						results[j] = partResult;
						lastItem[j] = i;
					}
				}
			}

			int[] resultCounters = new int[impressionDataNumber];

			int c = maxImpressionInMonth;
			while (c > 0) {
				int itemIndex = lastItem[c];

				while (itemIndex < 0 && c > 0) {
					c--;
					itemIndex = lastItem[c];
				}

				if (itemIndex >= 0 && c > 0) {
					resultCounters[itemIndex]++;
					c = c - inputImpression.getInputImpressionDatas().get(itemIndex).getImpressionInCampaignQuantity();
				}
			}

			LOGGER.info("END, Time: " + (System.currentTimeMillis() - startTime) / 1000);
			return createOutputData(resultCounters, minCommonDivision);

		} catch (Exception e) {

			LOGGER.error("Exception in MaximizationImpressionProfit: ", e);
			throw new ImpressionCalculatorException(e.getMessage(), e.getCause());

		}

	}

	/**
	 * 
	 * Func reduce size of array through find greatest common divisor algorithm
	 * in ImpressionInCampaignQuantity and maxImpressionInMonth
	 * 
	 * @return minCommonDivision - min value from array of common divisior
	 * 
	 */
	private int calculateReduceCommonDivisior() {

		LOGGER.info("reduce data size");

		Integer[] commonDivisiorArray = new Integer[impressionDataNumber];

		for (int i = 0; i < impressionDataNumber; i++) {

			if (inputImpression.getInputImpressionDatas().get(i).getImpressionInCampaignQuantity() == 0) {

				commonDivisiorArray[i] = maxImpressionInMonth;

			} else {

				commonDivisiorArray[i] = BigInteger
						.valueOf(inputImpression.getInputImpressionDatas().get(i).getImpressionInCampaignQuantity())
						.gcd(BigInteger.valueOf(maxImpressionInMonth)).intValue();

			}

		}

		int minCommonDivision = Collections.min(Arrays.asList(commonDivisiorArray));

		LOGGER.info("minCommonDivision is " + minCommonDivision);

		return minCommonDivision;

	}

	/**
	 *
	 * @param minCommonDivision
	 *            - result of func calculateReduceCommonDivisior
	 * 
	 *
	 */
	private void reduceInputDataSet(int minCommonDivision) {

		maxImpressionInMonth = maxImpressionInMonth / minCommonDivision;

		for (int i = 0; i < impressionDataNumber; i++) {

			InputImpressionData inputImpressionData = inputImpression.getInputImpressionDatas().get(i);
			inputImpressionData.setImpressionInCampaignQuantity(
					inputImpressionData.getImpressionInCampaignQuantity() / minCommonDivision);
			inputImpression.getInputImpressionDatas().set(i, inputImpressionData);

		}

	}

	/**
	 * 
	 * @param resultCounters
	 *            - quantity of item that will go to knapsack
	 * @param minCommonDivision
	 *            - result of func calculateReduceCommonDivisior
	 * @return output data set with result of knapsack problem
	 */
	private OutputImpression createOutputData(int[] resultCounters, int minCommonDivision) {

		OutputImpression outputImpression = new OutputImpression();
		List<OutputImpressionData> outputImpressionDatas = new ArrayList<OutputImpressionData>();

		Integer impressionSummary = 0;
		Integer monthlyTotalProfit = 0;

		for (int i = 0; i < impressionDataNumber; i++) {

			InputImpressionData iid = inputImpression.getInputImpressionDatas().get(i);

			OutputImpressionData outputImpressionData = new OutputImpressionData();
			outputImpressionData.setClientName(iid.getClientName());
			outputImpressionData.setCampainToSellQuantity(resultCounters[i]);
			outputImpressionData.setImpressionSellerSummary(
					resultCounters[i] * iid.getImpressionInCampaignQuantity() * minCommonDivision);
			outputImpressionData.setTotalProfit(resultCounters[i] * iid.getCampaignPrice());

			impressionSummary += outputImpressionData.getImpressionSellerSummary();
			monthlyTotalProfit += outputImpressionData.getTotalProfit();

			outputImpressionDatas.add(outputImpressionData);

		}

		outputImpression.setImpressionSummary(impressionSummary);
		outputImpression.setMonthlyTotalProfit(monthlyTotalProfit);

		outputImpression.setOutputImpressionDatas(outputImpressionDatas);

		return outputImpression;

	}
}
