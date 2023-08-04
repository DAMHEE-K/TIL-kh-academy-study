package com.sh.app.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private String memberId;
	private String password;
	private String name;
	private LocalDate birthday;
	private String email;
	private LocalDateTime createdAt;
}
