package pl.lkiszka.server.request.util;

import pl.lkiszka.core.model.OutputImpression;

public class CalculationResult {

	private OutputImpression outputImpression;
	private int httpStatus;

	public OutputImpression getOutputImpression() {
		return outputImpression;
	}

	public void setOutputImpression(OutputImpression outputImpression) {
		this.outputImpression = outputImpression;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + httpStatus;
		result = prime * result + ((outputImpression == null) ? 0 : outputImpression.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculationResult other = (CalculationResult) obj;
		if (httpStatus != other.httpStatus)
			return false;
		if (outputImpression == null) {
			if (other.outputImpression != null)
				return false;
		} else if (!outputImpression.equals(other.outputImpression))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalculationResult [outputImpression=" + outputImpression + ", httpStatus=" + httpStatus + "]";
	}

}
