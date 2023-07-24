package ncs.test6;

import java.util.Scanner;

public class ExceptionTest {
	/*
	2부터 5까지의 정수형 데이터만을 키보드로 입력 받아 1부터 입력 받은 수까지의 합을 출력 한
	다. 단, 입력 받은 수가 2부터 5까지의 범위를 벋어나면 “입력 값에 오류가 있습니다.”라고 출력
	하고 프로그램을 종료한다.
	*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("2와 5 사이의 정수 입력 >> ");
		int data = sc.nextInt();
		
		try {
			Calculator calculator = new Calculator();
			System.out.println("결과 값 : " + calculator.getSum(data));
		} catch (InvalidException e) {
			System.err.println(e.getMessage());
		}
	}
	
}
