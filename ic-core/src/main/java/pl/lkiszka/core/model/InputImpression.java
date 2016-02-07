package pl.lkiszka.core.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InputImpression {

	@NotNull
	private Integer mounthImpressionQuantity;

	@NotNull
	@Size(min = 1)
	@Valid
	private List<InputImpressionData> inputImpressionDatas;

	public Integer getMounthImpressionQuantity() {
		return mounthImpressionQuantity;
	}

	public void setMounthImpressionQuantity(Integer mounthImpressionQuantity) {
		this.mounthImpressionQuantity = mounthImpressionQuantity;
	}

	public List<InputImpressionData> getInputImpressionDatas() {
		return inputImpressionDatas;
	}

	public void setInputImpressionDatas(List<InputImpressionData> inputImpressionDatas) {
		this.inputImpressionDatas = inputImpressionDatas;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		InputImpression that = (InputImpression) o;

		if (mounthImpressionQuantity != null ? !mounthImpressionQuantity.equals(that.mounthImpressionQuantity)
				: that.mounthImpressionQuantity != null)
			return false;
		return !(inputImpressionDatas != null ? !inputImpressionDatas.equals(that.inputImpressionDatas)
				: that.inputImpressionDatas != null);

	}

	@Override
	public int hashCode() {
		int result = mounthImpressionQuantity != null ? mounthImpressionQuantity.hashCode() : 0;
		result = 31 * result + (inputImpressionDatas != null ? inputImpressionDatas.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "InputImpression{" + "mounthImpressionQuantity=" + mounthImpressionQuantity + ", inputImpressionDatas="
				+ inputImpressionDatas + '}';
	}
}
