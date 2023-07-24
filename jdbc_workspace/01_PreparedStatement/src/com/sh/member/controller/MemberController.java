package com.sh.member.controller;

import java.sql.Date;
import java.util.List;

import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.dto.Member;

/**
 * 
 * MVC 패턴의 제어센터 - Controller 클래스
 * - 모든 요청은 이 controller 클래스를 통해야 한다.
 * 
 */
public class MemberController {
	
	private MemberDao memberDao = new MemberDao();

	public int insertMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}

	public List<Member> findAll() {
		List<Member> memberList = memberDao.findAll();
		return memberList;
	}

	public Member findById(String id) {
		Member member = memberDao.findById(id);
		return member;
	}

	public int updateName(String id, String name) {
		int result = memberDao.updateName(id, name);
		return result;
	}

	public int updateBirthday(String id, Date birthday) {
		int result = memberDao.updateBirthday(id, birthday);
		return result;
	}

	public int updateEmail(String id, String email) {
		int result = memberDao.updateEmail(id, email);
		return result;
	}

	public List<Member> findByName(String name) {
		List<Member> memberList = memberDao.findByName(name);
		return memberList;
	}

	public int deleteMember(String id) {
		int result = memberDao.deleteMember(id);
		return result;
	}
}
