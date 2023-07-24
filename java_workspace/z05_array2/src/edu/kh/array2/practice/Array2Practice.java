package edu.kh.array2.practice;

import java.util.Arrays;
import java.util.Scanner;

public class Array2Practice {
	
	// 3행 3열짜리 문자열 배열을 선언 및 할당하고
	// 인덱스 0행 0열부터 2행 2열까지 차례대로 접근하여
	// "(0,0)"과 같은 형식으로 저장 후 출력하세요.
	
	public void practice1() {
		
		String[][] arr = new String[3][3];
		
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr[row].length; col++) {
				
				arr[row][col] = "("+row+", " + col +")";
				System.out.print(arr[row][col]);
			}
			System.out.println(); // 줄바꿈
		}
	}
	
	public void practice2() {
		
		// 4행 4열짜리 정수형 배열을 선언 및 할당하고
		// 1) 1 ~ 16까지 값을 차례대로 저장하세요
		// 2) 저장된 값들을 차례대로 출력하세요
		
		int[][] arr = new int[4][4];
		
		int i = 1;
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr[row].length; col++) {
				arr[row][col] = i++;
				
				System.out.printf("%3d",arr[row][col]);
			}
			System.out.println();
		}
	}
	
	public void practice3() {
		
		int[][] arr = new int[4][4];

		int i = 16;
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr[row].length; col++) {
				arr[row][col] = i--;
				
				System.out.printf("%3d", arr[row][col]);
			}
			System.out.println();
		}
	}
	
	public void practice4() {
		//4행 4열 2차원 배열을 생성하여 0행 0열부터 2행 2열까지는 1~10까지의 임의의 정수
		//값 저장 후 아래의 내용처럼 처리하세요.
		
		/*[실행 화면]
		 *   9  3  7 19
		 *   3  6  9 18
		 *   6 10 10 26
		 *  18 19 26 63
		 */
		
		int[][] arr = new int[4][4];
		
		// 상수 사용법 : 변하지 않는 특정 값에 이름을 붙여줌
		final int ROW_LAST_INDEX = arr.length-1; // 행 마지막 인덱스
		final int COL_LAST_INDEX = arr[0].length-1; // 열 마지막 인덱스
		
		for(int row=0; row<arr.length; row++) { // 행 반복
							// 행의 길이
			for(int col=0; col<arr[row].length; col++) { // 열 반복
							// row번째 행의 열의 길이
				
				// 마지막 행, 마지막 열이 아닌 경우
				if(row != ROW_LAST_INDEX && col != COL_LAST_INDEX) {
					int random = (int)(Math.random()*10+1); // 1~10 난수
					arr[row][col] = random;
					
					// 각 행의 마지막 열에 난수를 누적
					arr[row][COL_LAST_INDEX] += arr[row][col];
					
					// 각 열의 마지막 행에 난수를 누적
					arr[ROW_LAST_INDEX][col] += arr[row][col];
					
					// 생성된 모든 난수를 마지막 행, 마지막 열에 누적
					arr[ROW_LAST_INDEX][COL_LAST_INDEX] += arr[row][col];
				}
				
				System.out.printf("%4d", arr[row][col]);
			} // 열 반복 끝
			
			System.out.println();
			
		} // 행 반복 끝
		
		/* 내가 쓴 코드 
		int[][] arr = new int[4][4];
		int sum1 = 0;
		
		for(int row=0; row<arr.length-1; row++) {
			for(int col=0; col<arr[row].length-1; col++) {
				int random = (int)(Math.random()*10+1);
				arr[row][col] = random;
				
				arr[row][3] += arr[row][col];
				arr[3][col] += arr[row][col];
				arr[3][3] += arr[row][col];
				}
			}							
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				
				
				System.out.printf("%3d ",arr[i][j]);
			}
			System.out.println();
		}
		*/
		
	}
	
	public void practice5() {
		// 2차원 배열의 행과 열의 크기를 사용자에게 직접 입력받되, 1~10숫자가 아니면 
		// "반드시 1~10사이의 정수를 입력해야 합니다." 출력 후 다시 정수를 받게 하세요.
		// 크기가 정해진 이차원 배열 안에는 영어 대문자가 랜덤으로 들어가게 한 뒤 출력하세요.
		// (char형은 숫자를 더해서 문자를 표현할 수 있고 65는 A를 나타냄, 알파벳은 총 26글자)
		
		/*[실행 화면]
		 * 행 크기 : 5
		 * 열 크기 : 4
		 * T P M B
		 * U I H S
		 * Q M B H
		 * H B I X
		 * G F X I
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("행 크기 : ");
			int row = sc.nextInt();
			
			System.out.print("열 크기 : ");
			int col = sc.nextInt();
			
			if( (row<1 || row>10) || (col<1 || col>10) ) { // 1~10사이의 숫자가 아닌 경우
				System.out.println("반드시 1~10 사이의 정수를 입력해야 합니다.");
				continue;
			} else {
				int[][] arr = new int[row][col];
				
				for (int i=0; i<row; i++) {
					for(int j=0; j<col; j++) {
						arr[i][j] = (int)(Math.random()*26+1);
						
						System.out.printf("%c ",arr[i][j]+64);
					}
					System.out.println();
				} // for  문 끝
				break;
			}
		}
	}
	
	public void practice6() {
		// 사용자에게 행의 크기를 입력 받고 그 수 만큼의 반복을 통해 열의 크기도 받아
		// 문자형 가변 배열을 선언 및 할당하세요.
		// 그리고 각 인덱스에 'a'부터 총 인덱스의 개수만큼 하나씩 늘려 저장하고 출력하세요.
		
		/*[실행 화면]
		 * 행의 크기 : 4
		 * 0열의 크기 : 2
		 * 1열의 크기 : 6
		 * 2열의 크기 : 3
		 * 3열의 크기 : 5
		 * a  b
		 * c  d  e  f  g  h
		 * i  j  k
		 * l  m  n  o  p
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("행 크기 : ");
		int row = sc.nextInt();
		
		char[][] arr = new char[row][];
		
		for(int i=0; i<row; i++) {
			System.out.print(i + "열의 크기 :");
			int col = sc.nextInt();
			arr[i] = new char[col];
		}
		
		char ch = 'a';
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = ch++;
				
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void practice7() {
		// 1차원 문자열 배열에 학생 이름 초기화되어있다.
		// 3행 2열짜리 2차원 문자열 배열 2개를 새로 선언 및 할당하여
		// 학생 이름을 2차원 배열에 순서대로 저장하고 아래와 같이 출력하시오.
		// (첫 번째 2차원 배열이 모두 저장된 경우 두 번째 2차원 배열에 저장 진행)
		
		/*[실행화면]
		 * == 1분단 ==
		 * 강건강 남나나
		 * 도대담 류라라
		 * 문미미 박보배
		 * 
		 * == 2분단 ==
		 * 송성실 윤예의
		 * 진재주 차천축
		 * 피퐁표 홍하하
		 * */
		
		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배",
				"송성실", "윤예의", "진재주", "차천축", "피퐁표", "흥하라" };
		
		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];
		
		int num = 0;
		System.out.println("== 1분단 ==");
		for (int i=0; i<arr1.length; i++) {
			for(int j=0; j<arr1[i].length; j++) {
				arr1[i][j] = students[num];
				num++;
				System.out.print(arr1[i][j]+ " ");
			}
			System.out.println();
		}

		System.out.println("== 2분단 ==");
		for (int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				arr2[i][j] = students[num];
				num++;
				System.out.print(arr2[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	public void practice8() {
		// 위 문제에서 자리 배치한 것을 가지고 학생 이름을 검색하여
		// 해당 학생이 어느 자리에 앉았는지 출력하세요.
		      
		/* ================
		 * 검색할 학생 이름을 입력하세요 : 차천축
		 * 검색하신 차천축 학생은 2분단 2번째 줄 오른쪽에 있습니다.
		 */
		 
		
		String[] students = {"강건강", "남나나", "도대담", "류라라", "문미미", "박보배",
		            "송성실", "윤예의", "진재주", "차천축", "피퐁표", "흥하라" };
		      
		String[][] arr1 = new String[3][2];
		String[][] arr2 = new String[3][2];
		      
		int num = 0;
		System.out.println("== 1분단 ==");
		for (int i=0; i<arr1.length; i++) {
			for(int j=0; j<arr1[i].length; j++) {
				arr1[i][j] = students[num];
				num++;
				System.out.print(arr1[i][j]+ " ");
			}
			System.out.println();
			}

		System.out.println("== 2분단 ==");
		for (int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				arr2[i][j] = students[num];
				num++;
				System.out.print(arr2[i][j]+ " ");
			}
			System.out.println();
		}
		System.out.println("====================================");
		      
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 학생 이름을 입력하세요 : ");
		String name = sc.nextLine();
		      
		for (int i=0; i<arr1.length; i++) {
			for(int j=0; j<arr1[i].length; j++) {
				if (arr1[i][j].equals(name)) {
					if(j==0) {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 왼쪽에 있습니다.", name,i+1);
					} else {
						System.out.printf("검색하신 %s 학생은 1분단 %d번째 줄 오른쪽에 있습니다.", name,i+1);
					}
				}
			}
		}
		      
		for (int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++) {
				if (arr2[i][j].equals(name)) {
					if(j==0) {
						System.out.printf("검색하신 %s 학생은 2분단 %d번째 줄 왼쪽에 있습니다.", name,i+1);
					} else {
						System.out.printf("검색하신 %s 학생은 2분단 %d번째 줄 오른쪽에 있습니다.", name,i+1);
					}
				}
			}
		}  
	}
	
	public void practice9() {
		// String 2차원 배열 6행 6열을 만들고 행의 맨 위와 제일 앞 열은 각 인덱스를 저장하세요
		// 그리고 사용자에게 행과 열을 입력받아 해당 좌표의 값을 "X"로 변환해 2차원 배열을 출력하세요.
		
		/*[실행 화면]
		 * 행 인덱스 입력 : 4
		 * 열 인덱스 입력 : 2
		 *   0 1 2 3 4
		 * 0
		 * 1
		 * 2
		 * 3
		 * 4     X
		 * */
		
		/* 내가 푼 코드
	      String[][] arr = new String[6][6];
	      
	      Scanner sc = new Scanner(System.in);
	      System.out.print("행 입력 : ");
	      int inputrow = sc.nextInt();
	      
	      System.out.print("열 입력 : ");
	      int inputcol = sc.nextInt();
	      
	      int i = 0;
	      int j = 0;
	      for(int row=0; row<arr.length; row++) {
	         for(int col=0; col<arr[row].length; col++) {
	            
	            if(row==0 && col==0) {
	               arr[row][col] = " ";
	               
	            } else if(row==inputrow+1 && col==inputcol+1) {
	               arr[row][col] = "X";
	               
	            } else if(row==0 && col!=0) {
	               arr[row][col] = (i++)+" ";
	               
	            } else if (col==0 && row != 0) {
	               arr[row][col] = (j++)+"";
	               
	            } else {
	               arr[row][col] = "  ";
	            }
	         }
	      }
	      
	      for(int row=0; row<arr.length; row++) {
	         for(int col=0; col<arr[row].length; col++) {
	            System.out.print(arr[row][col]);
	         }
	         System.out.println();
	      }
	      
	      */
		
		Scanner sc = new Scanner(System.in);
		
		String[][] arr = new String[6][6];
		
		System.out.print("행 인덱스 입력 : ");
		int rowIndex = sc.nextInt();
		
		System.out.print("열 인덱스 입력 : ");
		int colIndex = sc.nextInt();
		
		int num1 = 0;
		int num2 = 0;
		
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr.length; col++) {
				if(row == 0 && col != 0) {
					arr[row][col] = num1++ + " ";
					
				} else if (col == 0 && row != 0) {
					arr[row][col] = num2++ + " ";
				} else {
					arr[row][col] = "  ";
				}
				arr[rowIndex+1][colIndex+1] = "X";
				System.out.print(arr[row][col]);
			}
			System.out.println();
		}
		
		
	}
	
	public void practice10() {
		// 실습문제9와 내용은 같으나 행 입력 시 99가 입력되지 않으면 무한 반복이 되도록 구현하세요
		/*[실행 화면]
		 * 행 인덱스 입력 : 2
		 * 열 인덱스 입력 : 2
		 *   0 1 2 3 4
		 * 0
		 * 1
		 * 2     X
		 * 3
		 * 4 
		 *     
		 * 행 인덱스 입력 : 3
		 * 열 인덱스 입력 : 1
		 *   0 1 2 3 4
		 * 0
		 * 1       
		 * 2     X
		 * 3   X
		 * 4     
		 * 
		 * 행 인덱스 입력 >> 99
		 * 
		 * 프로그램 종료
		 * */
		
		/* 내가 푼 코드
	      String[][] arr = new String[6][6];
	      Scanner sc = new Scanner(System.in);
	      
	      int i = 0;
	      int j = 0;
	      for(int row=0; row<arr.length; row++) {
	         for(int col=0; col<arr[row].length; col++) {
	            
	            if(row==0 && col==0) {
	               arr[row][col] = " ";
	               
	            } else if(row==0 && col!=0) {
	               arr[row][col] = (i++)+" ";
	               
	            } else if (col==0 && row != 0) {
	               arr[row][col] = (j++)+"";
	               
	            } else {
	               arr[row][col] = "  ";
	            }
	         }
	      }
	      
	      while(true) {
	         
	         System.out.print("행 입력 : ");
	         int inputrow = sc.nextInt();
	         
	         if(inputrow==99) {
	            System.out.println("프로그램 종료");
	            break;
	         }
	         
	         System.out.print("열 입력 : ");
	         int inputcol = sc.nextInt();
	         
	         arr[inputrow+1][inputcol+1] = "X";
	         for(int row=0; row<arr.length; row++) {
	            for(int col=0; col<arr[row].length; col++) {
	               System.out.print(arr[row][col]);
	            }
	            System.out.println();
	         } // for문 끝
	      }
		*/
		
		Scanner sc = new Scanner(System.in);
		
		String[][] arr = new String[6][6];
		
		int num1 = 0;
		int num2 = 0;
		
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr.length; col++) {
				if(row == 0 && col != 0) {
					arr[row][col] = num1++ + " ";
					
				} else if (col == 0 && row != 0) {
					arr[row][col] = num2++ + " ";
				} else {
					arr[row][col] = "  ";
				}
			}
		}
		
		boolean flag = true;
		
		System.out.print("행 인덱스 입력 : ");
		int rowIndex = sc.nextInt();

		do {
			System.out.print("열 인덱스 입력 : ");
			int colIndex = sc.nextInt();
			
			arr[rowIndex+1][colIndex+1] = "X";
			
			for(int row=0; row<arr.length; row++) {
				for(int col=0; col<arr.length; col++) {
					System.out.print(arr[row][col]);
				}
				System.out.println();
			}
			
			System.out.print("\n행 인덱스 입력 >> ");
			int input = sc.nextInt();
			
			if(input == 99) {
				System.out.println("\n프로그램 종료");
				flag = false;
			}
		} while(flag);
		
	}
	
	// 빙고판 크기를 입력 받아, 그 크기만큼의 행과 열을 가지는 2차원 배열(빙고판) 생성
	// 1부터 크기*크기 사이의 정수 난수를 무작위 배치
	
	// 정수를 입력받아 빙고판에서 일치하는 부분을 찾아 해당 부분의 숫자를 "★"로 변경하고
	// 현재 빙고 카운트가 몇인지 출력
	// 단, 빙고판에 없는 정수를 입력한 경우 "다시 입력해주세요" 출력
	
	// 가로, 세로, 대각선 한 줄이 모두 "★"로 변경되어 있을 경우 빙고 카운트 1 증가
	// 빙고 카운트 3 이상이 되면 "***Bingo!***" 출력하고 프로그램 종료
	
	/*[프로그램 실행]
	 * 빙고판 크기 지정 : 5
	 * 13  7   21  19  4
	 * 22  6   2   8  14
	 * 25  9  20  12   1
	 * 17  3  18  24  11
	 * 15  5  10  23  16
	 * ============빙고게임 시작==========
	 * 정수를 입력하시오 : 13
	 *  ★  7   21  19  4
	 * 22  6   2   8  14
	 * 25  9  20  12   1
	 * 17  3  18  24  11
	 * 15  5  10  23  16
	 * 현재 0빙고
	 * 
	 * 정수를 입력하시오 : 
	 * 
	 *  ★   ★   ★   ★   ★
	 *  ★   ★   2   8   14
	 *  ★   9   ★   12   1
	 *  ★   3  18    ★  11
	 *  ★   5  10   23   ★
	 * 현재 3빙고
	 * 
	 * ***Bingo!***
	 * */
	
	public void bingoGame() {
		
		/* 내가 푼 코드
		Scanner sc = new Scanner(System.in);
		      
		System.out.print("빙고판 크기 지정 : ");
		int input = sc.nextInt();
		String[] arr1 = new String[input*input];
		String[][] arr2 = new String[input][input];
		      
		for(int i=0; i<arr1.length; i++) {
			String random = (int)(Math.random()*arr1.length+1)+"";
			arr1[i] = random;
		         
			for(int x=0; x<i; x++) {
				if(arr1[x].equals(random)) {
					i--;
					break;
				}
			}
		} // 1차원 배열에 랜덤값 할당
		int num = 0; // arr1의 인덱스
		for(int row=0; row<arr2.length; row++) {
			for(int col=0; col<arr2.length; col++) {
				arr2[row][col] = arr1[num];
				num++;
		            
				System.out.printf("%3s", arr2[row][col]);
			}
			System.out.println();
		}

		System.out.println("================= 빙고게임 시작 =================");
		      
		int count = 0;
		while(true) {
			System.out.print("정수를 입력하시오 : ");
			String inputNum = sc.next();
		         
			for(int row=0; row<arr2.length; row++) {
				for(int col=0; col<arr2.length; col++) {
		               
					if(inputNum.equals(arr2[row][col])) {
						arr2[row][col] = "★";
					}
					System.out.printf("%3s",arr2[row][col]);
				}
				System.out.println();
			}
			
			System.out.println("현재 " + count + "빙고");

		}
		*/
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("빙고판 크기 지정 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		// 1차원 배열로 빙고판에 입력될 값 생성 + 중복제거 진행
		int[] tempArr = new int[num*num]; // 1차원 배열 생성
		// num * num --> 빙고판의 크기는 가로 세로의 곱 만큼의 공간이 필요함
		
		// 중복값을 제거하면서 랜덤 값 집어넣기
		for(int i=0; i<tempArr.length; i++) {
			tempArr[i] = (int)(Math.random()*(num*num))+1; 
						// tempArr[0 ~ tempArr.length-1]에 각각 랜덤 값 생성
			// 중복 제거
			for(int j=0; j<i; j++) {
				if(tempArr[i] == tempArr[j]) {
					i--;
					break;
				}
			}
		}
		
		// 위에서 만들어진 중복 제거한 1차원 배열을 --> 2차원 배열에 넣기
		// String 배열로 변경해서 대입 진행
		// 왜? String 배열로? 빙고가 된 부분을 "★"로 변경하기 위해서
		String[][] bingoBoard = new String[num][num];
		
		int index = 0; // 1차원 배열 인덱스 지정을 위한 변수
		
		for(int i=0; i<num; i++) {
			for(int j=0; j<num; j++) {
				// 1차원 배열은 0번부터 num*num까지 반복 진행
				
				bingoBoard[i][j] = tempArr[index]+"";
				index++;
			}
		}
		
		// --------------------------------------------------------------
		
		// 랜덤 배치된 빙고판 최초 1회 출력
		for(int i=0; i<bingoBoard.length; i++) {
			for(int j=0; j<bingoBoard.length; j++) {
				System.out.printf("%4s", bingoBoard[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("=============== 빙고게임 시작 ===============");
		
		while(true) {
			System.out.print("정수를 입력하시오 : ");
			String input = sc.nextLine();
			
			boolean flag = true; // 검색된 값이 빙고판에 있는지 확인 (잘못된 입력 확인)
			
			for(int i=0; i<bingoBoard.length; i++) {
				for(int j=0; j<bingoBoard.length; j++) {
					
					// 입력된 값과 일치되는 곳을 ★형태로 변환
					if(bingoBoard[i][j].equals(input)) {
						bingoBoard[i][j] = "★";
						flag = false;
					} 
					System.out.printf("%4s",bingoBoard[i][j]);
				}
				System.out.println();
			}
			
			if(flag) {
				System.out.print("잘못 입력하셨습니다. 다시 입력하세요. ");
				continue;
			}
			
			// 빙고판 크기에 따라
			// 빙고 기준이 되는 문자열 생성
			// ex) 5 * 5 크기 빙고 --> 한 줄이 "★★★★★"이면 빙고
			
			String bingoLine = "";
			
			for(int i=0; i<num; i++) {
				bingoLine += "★";
			}
			
			// 빙고 검사
			int bingoCount = 0; // 빙고 수를 저장할 변수
			
			// 가로(행) 또는 세로(열)의 문자열을 더하여 하나의 문자열로 저장
			// --> 저장된 만주열의 모양이 "★★★★★"인 경우 == 빙고
			// --> bingoCount 증가
			
			String row = null; // 가로(행)을 모두 더한 문자열을 참조할 변수 선언
			String col = null; // 세로(행)을 모두 더한 문자열을 참조할 변수 선언
			
			for(int i=0; i<bingoBoard.length; i++) {
				// 매 반복 시 마다 row, col 빈 문자열로 초기화
				// --> 바깥쪽 for문이 반복될 때 마다 검사하는 행과 열이 이동하므로
				//     빙고 여부를 검사하기 위한 row, col을 빈 문자열로 초기화 해야함
				row = "";
				col = "";
				
				for(int j=0; j<bingoBoard.length; j++) {
					row += bingoBoard[i][j];
					
					// i,j(행렬)를 반대로 하여 열의 모든 문자를 더함
					col += bingoBoard[j][i];
					
				}
				
				if(row.equals(bingoLine)) {
					bingoCount++; // 가로 빙고가 존재 할 경우
				}
				
				if(col.equals(bingoLine)) {
					bingoCount++; // 세로 빙고가 존재 할 경우
				}
			}
			
			// 대각선 빙고 여부
			// 대각선 : diagonal(다이애그널)
			
			// 대각선은 빙고판에 두 개만 존재
			// --> 대각선 문자를 더해서 저장할 변수 두 개 선언 및 빈 문자열로 초기화
			String dial1 = "";
			String dial2 = "";
			
			// 대각선은 for문 하나로 가능
			for(int i=0; i<bingoBoard.length; i++) {
				dial1 += bingoBoard[i][i]; // 좌상 우하
				dial2 += bingoBoard[bingoBoard.length-1-i][i]; // 우상 좌하
			}
			
			if(dial1.equals(bingoLine)) {
				bingoCount++;
			}
			
			if(dial2.equals(bingoLine)) {
				bingoCount++;
			}
			
			// 빙고 카운트 출력
			System.out.println("현재 "+ bingoCount + "빙고");
			
			if(bingoCount >= 3) { // 빙고 개수가 3개 이상인 경우
				System.out.println("***** BINGO!!! *****");
				break;
			}
			System.out.println();
			System.out.println();
		}
	}



}
