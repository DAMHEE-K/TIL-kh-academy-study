package com.guzzi.member.model.vo;

import java.sql.Date;

// 판매량 조회를 위한 vo
public class SelectSales {
	private String itemNum;
	private String itemName;
	private Date sale_date;
	private int sum; // 총 판매량
	
	public SelectSales() {}

	public SelectSales(String itemNum, String itemName, Date sale_date, int sum) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.sale_date = sale_date;
		this.sum = sum;
	}

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

	public Date getSale_date() {
		return sale_date;
	}

	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}


}
