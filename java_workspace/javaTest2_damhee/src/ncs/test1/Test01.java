package ncs.test1;

import java.util.Scanner;

public class Test01 {
	
	/*
	 * 5개의 점수를 공백으로 구분하여 하나의 문자열로 입력 받는다. 공백을 기준으로 값들을 분리한 다음, 
	 * 5개가 아니면 “다시 입력 하세요”라고 메시지를 출력한다.
	 *  각 분리 문자열을 정수로 변환한 다음, 숫자가 10부터 99까지의 값인지 확인하고 계산에 사용한다. 
	 * 입력된 값이 10부터 99까지의 정수가 아닌 경우 “다시 입력 하세요”라고 메시지를 출력한다. 
	 * 반복 실행은 하지 않는다.
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("A B C D E 점수를 차례대로 입력하세요. (공백으로 구분합니다.)");
		System.out.print("점수 입력 : ");
		String input = sc.nextLine();
		String[] tmp = input.split(" ");
		
		if(tmp.length != 5) {
			System.out.println("5개의 점수를 다 입력하지 않았습니다. 다시 입력하세요.");
		}

		int[] score = new int[tmp.length];
		
		boolean flag = false;
		
		for(int i=0; i<tmp.length; i++) {
			score[i] = Integer.valueOf(tmp[i]);
			
			if(10>score[i] || score[i]>99) {
				flag = true;
			}
		}
		
		if(flag) {
			System.out.println("다시 입력하세요. (10부터 99까지의 정수만 입력하세요.)");
		}
		
		
		double num1 = ((score[0] + score[1])/2) * 0.6;
		double num2 = ((score[2]+score[3])/2) * 0.2;
		double num3 = score[4] * 0.2;
		
		double sum = num1 + num2 + num3;
		System.out.printf("평가점수 : %.1f\n",sum);
		
		if(sum >= 90) {
			System.out.println("Class : Gold Class");
		} else if (sum >= 80) {
			System.out.println("Class : Silver Class");
		} else if (sum >= 70) {
			System.out.println("Class : Bronze Class");
		} else {
			System.out.println("Class : Normal Class");
		}
	}

}
