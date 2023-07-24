package com.sh.member.controller;

import java.sql.Date;
import java.util.List;

import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.dto.Address;
import com.sh.member.model.dto.Member;
import com.sh.member.model.service.MemberService;

public class MemberController {
	
	private MemberService memberService = new MemberService();

	public int insertMember(Member member) {
		int result = 0;
		try {
			result = memberService.insertMember(member);
		} catch (Exception e) {
			// 사용자에게 에러 메세지 알림
			System.err.println("> 불편을 드려 죄송합니다. 관리자에게 연락해주세요." + e.getMessage());
			// 로깅 (개발자용)
			e.printStackTrace();
		}
		return result;
	}

	public List<Member> findAll() {
		List<Member> memberList = null;
		try {
			memberList = memberService.findAll();
		} catch (Exception e) {
			System.err.println(">관리자에게 연락바랍니다.: " + e.getMessage());
			e.printStackTrace();
		}
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

	public List<Address> findMemberAddressByMemberId(String id) {
		List<Address> addressList = null;
		try {
			addressList = memberService.findMemberAddressByMemberId(id);
		} catch (Exception e) {
			System.err.println("> 관리자에게 문의하세요. : " + e.getMessage());
			e.printStackTrace(); //개발자용 로깅
		}
		return addressList;
	}
}
