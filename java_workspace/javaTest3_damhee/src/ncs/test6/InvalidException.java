package ncs.test6;

public class InvalidException extends RuntimeException {
	public InvalidException() {} // 기본생정자
	public InvalidException(String message) {
		super(message);
	} // 기본생정자
}
