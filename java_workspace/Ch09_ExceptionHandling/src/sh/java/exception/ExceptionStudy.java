package sh.java.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Exception
 * - 프로그램 실행 중 오류가 발생했으나, 예외처리(try...catch)에 의해서 수습될 수 있는 미약한 오류
 * - Unchecked Exception : 컴파일 타임에 확인 불가한 예외. 예외 처리를 강제화하지 않음.
 *   ㄴ RuntimeException 클래스와 그 후손 클래스 모두 UncheckedException
 * - Checked Exception : 컴파일 타임에 확인 가능한 예외. 예외처리를 강제화, 예외처리를 안하면 컴파일 오류 발생
 * - 두 타입의 예외 모두 발생 시 예외처리가 안되면 프로그램은 비정상 종료된다.
 */
public class ExceptionStudy {

	public static void main(String[] arg) {
		System.out.println("---- start ----");
		ExceptionStudy study = new ExceptionStudy();
		// study.test1();
		// study.test2();
		// study.test3();
		// study.test4();
		study.test5();
		
		
		System.out.println("---- end ----");
		
	}
	

	/**
	 * ArithmeticException, InputMismatchException 등의 예외는 
	 * try...catch 예외처리절에 의해 정상적으로 복구될 수 있다.
	 */
	public void test1() {
		
		try {
			// Unchecked Exception (빨간줄 안뜸)
			// System.out.println(10/0);
			// java.lang.ArithmeticException: / by zero
			//				산술연산예외		  / 예외 메세지(Exception message)
			
			String str = null;
			System.out.println(str.length());
			// java.lang.NullPointerException: 
			// Cannot invoke "String.length()" because "str" is null
			
			
			// checked Exception (빨간줄 뜸)
			FileReader fr = new FileReader("hello.text");
			// FileNotFoundException은 checked exception이라 예외처리 강제화
			
			// java.io.FileReader.FileReader(String fileName) throws FileNotFoundException
	        // java.io : 패키지
	        // FileReader : 클래스
	        // FileReader(String fileName) : 생성자 메소드
	        // throws FileNotFoundException : FileNotFoundException을 던짐
		
		} catch (ArithmeticException e) {
			// ArithmeticException이 발생하면 이 catch 절에 의해서 예외처리 되고,
			// 이후 프로그램은 정상적으로 실행됨
			System.out.println("ArithmeticException이 발생함(던져졌음)!");
		} catch(FileNotFoundException e) {
			System.out.println("FileNotFoundException이 발생함");
		} catch(NullPointerException e) {
			System.out.println("NullPointerException이 발생");
		}
	}
	
	/**
	 * catch 예외처리 절에서도 예외로그, 예외메세지 등은 확인이 가능
	 */
	public void test2() {
		
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("정수 입력 : ");
			int num = sc.nextInt();
			System.out.printf("[%d]를 입력하셨습니다.\n", num);
			
		} catch(InputMismatchException e) {
			System.out.println("올바른 정수를 입력해주세요");
			System.out.println(e.getMessage()); // 예외메세지 출력
			e.printStackTrace(); // 호출스택출력
			// java.util.InputMismatchException
		}
	}
	
	/**
	 * try..catch 처리 흐름
	 */
	public void test3() {
		System.out.println(1);
		try {
			System.out.println(2);
			// System.out.println(100 / 0); // 예외 발생!
			System.out.println(3); // 실행되지 않고 catch절로 이동
			
			if(true)
				return; // 현재 실행중인 메소드를 종료하고 현재 메소드의 호출부로 돌아감
			
		} catch(ArithmeticException e) { // 예외가 발생했을 경우 시행
			System.out.println(4);
		} finally {
			// 예외 발생유무와 관계없이 실행되는 구문
			// 자원반납 등을 작성
			// try 절의 조기 리턴시에도 실행됨
			System.out.println("*");
		}
		System.out.println(5);
	}
	
	/**
	 * catch 절의 다형성 (부모타입의 변수를 통해 자식 객체를 제어)
	 * - 예외 객체의 부모자식 관계를 고려해 다형성을 적용할 수 있다.
	 * - 자식/부모 타입의 예외를 동시에 여러개 작성하는 경우, 자식-부모 타입순으로 catch절 작성해야 한다.
	 */
	public void test4() {
		
		try {
			int n = new Random().nextInt(4); // 4가지 경우의 정수형 난수를 반환 0, 1, 2,3
			
			switch(n) {
			case 0:
				System.out.println(100/0);
				break;
			case 1:
				String s = null;
				System.out.println(s.length());
				break;
			case 2:
				int[] arr= new int[3];
				System.out.println(arr[100]);
				break;
			default :
				System.out.println("Lucky~");
			}
			
		} catch (NullPointerException e) {
			System.out.println("null~~~~");
		} catch (Exception e) { // RuntimeException이나 Exception등 조상으로 처리 가능
			e.printStackTrace();
		}
			
//		} catch(ArrayIndexOutOfBoundsException e) {
//			e.printStackTrace();
//		} catch(ArithmeticException e) {
//			e.printStackTrace();
//		} catch(NullPointerException e) {
//			e.printStackTrace();
//		} 
		
	}
	
	/**
	 * @실습문제 : 사용자로부터 2개의 정수를 받아서 합을 출력하는 코드 작성
	 * - 사용자가 정수가 아닌 문자열을 입력한 경우, "잘못 입력하셨습니다." 출력 후 종료되어야 함 
	 */
	public void test5() {
		Scanner sc = new Scanner(System.in);
		
		
		while(true) {
			try {
				System.out.print("정수1 입력 : ");
				int num1 = sc.nextInt();
				
				System.out.print("정수2 입력 : ");
				int num2 = sc.nextInt();
				
				System.out.println("두 정수의 합 : " + (num1+num2));
				break; // 정상처리시 반복문 탈출
				
			} catch(InputMismatchException e) {
				System.out.println("잘못 입력하셨습니다.");
				break; // 비정상종료시 반복문 탈출
			}
			
		}
		
	}
}
