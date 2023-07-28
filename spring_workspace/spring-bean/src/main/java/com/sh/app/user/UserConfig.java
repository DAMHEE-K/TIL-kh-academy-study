package com.sh.app.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class UserConfig {
	
	/**
	 * id : 메소드명 userController
	 * class : 반환타입 UserController
	 * 
	 * 생성자 UserController 타입의 객체를 만들고
	 * id는 userController이다.
	 */
	@Bean
	@Scope
	@Lazy // 실제 호출하기 전까지는 안 만듦
	public UserController userController() {
		System.out.println("UserController 빈을 생성합니다...");
		return new UserController(userService());
	}

	/**
	 * id : userService
	 * class : com.sh.app.user.UserService
	 */
	@Bean
	public UserService userService() {
		System.out.println("UserService 빈을 생성합니다...");
		return new UserServiceImpl();
	}
}
