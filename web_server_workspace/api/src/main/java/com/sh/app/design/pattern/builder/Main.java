package com.sh.app.design.pattern.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
	
	public static void main(String[] args) {
		long code = 1234567L;
		String username = "honggd";
		String password = "1234";
		LocalDate birthday = LocalDate.of(1999, 9, 9);
		String address = "서울시 강남구 역삼동";
		boolean married = true;
		LocalDateTime createdAt = null;
//		User user = new User(code, username, password, birthday, address, married, createdAt);
		
		User user1 = User.builder()
				.code(code)
				.username(username)
				.birthday(birthday)
				.password(password)
				.address(address)
				.married(married)
				.build();
		
		System.out.println(user1);
		
		
		User user2 = User.builder()
				.username(username)
				.password(password)
				.build();
		
		System.out.println(user2);
	}
}
