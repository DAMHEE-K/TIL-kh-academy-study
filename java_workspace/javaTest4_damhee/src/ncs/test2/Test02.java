package ncs.test2;

import java.util.Arrays;

public class Test02 {
	public static void main(String[] args) {
		/*
		다음과 같이 선언되어 있는 배열에 1~10까지의 랜덤한 숫자(중복허용)를 넣고 이 배열에 어떤
		값이 셋팅 되었는지 출력 하고 배열 데이터의 합과 평균을 구하는 프로그램을 작성 하시오.
		option: 랜덤한 숫자의 중복 허용을 하지 않게 배열에 입력 한다.
		*/
		int[] arr3 = new int[5];
		int sum = 0;
		double avg = 0;
		
		for(int i=0; i<arr3.length; i++) {
			arr3[i] = (int)(Math.random()*10+1);			
			for(int j=0; j<i; j++) { // 중복값 제거를 위한 for문 작성
				if (arr3[j] == arr3[i]) {
					i--;
					break;
				}
			}
			sum += arr3[i];
		
		}
		for(int i=0; i<arr3.length; i++) { // 출력을 위한 for문
			System.out.print(arr3[i] + " ");
		}
		
		avg = (double)sum/arr3.length;
		System.out.printf("\nsum = %d\n", sum);
		System.out.printf("avg = %.1f", avg);
		
		
		
	}
}
