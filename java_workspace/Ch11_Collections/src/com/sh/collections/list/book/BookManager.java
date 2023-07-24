package com.sh.collections.list.book;
import java.util.*;

/**
 * 책 데이터 관리 List<Book>
 * - 책 추가/수정/삭제/정렬 기능
 */
public class BookManager {
	
	private Scanner sc = new Scanner(System.in);
	
	public static final int CATEGORY_인문 = 1;
	public static final int CATEGORY_자연과학 = 2;
	public static final int CATEGORY_의료 = 3;
	public static final int CATEGORY_기타 = 4;
	
	private List<Book> books = new ArrayList<>();
	// 초기화블럭
	{
		// 정수기입보다 카테고리 상수를 사용하면 가독성을 좋게할 수 있다
        books.add(new Book("200", CATEGORY_자연과학, "나미야 잡화점의 기적", "히가시노 게이고"));
        books.add(new Book("100", CATEGORY_의료, "파리의 아파트", "기욤 뮈소"));
        books.add(new Book("400", CATEGORY_인문, "ABCDE", "도레미"));
        books.add(new Book("300", CATEGORY_자연과학, "미중전쟁", "김진명"));
        books.add(new Book("500", CATEGORY_인문, "JAVA 삽질하기", "김동현"));
	}
	
	
	public void printBook(List<Book> books) {
		if(books == null)
			books = this.books;
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

	/**
	 * Object#clone : 객체를 복제
	 * - 복제하고자 하는 클래스에서 오버라이딩 이후 사용할 수 있다 (protected -> public)
	 * - 반환타입이 Object이므로 downcasting후 사용할 것
	 */
	public void sortBook(Comparator<Book> comparator) {
		// List의 순서를 보존하기 위하여 (List는 저장 순서가 중요하니깐)
		// 원본을 copy를 하여 그 카피본에 정렬 방법을 적용함
		ArrayList<Book> _books = (ArrayList<Book>) this.books; // ArrayList#clone을 사용하기 위한 다운캐스팅
		List<Book> copy = (List<Book>) _books.clone();
		copy.sort(comparator); // null 이면 기본정렬,
								// comparator가 존재한다면 기타정렬
		printBook(copy);
	}
	
	

	// 해당 책 번호로 책 삭제
	public void deleteBook() {
		System.out.print("삭제할 책의 번호를 입력해주세요 : ");
		String no = sc.next();
		
		Book delBook = null;
		// List는 반복문 밖에서 remove하는 것이 좋음
		for(int i=0; i<books.size(); i++) {
			Book book = books.get(i);
			if(book.getNo().equals(no)) {
				delBook = book;
				break;
			} 
		}
		
		if(delBook == null) {
			System.out.println("> 해당 책이 존재하지 않습니다...");
		} else {
			books.remove(delBook); // 인덱스 또는 해당 요소로 삭제
			System.out.println("> 해당 책을 삭제했습니다...");
		}
		
	}
	
	// 사용자의 책 제목 검색어와 일치하는 책 목록 출력 (일부 일치해도 나오도록)
	// 사용자 검색 제목 입력 -> books에 조회 -> 결과출력
	public void searchBook() {
		// n개의 검색결과를 담을 수 있는 List 생성
		List<Book> results = new ArrayList<>();

		System.out.println("> 제목 검색어를 입력하세요...");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		
		boolean flag = true;
		for(int i=0; i<books.size(); i++) {
			Book book = books.get(i);
			if((book.getTitle()).contains(title)) {
		//  if(book.getTile().indexOf(title) > -1 ) {
				results.add(book);
			} 
		}
		printBook(results);
		
		
	}

	// 사용자 책 정보를 하나하나 받은 다음에 객체 만들어서 BookManager#books에 추가
	// 사용자 책 정보 입력 -> Book 객체 -> books 추가
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
		
		books.add(new Book(no, category, title, author));
		System.out.println("책을 저장했습니다.");
		
	}

}
