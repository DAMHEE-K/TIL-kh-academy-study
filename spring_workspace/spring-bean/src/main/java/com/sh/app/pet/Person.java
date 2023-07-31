package com.sh.app.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
	/**
	 * Pet 타입의 빈(Pet을 구현한 빈을 조회해서 의존주입)
	 * - filed 레벨
	 * - method 레벨
	 * - constructor 레벨
	 */
	@Autowired
	@Qualifier("cat")
	private Pet pet;
	
	public Person() {
		System.out.println("Person 객체 생성!!");
		System.out.println(this.pet); // null
	}
	
	public void helloPet() {
		System.out.println(this.pet);
		this.pet.helloPerson();
	}
}