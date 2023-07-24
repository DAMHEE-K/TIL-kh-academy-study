package com.sh.collections.map.book;

import java.util.Collections;
import java.util.Scanner;

import com.sh.collections.list.book.BookAuthorAsc;

/**
 * 실행메소드(main)
 * 사용자 메뉴 노출
 */
public class BookMain {

	private Scanner sc = new Scanner(System.in);
	private BookManager bookManager = new BookManager();
	
	public static void main(String[] args) {
		new BookMain().start();
		System.out.println("----- 이용해주셔서 감사합니다. ----");
	}

	private void start() {
		String menu = "===========================\n"
				+ "1.도서추가\n"
				+ "2.도서검색(제목)\n"
				+ "3.도서삭제\n"
				+ "4.도서 정렬 후 조회\n"
				+ "5.도서 전체 조회\n"
				+ "0. 끝내기\n"
				+ "===========================\n"
				+ "입력 : ";
	
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			switch(choice) {
			case "1": bookManager.addBook(); break;
			case "2": bookManager.searchBook(); break;
			case "3": bookManager.deleteBook(); break;
			case "4": sortBook(); break;
			case "5": bookManager.printBook(null); break;
			case "0": return;
			default: System.out.println("잘못 입력하셨습니다.\n");
			}
		}
	}
	
	private void sortBook() {
		String sortMenu = "--------------------------\n"
				+ "1.책 제목 오름차순\n"
				+ "2.책 제목 내림차순\n"
				+ "3.저자 오름차순\n"
				+ "4.저자 내림차순\n"
				+ "0.메인 메뉴로 돌아가기\n"
				+ "--------------------------\n"
				+ "입력 : ";
		
		while(true) {
			System.out.print(sortMenu);
			String choice = sc.next();
			
			switch(choice) {
			case "1" : bookManager.sortBook(null); break; // 기본정렬
			case "2" : bookManager.sortBook(Collections.reverseOrder()); break;
			case "3" : bookManager.sortBook(new BookAuthorAsc()); break;
			case "4" : bookManager.sortBook(Collections.reverseOrder(new BookAuthorAsc())); break;
			case "0" : return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

}
