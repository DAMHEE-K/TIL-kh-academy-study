package edu.kh.control.practice;

import java.util.Scanner;

public class ConditionPractice {

	public void practice1() {
		// 키보드로 입력받은 정수가 양수이면서 짝수일때만 "짝수입니다." 출력
		// 짝수가 아니면 "홀수입니다." 출력
		// 양수가 아니면 "양수만 입력해주세요." 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 한개 입력 하세요 : ");
		int num = sc.nextInt();
		
		if(num<0) {
			System.out.println("양수만 입력해주세요.");
		} else {
			if((num>0) && (num%2==0)) {
				System.out.println("짝수입니다.");
			} else {
				System.out.println("홀수입니다.");
			} 
			}
			
	}
	
	public void practice2() {
		// 국어, 영어, 수학 세 과목의 점수를 키보드로 입력받고
		// 합계와 평균을 계산하고
		// 합계와 평균을 이용하여 합격 / 불합격 처리하는 기능을 구현하세요.
		// (합격 조건 : 세 과목의 점수가 각각 40점 이상이면서 평균이 60점 이상일 경우)
		
		// 합격했을 경우 과목 별 점수와 합계, 평균, "축하합니다, 합격입니다!"를 출력하고
		// 불합격인 경우에는 "불합격입니다."를 출력하세요
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("국어점수 : ");
		int korean = sc.nextInt();
			
		System.out.print("수학점수 : ");
		int math = sc.nextInt();
			
		System.out.print("영어점수 : ");
		int english = sc.nextInt();
			
		int sum = korean + math + english;
		double avg = sum/3;
			
		if(korean>=40 && math>=40 && english>=40 && avg>=60) {
			System.out.println("국어 : " + korean);
			System.out.println("수학 : " + math);
			System.out.println("영어 : " + english);
			System.out.println("합계 : " + sum);
			System.out.println("평균 : " + avg);
			System.out.println("축하합니다, 합격입니다!"); 
		} else {
			System.out.println("불합격입니다."); 
		}							
		
	}
	
	public void practice3() {
		// 1 ~ 12 사이의 수를 입력받아 해당 달의 일수를 출력하세요(2월 윤달은 생각하지 않습니다)
		// 잘못 입력한 경우 "oo월은 잘못 입력된 달입니다."를 출력하세요 (switch 사용)
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1~12 사이의 정수 입력 : ");
		int month = sc.nextInt();
		
		switch(month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			System.out.printf("%d월은 %d일까지 있습니다.", month, 31); break;
		case 4: case 6: case 9: case 11: 
			System.out.printf("%d월은 %d일까지 있습니다.", month, 30); break;
		case 2:
			System.out.printf("%d월은 %d일까지 있습니다.", month, 28); break;
		default : System.out.printf("%d월은 잘못 입력된 달 입니다.", month); break;
		}
	}
	
	public void practice4() {
		// 키, 몸무게를 double로 입력받고 BMI지수를 계산하여 결과에 따라
		// 저체중/정상체중/과체중/비만 출력하세요
		// BMI = 몸무게 / 키(m) * 키(m)
		// BMI가 18.5 미만일 경우 저체중 / 18.5이상 23미만일 경우 정상체중
		// BMI가 23이상 25미만일 경우 과체중 / 25이상 30미만일 경우 비만
		// BMI가 30이상일 경우 고도비만
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("키(m)를 입력해 주세요 : ");
		double height = sc.nextDouble();
		
		System.out.print("몸무게(kg)를 입력해 주세요 : ");
		double weight = sc.nextDouble();
		
		double bmi = weight / (height * height);
		
		System.out.println("BMI 지수 : " + bmi);
		
		if(bmi >= 30) {
			System.out.println("고도비만");
		} else if (25<=bmi && bmi<30) {
			System.out.println("비만");
		} else if (23<=bmi && bmi<25) {
			System.out.println("과체중");
		} else if (18.5<=bmi && bmi<23) {
			System.out.println("정상체중");
		} else {
			System.out.println("저체중");
		}
	}
	
	public void practice5() {
		/*
		중간고사, 기말고사, 과제점수, 출석회수를 입력하고 Pass 또는 Fail을 출력하세요.
		평가 비율은 중간고사 20%, 기말고사 30%, 과제 30%, 출석 20%로 이루어져 있고
		이 때, 출석 회수는 총 강의 회수 20회 중에서 출석한 날만 따진 값으로 계산하세요. 
		70점 이상일 경우 Pass, 70점 미만이거나 전체 강의에 30% 이상 결석 시 Fail을 출력하세요
		
		[실행 화면1]
		중간 고사 점수 : 80
		기말 고사 점수 : 30
		과제 점수 : 60
		출석 횟수 : 18
		
		===========결과==========
		
		중간 고사 점수(20) : 16.0
		기말 고사 점수(30) : 9.0
		과제 점수		(30) : 18.0
		출석 점수		(20) : 18.0
		총점 : 61.0
		Fail [점수미달]
		
		[실행 화면2]
		중간 고사 점수 : 80
		기말 고사 점수 : 90
		과제 점수 : 50
		출석 횟수 : 15
		
		===========결과==========
		
		중간 고사 점수(20) : 16.0
		기말 고사 점수(30) : 27.0
		과제 점수		(30) : 15.0
		출석 점수		(20) : 15.0
		총점 : 73.0
		Pass
		
		[실행 화면3]
		중간 고사 점수 : 100
		기말 고사 점수 : 80
		과제 점수 : 40
		출석 횟수 : 10
		
		===========결과==========
		
		Fail [출석 횟수 부족(10/20)]
				
		*/
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("중간 고사 점수 : ");
		double midTerm = sc.nextDouble();
		
		System.out.print("기말 고사 점수 : ");
		double finalTerm = sc.nextDouble();
		
		System.out.print("과제 점수 : ");
		double homework = sc.nextDouble();
		
		System.out.print("출석 횟수 : ");
		int attendance = sc.nextInt();
		
		System.out.println("==============결과==============");
		
		if(attendance <= (20*0.7)) { // 30% 이상 결석
			
			System.out.println("Fail [출석 횟수 부족(" + attendance + "/20)]");
			
		} else {
			
			midTerm *= 0.2;
			finalTerm *= 0.3;
			homework *= 0.3;
			double sum = midTerm + finalTerm + homework + attendance;
			
			System.out.printf("중간 고사 점수(20) : %.1f\n", midTerm);
			System.out.printf("기말 고사 점수(30) : %.1f\n", finalTerm);
			System.out.printf("과제 점수(30) : %.1f\n", homework);
			System.out.printf("출석 점수(20) : %.1f\n", attendance);
			System.out.printf("총점 : %.1f\n" + sum);
			
			if (sum>=70) {
			System.out.println("PASS");
			} else {
				System.out.println("Fail[점수 미달]");
			}
		}
	}
	
	
}

