package com.sh.exception.charcheck;

import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		
		new Run().test1();
		System.out.println("--- 프로그램이 정상 종료되었습니다. ---");
	}
	
	/**
	 * - 사용자 입력값을 받기
	 * - CharacterProcess#countAlpha 메소드 호출, 사용자 입력값 전달
	 * - 알파벳 갯수를 출력
	 */
	public void test1() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("아무 문자열이나 입력하세요...");
		String str = sc.nextLine();
		System.out.println(str);
		
		try {
			// 예외가 발생
			CharacterProcess characterProcess = new CharacterProcess();
			int count = characterProcess.countAlpha(str);
			System.out.printf("입력하신 문자열 [%s]에서 알파벳의 개수는 %d개 입니다.\n", str, count);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
}
