package com.practice.member.model.vo;

import java.sql.Date;

public class Product {
	private int number;
	private String prodName;
	private String prodImg;
	private Date createdAt;
	
	public Product() {} // 기본생성자

	public Product(int number, String prodName, String prodImg, Date createdAt) {
		super();
		this.number = number;
		this.prodName = prodName;
		this.prodImg = prodImg;
		this.createdAt = createdAt;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
