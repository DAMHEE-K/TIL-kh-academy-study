package com.sh.member.model.service;

// static 자원(메소드)을 import함 - 클래스명 생략 가능
import static com.sh.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.sh.common.JdbcTemplate;
import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.dto.Address;
import com.sh.member.model.dto.Member;

// Connection에 대한 일들을 Service가 처리 (commit | rollback)
public class MemberService {

	private MemberDao memberDao = new MemberDao();
	
	public int insertMember(Member member) {
		int result = 0;
		// 1. Connection 생성 및 자동커밋 생성 -> Connection 가져오기
		Connection conn = getConnection(); // JdbcTemplate.getConnection() -> static 임포트를 했으니 앞에 클래스명 생략 가능
		// Jdbc템플릿에서 예외처리도 했기 때문에 여기서 할 필요가 없음
		try {
			// 2. DAO 호출
			result = memberDao.insertMember(conn, member);
			List<Address> addressList = member.getAddressList();
			
			if(!addressList.isEmpty()) {
				Address address = addressList.get(0);
				result = memberDao.insertAddress(conn, address);
			}
			
			// 3. 트랜잭션
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에 예외 사실을 알림
		} finally {
			// 4. 자원반납
			close(conn);
		}
		return result;
	}
	
	
	public List<Member> findAll() {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Dao 호출
		List<Member> memberList = memberDao.findAll(conn);
		// 3. 자원반납
		close(conn);
		return memberList;
	}

	public Member findById(String id) {
		Connection conn = getConnection();
		Member member = memberDao.findById(conn, id);
		List<Address> addressList = memberDao.findAddressByMemberId(conn, id);
		member.setAddressList(addressList);
		close(conn);
		return member;
	}

	public List<Member> findByName(String name) {
		Connection conn = getConnection();
		List<Member> members = memberDao.findByName(conn, name);
		close(conn);
		return members;
	}

	
	
	
	/**
	 * DML
	 * 1. Connection 가져오기
	 * 2. DAO 호출
	 * 3. 트랜잭션 처리
	 * 4. 자원반납
	 */
	public int deleteMember(String id) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(conn, id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace(); // 로깅
		} finally {
			close(conn);
		}
		return result;
	}

	
//	public int updateName(String id, String name) {
//		int result = 0;
//		Connection conn = getConnection();
//		try {
//			result = memberDao.updateName(conn, id, name);
//			commit(conn);
//		} catch (Exception e) {
//			rollback(conn);
//			e.printStackTrace();
//		} finally {
//			close(conn);
//		}
//		return result;
//	}
//
//
//	public int updateBirthday(String id, Date birthday) {
//		int result = 0;
//		Connection conn = getConnection();
//		
//		try {
//			result = memberDao.updateBirthday(conn, id, birthday);
//			commit(conn);
//		} catch (Exception e) {
//			rollback(conn);
//			e.printStackTrace();
//		} finally {
//			close(conn);
//		}
//		return result;
//	}
//
//
//	public int updateEmail(String id, String email) {
//		int result = 0;
//		Connection conn = getConnection();
//		
//		try {
//			result = memberDao.updateEmail(conn, id, email);
//			commit(conn);
//		} catch (Exception e) {
//			rollback(conn);
//			e.printStackTrace();
//		} finally {
//			close(conn);
//		}
//		return result;
//	}


	public List<Member> findDelMember() {
		// 1. Connection 생성
		Connection conn = getConnection();
				
		// 2. Dao 호출
		List<Member> memberList = memberDao.findDelMember(conn);
		close(conn);
		return memberList;
	}


	public int updateMember(String id, String colName, Object newValue) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMember(conn, id, colName, newValue);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);			
		}
		return result;
	}


	public List<Address> findMemberAddressByMemberId(String id) {
		// 1. Connection 생성
		Connection conn = getConnection();
		// 2. Dao 호출
		List<Address> addressList = memberDao.findMemberAddressByMemberId(conn, id);
		// 3. 자원반납
		close(conn);
		return addressList;
	}
	
	
	
}
