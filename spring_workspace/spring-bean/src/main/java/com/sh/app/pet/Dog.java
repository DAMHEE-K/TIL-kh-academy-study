package com.sh.app.pet;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Pet {
	
	public Dog() {
		System.out.println("Dog 객체 생성!");
	}

	public void helloPerson() {
		System.out.println("멍멍!!!");
	}

}
