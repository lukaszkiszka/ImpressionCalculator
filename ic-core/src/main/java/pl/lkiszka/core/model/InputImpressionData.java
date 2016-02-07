package pl.lkiszka.core.model;

import javax.validation.constraints.NotNull;

public class InputImpressionData {

	@NotNull
	private String clientName;

	@NotNull
	private Integer impressionInCampaignQuantity;

	@NotNull
	private Integer campaignPrice;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getCampaignPrice() {
		return campaignPrice;
	}

	public void setCampaignPrice(Integer campaignPrice) {
		this.campaignPrice = campaignPrice;
	}

	public Integer getImpressionInCampaignQuantity() {
		return impressionInCampaignQuantity;
	}

	public void setImpressionInCampaignQuantity(Integer impressionInCampaignQuantity) {
		this.impressionInCampaignQuantity = impressionInCampaignQuantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		InputImpressionData that = (InputImpressionData) o;

		if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null)
			return false;
		if (impressionInCampaignQuantity != null
				? !impressionInCampaignQuantity.equals(that.impressionInCampaignQuantity)
				: that.impressionInCampaignQuantity != null)
			return false;
		return !(campaignPrice != null ? !campaignPrice.equals(that.campaignPrice) : that.campaignPrice != null);

	}

	@Override
	public int hashCode() {
		int result = clientName != null ? clientName.hashCode() : 0;
		result = 31 * result + (impressionInCampaignQuantity != null ? impressionInCampaignQuantity.hashCode() : 0);
		result = 31 * result + (campaignPrice != null ? campaignPrice.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "InputImpressionData{" + "clientName='" + clientName + '\'' + ", impressionInCampaignQuantity="
				+ impressionInCampaignQuantity + ", campaignPrice=" + campaignPrice + '}';
	}
}
