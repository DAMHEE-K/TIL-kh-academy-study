package ncs.test6;

import java.util.Arrays;

public class Test06 {
	/*
	1)eclipse Argument 에서 1~5까지의 두 개의 정수형 데이터를 입력 받아 2차원 배열을 만든다.
	2) 입력 받은 데이터가 2개 미만 또는 2개 초과로 입력 하면 “다시 입력 하세요” 출력
	3) 1~5이외의 숫자가 입력 될 경우 “숫자를 확인 하세요” 출력
	4) 입력 받은 두개의 정수를 이용하여 2차원 배열을 생성한다.
	5) 2차원 배열에 1~5까지의 랜덤한 숫자(중복허용)를 넣는다.
	6) 배열의 내용을 출력 한다.
	7) 배열의 총합과 평균을 출력 한다.
	*/
	public static void main(String[] args) {
		// args는 공백을 기준으로 String[]로 만들어짐
		int num1 = Integer.valueOf(args[0]);
		int num2 = Integer.valueOf(args[1]);
		
		double sum=0;
		double avg=0;
		int size = 0;

		if(!(args.length==2)) { // 입력 받은 데이터가 2개 미만 또는 2개 초과로 입력 하면 == 길이가 2가 아니면
			System.out.println("다시 입력하세요.");
		} else if ((1>num1||num1>6)||(1>num2||num2>6)) {
			System.out.println("숫자를 확인 하세요.");
		} else {
			int[][] intArr = new int[num1][num2];
			
			for(int i=0; i<intArr.length; i++) {
				for(int j=0; j<intArr[i].length; j++) {
					intArr[i][j] = (int)(Math.random()*5+1);
					System.out.print(intArr[i][j] + " ");
					sum += intArr[i][j];
					size++;
				}
				System.out.println();
			}
			avg = sum / size;
			System.out.printf("sum = %.1f",sum);
			System.out.printf("\navg = %.1f", avg);
		}

	}
}
