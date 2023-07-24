package com.guzzi.member.model.dto;

public class Member {
	private String id;
	private String password;
	private String name;
	private String location;
	private int money;
	
	
	public Member() {} // 기본생성자

	// 매개변수 생성자
	public Member(String id, String password, String name, String location, int money) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.location = location;
		this.money = money;
	}

	// getter / setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", location=" + location + ", money="
				+ money + "]";
	}
	
}
