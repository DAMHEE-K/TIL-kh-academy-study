package com.sh.collections.map.book;

public class Book {
	
	private String no;
	private int category;
	private String title;
	private String author;
	
	public Book() { // 기본 생성자
		super();
	}

	// 매개변수 생성자
	public Book(String no, int category, String title, String author) {
		super();
		this.no = no;
		this.category = category;
		this.title = title;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [no=" + no + ", category=" + category + ", title=" + title + ", author=" + author + "]";
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	
}