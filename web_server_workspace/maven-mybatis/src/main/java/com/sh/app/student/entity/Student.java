package com.sh.app.student.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 아웃라인 뷰에서 확인 가능함
// 클래스가 실행될 때 대신 생성해줌
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Student {
	// 속성값
	private int id;
	private String name;
	private String tel;
	private LocalDateTime createdAt;
	
	
//	public static Builder builder() {
//		return new Builder();
//	}
//	
//	
//	// inner class
//	public static class Builder {
//		private int id;
//		private String name;
//		private String tel;
//		private LocalDateTime createdAt;
//		
//		public Builder id (int id) {
//			this.id = id;
//			return this;
//		}
//		
//		public Builder name (String name) {
//			this.name = name;
//			return this;
//		}
//		
//		public Builder tel (String tel) {
//			this.tel = tel;
//			return this;
//		}
//		
//		public Builder createdAt (LocalDateTime createdAt) {
//			this.createdAt = createdAt;
//			return this;
//		}
//		
//		
//		public Student build() {
//			return new Student(this.id, this.name, this.tel, this.createdAt);
//		}
//	}
	
}