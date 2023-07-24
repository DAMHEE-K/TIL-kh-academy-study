package ncs.test5;

import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Scanner;

public class TestScore {
	public static void main(String[] args) {
		/*
		 * 학생 3명의 3 과목의 성적을 기록할 수 있는 기본 자료형 2차원 배열을 선언한다. 
		 * 각 행의 3열에는 과목의 총점을 기록하고, 각 행의 4열에는 과목의 평균을 기록한다
		 */
		Scanner sc = new Scanner(System.in);
		double[][] scoreArr = new double[3][3];
		
		for(int i=0; i<scoreArr.length; i++) {
			for(int j=0; j<scoreArr[i].length; j++) {
				System.out.print(i+"번 학생의" + (j+1) + "번째 과목 점수 입력 :");
				scoreArr[i][j] = sc.nextDouble();
			}	
		}
		
		System.out.println("index 과목1   과목2   과목3   총점   평균");
		
		for(int i=0; i<scoreArr.length; i++) {
			double sum = 0.0;
			double avg = 0.0;
			System.out.print(i + "    ");
			
			for(int j=0; j<scoreArr[i].length; j++) {
				sum += scoreArr[i][j];
				avg = sum / 3.0;
				System.out.print(" " + scoreArr[i][j] + "  ");
			}
			System.out.printf("%.1f  %.1f\n", sum, avg);
		}
	}
}
