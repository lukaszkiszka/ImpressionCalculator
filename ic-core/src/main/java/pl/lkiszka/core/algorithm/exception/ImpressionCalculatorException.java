package pl.lkiszka.core.algorithm.exception;

public class ImpressionCalculatorException extends Exception {

	private static final long serialVersionUID = 7539988355891856347L;

	public ImpressionCalculatorException() {
		super();
	}

	public ImpressionCalculatorException(String message) {
		super(message);
	}

	public ImpressionCalculatorException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
