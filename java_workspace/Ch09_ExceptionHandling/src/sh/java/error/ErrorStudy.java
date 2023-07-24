package sh.java.error;

/**
 * java doc 프로그램 문서화 가능한 주석
 * 
 * Error
 * - 프로그램 수행 시에 치명적 상황이 발생한 것 Error클래스를 사용하여 표현함
 * - 예외처리로는 해결이 되지 않음
 * - 소스코드를 수정 또는 환경에 대한 처리가 필요
 * - VirtualMachineError, OutOfMemoryError, StackOverflowError...
 */
public class ErrorStudy {

	public static void main(String[] args) {
		System.out.println("---- 시작 ----");
		
		ErrorStudy study = new ErrorStudy();
		// study.test1();
		// study.test2();
		
		System.out.println("---- 끝 ----");
		// test1()에서 에러가 발생하여 프로그램 비정상적 종료되었으므로,
		// "---- 끝 ----" 출력되지 않음

	}
	
	
	
	/**
	 * OutOfMemoryError
	 * - 할당받은 heap 메모리를 모두 소진한 경우
	 */
	public void test1() {
		// java.lang.OutOfMemoryError: Requested array size exceeds VM limit
		long[] arr = new long[Integer.MAX_VALUE];
	}
	
	
	
	/**
	 * StackOverflowError
	 * - 할당받은 stack 메모리를 모두 소진한 경우
	 * */
	public void test2() {
		System.out.println("test2 호출!!!");
		test2();
		// 메소드 안에서 스스로를 다시 호출하는 것, 재귀호출
	}
	

}
