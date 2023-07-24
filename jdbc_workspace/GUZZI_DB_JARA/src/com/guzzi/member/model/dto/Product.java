package com.guzzi.member.model.dto;

public class Product {
	private String itemNum;
	private String itemName;
	private int stock;
	private int price;
	
	public Product() {}

	public Product(String itemNum, String itemName, int stock, int price) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.stock = stock;
		this.price = price;
	}

	
	// getter / setter
	public String getItemNum() {
		return itemNum;
	}

	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
