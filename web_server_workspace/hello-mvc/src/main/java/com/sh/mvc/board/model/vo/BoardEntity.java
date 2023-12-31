package com.sh.mvc.board.model.vo;

import java.sql.Date;

public class BoardEntity {
	private int no;
	private String title;
	private String writer;
	private Date regDate;
//	private int file; // 테이블과 완전히 똑같은 vo를 만들고 추가 사항은 자식 클래스로 만드는 것이 좋음
	private int readCount;
	private String content;
	
	
	// 기본생성자
	public BoardEntity() { super(); }
	
	// 매개변수 생성자
	public BoardEntity(int no, String title, String writer, Date regDate, int file, int readCount, String content) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.regDate = regDate;
//		this.file = file;
		this.readCount = readCount;
		this.content = content;
	}
	

	// getter / setter
	public int getNo() {
		return no;
	}


//	public int getFile() {
//		return file;
//	}
//
//	public void setFile(int file) {
//		this.file = file;
//	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", readCount="
				+ readCount + ", regDate=" + regDate + "]";
	}
	
	
}
