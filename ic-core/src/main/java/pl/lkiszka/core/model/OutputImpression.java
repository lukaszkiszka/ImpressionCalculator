package pl.lkiszka.core.model;

import java.util.List;

public class OutputImpression {

	private List<OutputImpressionData> outputImpressionDatas;
	private Integer ImpressionSummary;
	private Integer monthlyTotalProfit;

	public List<OutputImpressionData> getOutputImpressionDatas() {
		return outputImpressionDatas;
	}

	public void setOutputImpressionDatas(List<OutputImpressionData> outputImpressionDatas) {
		this.outputImpressionDatas = outputImpressionDatas;
	}

	public Integer getMonthlyTotalProfit() {
		return monthlyTotalProfit;
	}

	public void setMonthlyTotalProfit(Integer monthlyTotalProfit) {
		this.monthlyTotalProfit = monthlyTotalProfit;
	}

	public Integer getImpressionSummary() {
		return ImpressionSummary;
	}

	public void setImpressionSummary(Integer impressionSummary) {
		ImpressionSummary = impressionSummary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OutputImpression that = (OutputImpression) o;

		if (outputImpressionDatas != null ? !outputImpressionDatas.equals(that.outputImpressionDatas)
				: that.outputImpressionDatas != null)
			return false;
		if (ImpressionSummary != null ? !ImpressionSummary.equals(that.ImpressionSummary)
				: that.ImpressionSummary != null)
			return false;
		return !(monthlyTotalProfit != null ? !monthlyTotalProfit.equals(that.monthlyTotalProfit)
				: that.monthlyTotalProfit != null);

	}

	@Override
	public int hashCode() {
		int result = outputImpressionDatas != null ? outputImpressionDatas.hashCode() : 0;
		result = 31 * result + (ImpressionSummary != null ? ImpressionSummary.hashCode() : 0);
		result = 31 * result + (monthlyTotalProfit != null ? monthlyTotalProfit.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "OutputImpression{" + "outputImpressionDatas=" + outputImpressionDatas + ", ImpressionSummary="
				+ ImpressionSummary + ", monthlyTotalProfit=" + monthlyTotalProfit + '}';
	}
}
