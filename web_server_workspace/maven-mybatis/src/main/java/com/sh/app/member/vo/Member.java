package com.sh.app.member.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Member {
	private int userNo;
	
	@NonNull
	private String userId;
	
	@NonNull
	private String userPw;
	
	@NonNull
	private String userName;
	
	@NonNull
	private String UserAddr;
	private Date regDate;
}
