package ncs.test6;

import java.util.Scanner;

public class CalcTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculate calc = new Calculate();
		
		int num1;
		int num2;
		
		while(true) {
			System.out.print("첫 번째 숫자 입력 (1~9 사이의 정수형 입력) : ");
			num1 = sc.nextInt();
			
			System.out.print("두 번째 숫자 입력 (1~9 사이의 정수형 입력) : ");
			num2 = sc.nextInt();
			
			if ((0<num1 && num1<10)&&(0<num2 && num2<10)) {
				break;
			} else {
				System.out.println("1~9 사이의 정수만 입력하세요.");
				System.out.println("-----------------------");
			}
		}
		
		System.out.println("합 : " + calc.sum(num1, num2));
		System.out.println("차 : " + calc.subtract(num1, num2));
		System.out.println("곱 : " + calc.multiply(num1, num2));
		System.out.println("나누기 : " + calc.divide(num1, num2));
		
	}
}
