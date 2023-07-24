package com.sh.uml.association.aggregation;

public class BookInfo {
	private String title;
	private String author; // 저자
	private String publisher; // 출판사
	
	public BookInfo(String title, String author, String publisher) {
		super();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	public BookInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookInfo [title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
	}
	
}
