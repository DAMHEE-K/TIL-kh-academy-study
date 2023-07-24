package ncs.test4;

import java.util.Scanner;

public class Test04 {
	public static void main(String[] args) {
		/*
		프로그램 실행 시 eclipse argument로 5~10까지의 정수형 데이터를 입력 받는다.
		입력 받은 정수 값을 Calc class의 calculate()함수를 이용하여 1부터 입력 받은 숫자까지
		짝수만 더하는 프로그램을 작성 한다.
		*/
		Calc calc = new Calc();
		
		int data = Integer.valueOf(args[0]);

		System.out.print("짝수 : ");
		System.out.println("\n결과 : " + calc.calculate(data));
	}
}
