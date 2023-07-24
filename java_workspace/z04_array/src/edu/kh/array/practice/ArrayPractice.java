package edu.kh.array.practice;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayPractice {
	
	public void practice1() {
		
		// 길이가 9인 배열을 선언 및 할당하고, 1부터 9까지의 값을 반복문을 이용하여
		// 순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
		// 짝수 번째 인덱스 값의 합을 출력하세요. (0번째 인덱스는 짝수로 취급)
		
		/* [실행 화면]
		 * 
		 * 1 2 3 4 5 6 7 8 9
		 * 짝수 번째 인덱스의 합 : 25
		
		 */
		
		int[] arr = new int[9];
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			arr[i] = i+1;
			
			System.out.print(arr[i]+ " ");
			
			if(i%2 == 0) {
				sum += arr[i];
			}
		}
		System.out.println("\n짝수 번째 인덱스 값의 합 : " + sum);
	}
	
	public void practice2() {
		
		// 길이가 9인 배열을 선언 및 할당하고, 9부터 1까지의 값을 반복문을 이용하여
		// 순서대로 배열의 각 인덱스 요소에 대입하고 출력한 후
		// 홀수 번째 인덱스 값의 합을 출력하세요. (0번째 인덱스는 짝수로 취급)
		
		/* [실행 화면]
		 * 
		 * 9 8 7 6 5 4 3 2 1
		 * 홀수 번째 인덱스의 합 : 20
		 */
		
		int[] arr = new int[9];
		
		int sum = 0;
		
		for(int i=0; i<arr.length ; i++) {
			arr[i] = 9-i;
			
			if(i%2 == 1) {
				sum += arr[i];
			}
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n홀수 번째 인덱스 값의 합 : " + sum);
		
	}
	
	public void practice3() {
		// 사용자에게 입력받은 양의 정수만큼 배열 크기를 할당하고
		// 1부터 입력받은 값까지 배열에 초기화한 후 출력하세요
		/*
		 * [실행화면]
		 * 양의 정수 : 5
		 * 1 2 3 4 5
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("양의 정수 : ");
		int input = sc.nextInt();
		
		int[] arr = new int[input];
		
		for(int i=0; i<input; i++) {
			arr[i] = i+1;
			
			System.out.print(arr[i] + " ");
		}
	}
	
	public void practice4() {
		// 정수 5개를 입력 받아 배열을 초기화하고
		// 검색할 정수를 하나 입력 받아 배열에서 같은 수가 있는 인덱스를 찾아 출력
		// 배열에 같은 수가 없을 경우 "일치하는 값이 존재하지 않습니다" 출력
		
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[5];
		
		for(int i=0; i<arr.length; i++) {
			System.out.printf("입력 %d : ", i );
			arr[i] = sc.nextInt();
		}
		
		
		System.out.print("검색할 값 : ");
		int search = sc.nextInt();
		
		boolean flag = false; // 일치하는 값 없음
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] == search) {
				System.out.println("인덱스 : " + i);
				flag = true;
			}
		}
		
		if(!flag) {
			System.out.println("일치하는 값이 존재하지 않습니다.");
		}
	}
		
	public void practice5() {
		
		// 문자열을 입력받아 하나하나를 배열에 넣고, 검색할 문자가 문자열에 몇 개 들어가 있는지
		// 개수와 몇 번째 인덱스에 위치하는지 인덱스를 출력하세요.
		
		/* [실행화면]
		 * 문자열 : application
		 * 문자 : i
		 * application에 i가 존재하는 위치(인덱스) : 4 8
		 * i 개수 : 2
		 * */
		
		/*
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("문자열 : ");
		String str = sc.next();
		char[] str2 = new char[str.length()];
		
		for(int i=0; i<str.length(); i++) {
			str2[i] = str.charAt(i);
		}
		
		System.out.print("문자 : "); // 검색할 문자
		String search = sc.next();
		
		int sum = 0;
		for(int i=0; i<str.length(); i++) {
			if(search.equals(str.charAt(i))) {
				System.out.println(str + "에 " + search + "가 존재하는 위치(인덱스)" + i);
				sum++;
			}
		}
		*/
		
		// 선생님 코드
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 : ");
		String input = sc.nextLine();
		
		System.out.print("문자 : ");
		char ch = sc.next().charAt(0); // 입력받은 문자열에서 0번 인덱스 문자를 반환
		// String -> char
		
		char[] arr = new char[input.length()]; // 입력받은 문자열의 길이 만큼의 배열 생성
		
		int count = 0; // input에 일치하는 ch가 몇 개 있는지 카운트하는 변수
		
		System.out.print(input + "에 " + ch + "가 존재하는 위치(인덱스) : ");
		
		for(int i=0; i<arr.length; i++) {
			
			// 배열 대입
			arr[i] = input.charAt(i); // 입력받은 문자열에서 i번째 인덱스 문자를 arr[i]에 대입
			
			// 검색 + 카운트
			if(arr[i] == ch) { // 검색조건 // char는 기본자료형이기 때문에 == 사용 가능
				System.out.print(i + " ");
				count++;
			}
		}
		
		System.out.println("\n" + ch + " 개수 : " + count);
	}
	
	public void practice6() {
		// 사용자가 배열의 길이를 직접 입력하여 그 값만큼 정수형 배열을 선언 및 할당하고
		// 배열의 크기만큼 사용자가 직접 값을 입력하여 각각의 인덱스에 값을 초기화하세요
		// 그리고 배열 전체 값을 나열하고 각 인덱스에 저장된 값들의 합을 출력하세여
		
		/*[실행화면]
		 * 정수 : 5
		 * 배열 0번째 인덱스에 넣을 값 : 4
		 * 배열 1번째 인덱스에 넣을 값 : -4
		 * 배열 2번째 인덱스에 넣을 값 : 3
		 * 배열 3번째 인덱스에 넣을 값 : -3
		 * 배열 4번째 인덱스에 넣을 값 : 2
		 *  4  -4  3  -3  2
		 *  총  합 : 2
		 */
		 
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 : ");
		int input = sc.nextInt();
		
		int[] arr = new int[input];
		
		int sum = 0;
		String value = "";
		
		for(int i=0; i<arr.length; i++) {
			System.out.printf("배열 %d번째 인덱스에 넣을 값 : ",i);
			arr[i] = sc.nextInt();
			sum += arr[i];
			value += arr[i] + " ";
//			System.out.print(arr[i]+" "); 오답
		}
		System.out.println(value);
		System.out.printf("총 합 : %d", sum);
		
	}
	
	public void practice7() {
		// 주민등록번호 번호를 입력받아 char 배열에 저장한 후 출력하세요
		// 단, char 배열 저장 시 성별을 나타내는 숫자 이후부터 *로 저장하세요
		/*[실행화면]
		 * 주민등록번호(-포함) :123456-1234567
		 * 123456-1******
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		
		
		System.out.print("주민등록번호(-포함) : ");
		char idNum[] = sc.nextLine().toCharArray();
		
		
		for(int i=0; i<idNum.length; i++) {
			if(i<8) {
				System.out.print(idNum[i]);
			} else {
				System.out.print("*");
			}
		}
		
		// char[] arr = new char[idNum.length()];
		// arr[i] = idNum.charAt(i);
		// System.out.println(Arrays.toString(arr));
	}
	
	public void practice8() {
		
		/* 3이상인 홀수를 입력받아 배열의 중간까지는 1부터 1씩 증가하여 오름차순으로
		 * 값을 넣고, 중간 이후부터 끝까지는 1씩 감소하여 내림차순으로 값을 넣어
		 * 출력하세요. 단, 입력한 정수가 홀수가 아니거나 3미만일 경우 "다시 입력하세요"를
		 * 출력하고 다시 정수를 받도록 하세요.
		 * 
		 * [실행화면]
		 * 정수 : 4
		 * 다시 입력하세요.
		 * 정수 : -6
		 * 다시 입력하세요.
		 * 정수 : 5
		 * 1, 2, 3, 2, 1
		 * */
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수 : ");
		int input = sc.nextInt();
		
		while(input%2!=1 || input<3) {
			System.out.print("다시 입력하세요.\n정수 입력 : ");
			input = sc.nextInt();
			if(input%2==1 && input>=3) {
				break;
			}
		}
		
		int[] arr = new int[input];
		
		int num = 0;
		
		for(int i=0; i<input; i++) {
			if(i <= input/2) {
				arr[i] = ++num;
			} else {
				arr[i] = --num;
			}
			
			if(i == arr.length-1) { // 마지막 바퀴
				System.out.println(arr[i]);
			} else {
				System.out.print(arr[i]+", ");
			}
		}
	}
	
	public void practice9() {
		// 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고, 
		// 1~10사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요
		
		int[] arr = new int[10];
		
		System.out.print("발생한 난수 : ");
		for(int i=0; i<arr.length; i++) {
			int random=(int)(Math.random()*10+1);
			arr[i] = random;			
			
			System.out.print(arr[i]+" ");
		}
		
	}
	
	public void practice10() {
		// 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고, 
		// 1~10사이의 난수를 발생시켜 배열에 초기화한 후 
		// 배열 전체 값과 그 값중에서 최대값과 최소값을 출력하세요
		
		int[] arr = new int[10];
		
		System.out.print("발생한 난수 : ");
		for(int i=0; i<arr.length; i++) {
			int random=(int)(Math.random()*10+1);
			arr[i] = random;			
			
			for(int x=0; x<i; x++) {
				if(arr[x] == random) {
				i--;
				break;
				}
			}
		}
		
		for(int j=0; j<arr.length; j++) {
			System.out.print(arr[j]+" ");
		}
		Arrays.sort(arr);
		System.out.println("\n최대값 :" + arr[0]);
		System.out.println("최소값 :" + arr[9]);
	}
	
	public void practice11() {
		
		// 10개의 값을 저장할 수 있는 정수형 배열을 선언 및 할당하고, 
		// 1~10사이의 난수를 발생시켜 배열에 초기화한 후 출력하세요
		
		int[] arr = new int[10];
		
		for(int i=0; i<arr.length; i++) {
			int random=(int)(Math.random()*10+1);
			arr[i] = random;			
			
			for(int x=0; x<i; x++) {
				if(arr[x] == random) {
				i--;
				break;
				}
			}
			
		}
		for(int j=0; j<arr.length; j++) {
			System.out.print(arr[j]+" ");
		}
		
	}
	
	public void practice12() {
		
		int[] arr = new int[6];
		
		for(int i=0; i<arr.length; i++) {
			int random=(int)(Math.random()*45+1);
			arr[i] = random;			
			
			for(int x=0; x<i; x++) {
				if(arr[x] == random) {
				i--;
				break;
				}
			}
		}
		Arrays.sort(arr);
		for(int j=0; j<arr.length; j++) {
			System.out.print(arr[j]+" ");
		}
		
	}
	
	public void practice13() {
		// 메소드명을 입력 받아 문자열에 어떤 문자가 들어갔는지 배열에 저장하고
		// 문자의 개수와 함께 출력하세요.
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열 : ");
		char[] input = sc.nextLine().toCharArray();
		
		System.out.print("문자열에 있는 문자 : ");
		for(int i=0; i<input.length; i++) {
			
			if(i==input.length-1) {
				System.out.print(input[i]);
			} else {
				System.out.print(input[i]+", ");
			}
		}
		
		System.out.println("\n문자 개수 : " + input.length);
	
	*/
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("문자열 : ");
		String input = sc.nextLine();
		
		char[] arr = new char[input.length()];
		int count = 0; // 카운트용 변수
		
		System.out.print("문자열에 있는 문자 : ");
		for(int i=0; i<arr.length; i++) {
			arr[i] = input.charAt(i); // 문자열을 -> char 배열에 옮겨담기
			
			// 중복 검사 + flag
			// application
			// 배열 : [a, p, p, l, i, c, a, t, i, o, n]
			// 화면 : a, p, l, i, c, t, o, n
			
			boolean flag = true; // 신호용 변수
			
			for(int x=0; x<i; x++) { // 중복 검사용 for문
				if(arr[i] == arr[x]) {
					// 현재 대입된 문자가 앞서 대입된 문자와 같다면 == 중복
					flag = false; // 신호용 변수의 값을 false로 변경
					break;
				}
			}
			
			if(flag) { // flag가 true인 경우 == 중복이 없었다라는 의미
				count++; // 카운트 1 증가
				
				if(i == 0) { // 첫 바퀴인 경우
					System.out.print(arr[i]);
				} else { // 첫 바퀴가 아닌 경우
					System.out.print(", "+arr[i]);
				}
			}
		} // 바깥쪽 for문 끝
		System.out.println("\n문자 개수 : " + count);
	
	}

}
