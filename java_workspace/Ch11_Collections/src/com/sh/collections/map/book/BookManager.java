package com.sh.collections.map.book;

import java.nio.file.spi.FileSystemProvider;
import java.util.*;

import com.sh.collections.list.book.Book;

public class BookManager {
	
	private Scanner sc = new Scanner(System.in);
	
	public static final int CATEGORY_인문 = 1;
	public static final int CATEGORY_자연과학 = 2;
	public static final int CATEGORY_의료 = 3;
	public static final int CATEGORY_기타 = 4;
	
	private Map<String, Book> bookMap = new HashMap<>();
	
	// 초기화블럭
	{
		// 정수기입보다 카테고리 상수를 사용하면 가독성을 좋게할 수 있다
        bookMap.put("200", new Book("200", CATEGORY_자연과학, "나미야 잡화점의 기적", "히가시노 게이고"));
        bookMap.put("100", new Book("100", CATEGORY_의료, "파리의 아파트", "기욤 뮈소"));
        bookMap.put("400", new Book("400", CATEGORY_인문, "ABCDE", "도레미"));
        bookMap.put("300", new Book("300", CATEGORY_자연과학, "미중전쟁", "김진명"));
        bookMap.put("500", new Book("500", CATEGORY_인문, "JAVA 삽질하기", "김동현"));
	}


	public void addBook() {
		System.out.println("> 책 정보를 입력하세요...");
		System.out.print("번호 : ");
		String no = sc.nextLine();
		
		System.out.print("카테고리 (1.인문 2.자연과학 3.의료 4.기타) : ");
		int category = sc.nextInt();
		
		sc.nextLine(); // 입력버퍼 개행문자 비워주기
		
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		System.out.print("저자 : ");
		String author = sc.nextLine();
		
		Book book = new Book(no, category, title, author);
		bookMap.put(book.getNo(), book);
		System.out.println("> 책을 저장했습니다...");
		
		
	}

	public void searchBook() {
		// n개의 검색 결과를 담을 수 있는 Map 생성
		Map<String, Book> resultMap = new HashMap<>();
		
		System.out.println("> 제목 검색어를 입력하세요...");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		Collection<Book> books = bookMap.values(); // value만 가져올 수 있음
		for(Book book : books) {
			if(book.getTitle().contains(title)) {
				resultMap.put(book.getNo(), book);
			}
		}
		printBook(resultMap);
		
	}

	public void deleteBook() {
		System.out.println("> 삭제할 책번호를 입력하세요...");
		String no = sc.next();
		
		Book removed = bookMap.remove(no); // 삭제되는 book을 반환함
		System.out.println("++++++ removed : " + removed);
		if(removed == null) {
			System.out.println("> 해당 도서가 존재하지 않습니다.");
			
		} else {
			System.out.println("> 해당 도서를 삭제했습니다.");
		}
		
	}

	public void printBook(Map<String, Book> bookMap) {
		if(bookMap == null)
			bookMap = this.bookMap;
		
		if(bookMap.isEmpty()) {
			System.out.println("---------------------------------------------------------");
			System.out.println("관리하는 도서가 존재하지 않습니다.");
			System.out.println("---------------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------------");
			System.out.println("no\tcategoty\ttile\t\tauthor");
			System.out.println("---------------------------------------------------------");
			
			Set<String> keySet = bookMap.keySet();
			for(String no : keySet) {
				Book book = bookMap.get(no);
				System.out.printf("%s\t%s\t%s\t\t\t%s\n",book.getNo(), 
						book.getCategory(), book.getTitle(), book.getAuthor() );
			}
			System.out.println("---------------------------------------------------------");
		}
		
	}
	
	public void printBookList(List<Book> books) {

		if(books.isEmpty()) {
			System.out.println("---------------------------------------------------------");
			System.out.println("관리하는 도서가 존재하지 않습니다.");
			System.out.println("---------------------------------------------------------");
		} else {
			System.out.println("---------------------------------------------------------");
			System.out.println("no\tcategoty\ttile\t\tauthor");
			System.out.println("---------------------------------------------------------");
			
			for(Book book : books) {
				System.out.printf("%s\t%s\t%s\t\t\t%s\n",book.getNo(), 
						book.getCategory(), book.getTitle(), book.getAuthor() );
			}
			System.out.println("---------------------------------------------------------");
		}
		
	}

	public void sortBook(Comparator<Book> comparator) {
		Collection<Book> books = this.bookMap.values();
		List<Book> _books = new ArrayList<>(books);
		_books.sort(comparator);
		printBookList(_books);
		
	}
	
}