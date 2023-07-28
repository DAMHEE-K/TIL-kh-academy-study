package com.sh.app.design.pattern.builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 빌더 패턴(Builder Pattern)
 * - 객체 생성 과정이나 방식을 분리하여 다양한 구성의 인스턴스 객체를 만드는 생성 패턴
 * - 생성자로 만들게 되면 매개변수 순서를 꼭 지켜야하는 등 번거로움이 있지만,
 * - 빌더 패턴으로 만들게 되면 순서는 상관 없이 객체에 넣고 싶은 값만 세팅할 수 있기 때문에
 * - 편함
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	private long code;
	private String username;
	private String password;
	private LocalDate birthday;
	private String address;
	private boolean married;
	private LocalDateTime createdAt;
	
	public static Builder builder() {
		return new Builder();
	}
	
	// 내부 클래스 (inner class) 
	public static class Builder {
		private long code;
		private String username;
		private String password;
		private LocalDate birthday;
		private String address;
		private boolean married;
		private LocalDateTime createdAt;
		
		public Builder code(long code) {
			this.code = code;
			return this; // Builder객체를 반환해서 method chaining 가능
		}
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}
		
		public Builder birthday(LocalDate birthday) {
			this.birthday = birthday;
			return this;
		}
		
		public Builder address(String address) {
			this.username = address;
			return this;
		}
		
		public Builder married(boolean married) {
			this.married = married;
			return this;
		}
		
		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public User build() {
			return new User(this.code, this.username, this.password, this.birthday, this.address, this.married, this.createdAt);
		}
		
	}
}
