package com.sh.app.student.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
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
public class Student {
	private int id;
	private String name;
	private String tel;
	private LocalDateTime createdAt;
	
}