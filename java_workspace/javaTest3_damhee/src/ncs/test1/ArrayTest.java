package ncs.test1;

public class ArrayTest {
	public static void main(String[] args) {
		/*
		2차원 배열에 들어 있는 데이터들의 합계와 평균을 구한다. 
		출력 결과는 다음과 같다. 소수점 아래 둘째자리까지 출력되게 한다.
		합계 : 767.00
		평균 : 38.35
		합계와 평균 값은 double로 처리한다.
		*/
		
		int [][] array = {{12, 41, 36, 56, 21}, 
							{82, 10, 12, 61, 45}, 
							{14, 16, 18, 78, 65}, 
							{45, 26, 72, 23, 34}};
		double sum = 0; // 합계를 저장할 변수 sum 
		double arrLength = 0;
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				sum += array[i][j];
				arrLength++;
			}
		}
		double avg = sum / arrLength;
		
		System.out.println("합계 : " + sum);
		System.out.printf("평군 : %.2f", avg);
	}
}
