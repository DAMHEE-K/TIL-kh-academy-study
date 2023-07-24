package sh.java.exception;

/**
 * 커스텀 예외 클래스 만들기 
 * - 비즈니스 로직의 흐름을 잘 설명할 수 있는 클래스명을 사용한다.
 * 
 * - checked exception으로 만들려면, extends Exception
 * 	 => 예외처리 흐름 작성까지 강제화하려는 경우
 * - unchecked exception으로 만들려면, extends RuntimeException
 * 
 * 멤버변수, 멤버메소드 상속O
 * 생성자, 초기화 블럭 상속X, 자식 클래스에서 매번 작성해야 함
 * 
 */
public class UnderAgeException extends RuntimeException {

	public UnderAgeException() {} // 기본 생성자
	
	public UnderAgeException(String message) { // 매개변수 생성자
		super(message);
	}
	
}
