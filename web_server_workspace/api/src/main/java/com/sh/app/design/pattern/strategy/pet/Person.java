package com.sh.app.design.pattern.strategy.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private String name;
	private Pet pet;
	
	public void play() {
		System.out.println(this.name + "이/가 " + this.pet.getName() + "와/과 놀고 있습니다.😁");
	}
}
