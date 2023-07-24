package p01.basic2;

// class에서는 field 초기화를 거의 하지 않는 것이 원칙임
// - 생성자(constructor)와 setter method를 통해서 field값을 초기화하거나 변경
// - 단, field가 상수인 경우와 같이 변하지 않는 경우에는 초기화

public class Book {
	String title;
	String author;
	
	public Book(String title) {
		super();
		this.title = title;
		this.author = "작가 미상";
	}
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}
	
	public void printBookInformation() {
		System.out.println("책 제목 : " + this.title + ",  작가 : " + this.author);
	}
	

}
