package pl.lkiszka.core.model;

public class OutputImpressionData {

	private String clientName;
	private Integer campainToSellQuantity;
	private Integer impressionSellerSummary;
	private Integer totalProfit;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getCampainToSellQuantity() {
		return campainToSellQuantity;
	}

	public void setCampainToSellQuantity(Integer campainToSellQuantity) {
		this.campainToSellQuantity = campainToSellQuantity;
	}

	public Integer getImpressionSellerSummary() {
		return impressionSellerSummary;
	}

	public void setImpressionSellerSummary(Integer impressionSellerSummary) {
		this.impressionSellerSummary = impressionSellerSummary;
	}

	public Integer getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Integer totalProfit) {
		this.totalProfit = totalProfit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OutputImpressionData that = (OutputImpressionData) o;

		if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null)
			return false;
		if (campainToSellQuantity != null ? !campainToSellQuantity.equals(that.campainToSellQuantity)
				: that.campainToSellQuantity != null)
			return false;
		if (impressionSellerSummary != null ? !impressionSellerSummary.equals(that.impressionSellerSummary)
				: that.impressionSellerSummary != null)
			return false;
		return !(totalProfit != null ? !totalProfit.equals(that.totalProfit) : that.totalProfit != null);

	}

	@Override
	public int hashCode() {
		int result = clientName != null ? clientName.hashCode() : 0;
		result = 31 * result + (campainToSellQuantity != null ? campainToSellQuantity.hashCode() : 0);
		result = 31 * result + (impressionSellerSummary != null ? impressionSellerSummary.hashCode() : 0);
		result = 31 * result + (totalProfit != null ? totalProfit.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "OutputImpressionData{" + "clientName='" + clientName + '\'' + ", campainToSellQuantity="
				+ campainToSellQuantity + ", impressionSellerSummary=" + impressionSellerSummary + ", totalProfit="
				+ totalProfit + '}';
	}
}
