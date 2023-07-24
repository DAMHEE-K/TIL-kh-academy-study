package com.sh.member.model.dto;

import java.sql.Date;
import java.sql.Timestamp;

// 탈퇴 회원 관리 테이블
public class MemberDel extends Member {
	private Timestamp delDate; // 탈퇴 시간
	
	public MemberDel() {}
	
	public MemberDel(String id, String name, String gender, Date birthday, String email, int point, Timestamp regDate,Timestamp delDate) {
		super(id, name, gender, birthday, email, point, regDate);
		this.delDate = delDate;
	}

	// getter / setter
	public Timestamp getDelDate() {
		return delDate;
	}

	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
	
	// toString
	@Override
	public String toString() {
		return "MemberDel [delDate=" + delDate + ", toString()=" + super.toString() + "]";
	}
	
}
