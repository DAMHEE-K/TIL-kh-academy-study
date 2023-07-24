package edu.kh.op.ex;

import java.util.Scanner;
public class OpExample { // 예제 코드 작성용 클래스
	
	// ex1() 메소드
	// - OpExample이 가지고 있는 기능 중 ex1()이라는 기능
	public void ex1() {
		// syso 작성 후 (ctrl + space)
		System.out.println("OpExample 클래에스 ex1() 기능 수행");
		System.out.println("클래스 분리 테스트");
		System.out.println("println 자동완성 해봤어요~");
	}
	
	// ex2() 메소드(기능)
	public void ex2() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 1 : ");
		int input1 = sc.nextInt(); // 다음 입력되는 정수를 읽어옴
		
		System.out.print("정수 입력 2 : ");
		int input2 = sc.nextInt();
		
		System.out.printf("%d / %d = %d\n", input1, input2, input1 / input2);
		System.out.printf("%d %% %d = %d\n", input1, input2, input1 % input2);
							// %% : printf에서 '%' 문자 출력하는 방법
	}
	
	public void ex3() {
		// 증감(증가, 감소) 연산자 : ++, --
		// - 피연산자(값)를 1 증가 또는 감소 시키는 연산자
		
		int iNum1 = 10;
		int iNum2 = 10;
		
		iNum1++; //iNum1 1증가
		iNum2--; //iNum2 1감소
		
		System.out.println("iNum1 : " + iNum1);
		System.out.println("iNum2 : " + iNum2);
		
		// 전위 연산 : ++3, --2 연산자가 앞쪽에 배치
		// - 다른 연산자보다 먼저 증가/감소		
		int temp1 = 5;
		System.out.println(++temp1 + 5);
						// ++5  +  5
						//   6  + 5 == 11
		System.out.println("temp1 : " + temp1); // 6
		
		// 후위 연산 : 10++, 6-- 연산자가 뒤쪽에 배치
		// - 다른 연산자보다 나중에 증가/감소
		int temp2 = 3;
		System.out.println(temp2-- + 2);
						//  3-- +  2
						//  3 + 2 = 5 연산 후, temp2 = 2;(1 감소)
		System.out.println("temp2 : " + temp2); // 2
		
		int a = 3;
		int b = 5;
		int c = a++ + --b;
		
		//		(a)3++ + --5(b)
		// c =  (a)3++ +   4(b)
		// c = 7
		
		// 미뤄놨던 a의 후위연산 a++ == 3+1 == 4 시행
		
		// 최종적으로 a, b, c는 각각 얼마일까요
		System.out.printf("%d / %d / %d\n", a, b, c);
		
	}
	
	public void ex4() {
		
		// 비교 연산자 : >, <, >=, <=. ==, !=
		// - 비교 연산자의 결과는 항상 논리값(true / false)
		// - 등호(=)가 포함된 연산자에서 등호는 항상 오른쪽!
		
		// 같다 기호는 =, == 어떤 것? : ==
		// 왜? 등호 1개(=)는 대입 연산자로 사용
		// 구분을 위해서 두개(==)를 "같다"라는 의미로 사용
		
		int a = 10;
		int b = 20;
		
		System.out.println( a > b ); //false
		System.out.println( a < b ); //true
		System.out.println( a != b ); //true
		System.out.println( (a == b) == false ); //true
		
		System.out.println("-----------------------------------");
		
		int c = 4;
		int d = 5;
		
		System.out.println( c < d ); //true
		System.out.println( c + 1 <= 5); //true
		System.out.println( c >= d - 1); //true
		System.out.println( (++c != d ) == (--c != d ) );
							// (5 != 5) -> false
							//			  (4 != 5) -> true
							//  0 == 1 니까 값은 false
		
		System.out.println("-------------------------------------");
		
		int temp = 723;
		System.out.println("temp는 짝수인가?" + (temp % 2 == 0));
		System.out.println("temp는 짝수인가?" + (temp % 2 != 1));

		System.out.println("temp는 홀수인가?" + (temp % 2 != 0));
		System.out.println("temp는 홀수인가?" + (temp % 2 == 1));

		System.out.println("temp는 3의 배수인가?" + (temp % 3 == 0));
		System.out.println("temp는 4의 배수인가?" + (temp % 4 == 0));
		System.out.println("temp는 5의 배수인가?" + (temp % 5 == 0));		
	}
	
	public void ex5() {
		
		// 논리 연산자 : &&(AND) , ||(OR)
		
		// &&(AND) 연산자 : 둘 다 true이면 true, 나머진 false
		// 와, 그리고(~이고), ~면서, ~이면서, ~부터 ~까지, ~사이
		// ex) 사과와 바나나, 사과 그리고 바나나, 사과이면서 바나나
		
		int a = 101;
		System.out.println("a는 100 이상?" + (a>100)); // a는 100 이상?
		System.out.println("a는 짝수?" + (a%2==0)); // a는 짝수?
		
		// a는 100 이상 이면서 짝수인가?
		System.out.println("a는 100이상 이면서 짝수인가?" + ((a>100) && (a%2==0)));						
		
		
		int b = 5;		
		// b는 1부터 10까지 숫자 범위에 포함되어 있는가? 
		// (1~10사이의 숫자인가?)
		// (b는 1보다 크거나 10보다 작거나 같은가?)
		
		System.out.println(b>=1); //b는 1 이상인가?
		System.out.println(b<=10); //b는 10 이하인가?
		System.out.println((b>=1) && (b<=10)); //두가지 조건 만족하는가
		
		System.out.println("---------------------------------------------");
		
		// ||(OR) 연산자 : 둘 다 false이면 false, 나머진 true (AND의 반대)
		// 또는, ~거나, ~이거나
		
		int c = 10;		
		System.out.println((c>10) || (c%2==0)); // c는 10을 초과했거나 짝수인가		
	}
	
	public void ex6() {
		
		// 논리 부정 연산자 : !
		// - 논리 값을 반대로 바꾸는 연산자
		
		boolean bool1 = true;
		boolean bool2 = !bool1;
		
		System.out.println("bool1 : " + bool1); //true
		System.out.println("bool2 : " + bool2); //false
		
		System.out.println("---------------------------------------------");
		
		// 정수를 하나 입력 받았을 때
		// 1) 해당 정수가 1부터 100사이 값이 맞는지 확인 (1이상 100이하)
		// 2) (반대) 1부터 100사이의 값이 아닌지 확인
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		// 1 <= inpurt <= 100
		boolean result1 = ((1<=input) && (input<=100));				
				
		// 1이상이면서 100이하인 <-> 1미만 또는 100초과
		boolean result2 = ((1>input) || (100<input));
		System.out.printf("%d은/는 1미만 또는 100초과의 정수인가? : %b\n", input, result2);
		
		boolean result3 = (!((1<=input) && (input<=100)));
		System.out.printf("%d은/는 1미만 또는 100초과의 정수인가? : %b\n", input, result3);	
	}
	
	public void ex7() {
		
		// 복합 대입 연산자 : +=, -=, *=, /=, %=
		// - 피연산자가 자신과 연산 후 결과를 다시 자신에게 대입
		
		int a = 10;
		
		// a를 1증가
		a++; // a = a + 1, a += 1
		System.out.println("a를 1 증가 : " + a);
		
		// a를 4증가
		a += 4;
		System.out.println("a를 4 증가 : " + a);
		
		// a를 10 감소
		a -= 10;
		System.out.println("a를 10 감소 : " + a);
		
		// a를 3배 증가
		a *= 3;
		System.out.println("a를 3배 증가 : " + a);

		// a를 6으로 나눴을 때 몫
		a /= 6;
		System.out.println("a를 6으로 나눴을때 몫 : " + a);
		
		// a를 2로 나눴을때 나머지
		a %= 2;
		System.out.println("a를 2로 나눴을때 나머지 : " + a);
	}
	
	public void ex8 () {
		
		// 삼항 연산자 :    조건식  ?  식1  :  식2		
		// - 조건식의 결과가 true이면 식1, false면 식2를 수행하는 연산자
		
		// ** 조건식 : 연산 결과가 true / false인 식
		// 		 	 (비교, 논리, 논리 부정 연산 포함)
		
		int num = 30;
		
		// num이 30보다 크면(초과) : "num은 30보다 큰 수이다."
		// 아니면					: "num은 30이하의 수이다." 출력
		
		String str1 = "num은 30보다 큰 수이다.";
		String str2 = "num은 30이하의 수이다.";
		
		String result = num > 30 ?  str1 : str2;
					//    조건식  ?   식1  :  식2
					//				true : false
		
		// num값이 30을 초과하면 str1
		// num값이 30을 초과하지 못하면 str2를
		// result 변수에 저장
		
		System.out.println(result);
		
		System.out.println("-------------------------------------");
		
		// 입력받은 정수가 음수인지 양수인지 구분
		// 단, 0은 양수로 처리
		
		// ex)
		// 정수 입력 : 4
		// 양수 입니다.
		
		// 정수 입력 : -5
		// 음수 입니다.
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 : ");
		int input = sc.nextInt();
		
		String str3 = "양수 입니다.";
		String str4 = "음수 입니다.";	
		String result2 = input>=0 ? str3 : str4;
		
		// 불필요한 변수 선언을 줄이고 코드를 짧게
		// String result2 = input>=0 ? "양수" : "음수";
		// System.out.println(result2 + "입니다.");
		

		
	}
	
	
}
