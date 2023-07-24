package edu.kh.variable.practice;

import java.util.Arrays;

/**
 * 메소드 호출 시 매개인자의 타입에 따른 구분
 * call by value : 값 복사, 기본형
 * call by reference : 주소값 복사, 참조형
 */
public class CallByStudy {
	public static void main(String[] args) {
		CallByStudy study = new CallByStudy();
		
		int a = 10;
		study.test1(a); // 매개인자 (호출부에 넘기는 값)
		System.out.println(a); // 10
		
		int[] brr = { 1, 2, 3 };
		study.test2(brr);
		System.out.println(Arrays.toString(brr));
		
		// String은 참조형이지만 call by value처럼 작동한다
		// 문자열 저장소를 이용해서 immutable하게 관리하기 때문
		String str = "안녕";
		study.test3(str);
		System.out.println(str);
	}

	// 같은 문자열(예를 들어 " "와 같은 아이들) 참조가 필요한 경우, 
	// new 연산자를 사용해 객체를 생성하는 것 보다, 문자열 리터럴을 이용하여
	// 초기화하는 것이 좋다.
	private void test3(String str) {
		str += "ㅋㅋㅋ";
	}

	/**
	 * call by reference
	 *  - 객체 주소값을 복사하고, heap 영역의 객체를 공유한다.
	 * 참조형 매개변수
	 */
	private void test2(int[] brr) {
		for(int i=0; i<brr.length; i++) {
			brr[i] *= 100;
		}
	}

	/**
	 * call by value
	 * 기본형 매개변수
	 */
	private void test1(int a) {
					  // 매개변수이자 지역변수
		a *= 100; // 메소드 종료되면서 메모리 반환과 동시에 a값은 사라짐
				  // 단순 a 값을 복사해서 실행했을 뿐임
	}
	
//	private int test1(int a) {
//		return a *= 100; 
//		값을 반환해준다면 계산 결과를 호출부로 돌려보낼 수 있음
//	}
	
}
