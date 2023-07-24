package com.sh.mvc.member.model.service;

import static com.sh.mvc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.vo.Member;

public class MemberService {

	public final MemberDao memberDao = new MemberDao();
	
	
	/**
	 * DQL
	 * 1. Connection 생성
	 * 2. PrepareStatement 생성 및 ?에 값대입
	 * 3. 실행 및 ResultSet 처리
	 * 4. 자원반납
	 * 
	 * DML
	 * 1. Connection 생성 (SetAutoCommit : false)
	 * 2. PrepareStatement 생성 및 ?에 값대입
	 * 3. 실행 및 int 반환
	 * 4. 트랜잭션 처리 (commit / rollback)
	 * 5. 자원반납
	 */
	public Member findById(String memberId) {
		Connection conn = getConnection(); // 다리연결
		Member member = memberDao.findById(conn, memberId);
		close(conn); // 자원반납
		return member;
	}


	public int insertMember(Member newMember) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, newMember);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; // controller로 예외 던지기
		} finally {
			close(conn);
		}
		return result;
	}


	public int updateMember(Member setMember) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, setMember);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; // controller로 예외 던지기
		} finally {
			close(conn);
		}
		return result;
	}


	public int deleteMember(Member delMember) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(conn, delMember);
			commit(conn);
		} catch (Exception e){
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public List<Member> findAll() {
		Connection conn = getConnection();
		List<Member> members = memberDao.findById(conn);
		close(conn);
		return members;
	}


	public int updateMemberRole(Member member, String memberRole) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMemberRole(conn, member, memberRole);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; // controller로 예외 던지기
		} finally {
			close(conn);
		}
		return result;
	}


	public List<Member> searchMember(String searchType, String searchKeyword) {
		Connection conn = getConnection();
		List<Member> members = memberDao.searchMember(conn, searchType, searchKeyword);
		close(conn);
		return members;
	}
	
}
