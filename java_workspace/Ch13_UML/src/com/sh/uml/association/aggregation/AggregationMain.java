package com.sh.uml.association.aggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Aggregation 집약관계
 * - 생명주기가 독립적인 연간 관계
 * - 속이 빈 다이아몬드로 표시함
 * 
 * Book ----<> BookInfo
 * - Book은 BookInfo를 참조하지만, 생명주기는 결정하지 않는다.
 * - Book이 삭제되어도, BookInfo는 삭제되지 않는다.
 * - Book은 BookInfo를 외부에서 주입 받는다.
 */
public class AggregationMain {
	public static void main(String[] args) {
		BookInfo bookInfo = new BookInfo("자바의 정석", "홍길동", "민음사");
		List<Book> books = new ArrayList<>();
		
		books.add(new Book(bookInfo, 1, false));
		books.add(new Book(bookInfo, 2, false));
		books.add(new Book(bookInfo, 3, false));
		
		books.remove(2); // book 객체를 삭제해도 bookInfo는 삭제되지 않음
		books.remove(1);
		books.remove(0);
		
		for(Book book : books)
			System.out.println(book);
			
	
		System.out.println(bookInfo);
	}
}
