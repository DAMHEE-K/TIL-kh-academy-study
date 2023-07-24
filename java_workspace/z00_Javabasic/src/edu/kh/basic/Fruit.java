package edu.kh.basic;

import java.io.Serializable;

public class Fruit {

	String fruitName;
	String color;
	
	public Fruit() {}
	
	public Fruit(String fruitName, String color) {
		this.fruitName = fruitName;
		this.color = color;
	}
	
	
	public String getFruitName() {
		return fruitName;
	}
	public void setFruitName(String fruitName) {
		this.fruitName = fruitName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Fruit [fruitName=" + fruitName + ", color=" + color + "]";
	}
	
	
}
