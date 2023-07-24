package com.sh.member.controller;

import java.sql.Date;
import java.util.List;

import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.dto.Member;
import com.sh.member.model.service.MemberService;

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public int insertMember(Member member) {
		int result = memberService.insertMember(member);
		return result;
	}

	public List<Member> findAll() {
		List<Member> memberList = memberService.findAll();
		return memberList;
	}

	// 
	public int deleteMember(String id) {
		int result = memberService.deleteMember(id);
		return result;
	}

	public Member findById(String id) {
		Member member = memberService.findById(id);
		return member;
	}

	public List<Member> findByName(String name) {
		List<Member> members = memberService.findByName(name);
		return members;
	}


	public List<Member> findDelMember() {
		List<Member> members = memberService.findDelMember();
		return members;
	}

	public int updateMember(String id, String colName, Object newValue) {
		int result = memberService.updateMember(id, colName, newValue);
		
		return result;
	}
}
