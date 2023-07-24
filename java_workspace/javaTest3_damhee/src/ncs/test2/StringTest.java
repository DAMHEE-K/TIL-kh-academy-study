package ncs.test2;

import java.text.DecimalFormat;

public class StringTest {

	public static void main(String[] args) {
		
		/*
		주어진 String 데이터를 “,”로 나누어 5개의 실수 데이터들을 꺼내어 합과 평균을 구한다.
		단, String 문자열의 모든 실수 데이터를 배열로 만들어 계산한다
		
		[실행결과]
		합계:29.320
		평균:5.864 (Format을 지원하는 객체사용할 것)
		 */
		
		String str = "1.22,4.12,5.93,8.71,9.34";
		
		double data[]= new double[5];
		double sum = 0;
		String[] st = str.split(","); // str에서 데이터를 분리한다.
		
		int i = 0;
		for(String tmp : st) { //for~each 문 사용한다.
			data[i] = Double.valueOf(tmp);  // 배열에 실수 데이터를 넣는다.
			sum += data[i++];
			//배열 데이터의 합을 구한다.
		}
		double avg = sum / i;
		
		DecimalFormat df = new DecimalFormat("##.###");
		System.out.println("합계 : " + df.format(sum));
		System.out.println("평균 : " + df.format(avg));
//		System.out.printf("합계 : %.3f \n",sum);  // 결과 값을 출력 한다. 
//		System.out.printf("평균 : %.3f", avg);
//		
		
	}

}
