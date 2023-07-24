package com.sh.member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sh.member.model.dto.Member;

public class MemberDao {

	/**
	 * DML
	 * 1. PreparedStatement 생성 & 미완성 쿼리 값 대입
	 * 2. 쿼리실행 PreparedStatement#executeUpdate: int(처리된 행의 수)
	 * 3. 자원반납 (PreparedStatement)
	 * @throws SQLException 
	 */
	public int insertMember(Connection conn, Member member) throws SQLException {
		String sql = "insert into member values(?, ?, ?, ?, ?, default, default)";
		int result = 0;
		
		// 1. PreparedStatement 생성 & 미완성 쿼리 값 대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,  member.getId());
			pstmt.setString(2,  member.getName());
			pstmt.setString(3,  member.getGender());
			pstmt.setDate(4,  member.getBirthday());
			pstmt.setString(5,  member.getEmail());
			
			// 2. 쿼리실행 PreparedStatement#executeUpdate: int(처리된 행의 수)
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// 예외가 발생하면 서비스로 던져서 예외 발생 여부를 알린다.
			throw e;
		}
		return result;
	}

	/**
	 * DQL
	 * 1. PreparedStatement 생성 & 미완성쿼리 값대입
     * 2. 쿼리 실행 PreapredStatement#executeQuery:ResultSet
     * 3. ResultSet 처리 : 행 -> dto객체
     * 4. ResultSet, PreparedStatement는 여기서 반환
	 */
	public List<Member> findAll(Connection conn) {
		
		List<Member> memberList = new ArrayList<>();
		String sql = "select * from member order by reg_date desc";
		
		// 1. PreparedStatement 생성 & 미완성쿼리 값대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// 2. 쿼리 실행 PreapredStatement#executeQuery:ResultSet
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					//한 행의 컬럼값을 가져와 Member객체로 변환한 후, memberList에 추가
					// 매개변수성장자 | 기본생성자 + setter
					// 3. ResultSet 처리 : 행 -> dto객체
					Member member = handleMemberResultSet(rset);
					
					memberList.add(member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	
	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		
		Member member = new Member();
		member.setId(rset.getString("id"));
		member.setName(rset.getString("name"));
		member.setGender(rset.getString("gender"));
		member.setBirthday(rset.getDate("birthday"));
		member.setEmail(rset.getString("email"));
		member.setPoint(rset.getInt("point"));
		member.setRegDate(rset.getTimestamp("reg_date"));
		return member;
	}

	/**
	 * DML
	 * 1. PreparedStatement 생성 & 미완성 쿼리 값 대입
	 * 2. 쿼리실행 PreparedStatement#executeUpdate: int(처리된 행의 수)
	 * 3. 자원반납 (PreparedStatement)
	 * @throws SQLException 
	 */
	public int deleteMember(Connection conn, String id) throws SQLException {
		String sql = "delete from member where id = ?";
		int result = 0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id); // 'id' 와 같이 '' 처리를 해주는 메소드
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		}
		return result;
	}

//	public int updateName(Connection conn, String id, String name) {
//		String sql = "update member set name = ? where id = ?";
//		int result = 0;
//		
//		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, name);
//			pstmt.setString(2, id);
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
//
//	public int updateBirthday(Connection conn, String id, Date birthday) {
//		int result = 0;
//		String sql = "update member set birthday = ? where id = ?";
//		
//		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setDate(1, birthday);
//			pstmt.setString(2, id);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
//	public int updateEmail(Connection conn, String id, String email) {
//		int result = 0;
//		String sql = "update member set email = ? where id = ?";
//		
//		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
//			pstmt.setString(1, email);
//			pstmt.setString(2, id);
//			result = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	
	
	public Member findById(Connection conn, String id) {
		String sql = "select * from member where id = ?";
		Member member = null;
		
		// 1.PreparedStatement 생성 및 미완성 쿼리 값 대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, id);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while (rset.next()) {
					id = rset.getString("id");
					String name = rset.getString("name");
					String gender = rset.getString("gender");
					Date birthday = rset.getDate("birthday");
					String email = rset.getString("email");
					int point = rset.getInt("point");
					Timestamp regDate = rset.getTimestamp("reg_date"); 
					
					member = new Member(id, name, gender, birthday, email, point, regDate);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}

	public List<Member> findByName(Connection conn, String name) {
		String sql = "select * from member where name like ?";
		List<Member> members = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%"+name+"%");
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					String id = rset.getString("id");
					name = rset.getString("name");
					String gender = rset.getString("gender");
					Date birthday = rset.getDate("birthday");
					String email = rset.getString("email");
					int point = rset.getInt("point");
					Timestamp regDate = rset.getTimestamp("reg_date"); 
					Member member = new Member(id, name, gender, birthday, email, point, regDate);
					members.add(member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	public List<Member> findDelMember(Connection conn) {
		List<Member> memberList = new ArrayList<>();
		String sql = "select * from member_del order by del_date desc";
		
		// 1. PreparedStatement 생성 & 미완성쿼리 값대입
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// 2. 쿼리 실행 PreapredStatement#executeQuery:ResultSet
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					//한 행의 컬럼값을 가져와 Member객체로 변환한 후, memberList에 추가
					// 매개변수성장자 | 기본생성자 + setter
					// 3. ResultSet 처리 : 행 -> dto객체
					Member member = handleMemberResultSet(rset);
					
					memberList.add(member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	/**
	 * PreparedStatement는 컬럼명을 지정하는 set메소드를 지원하지 않는다.
	 * String#replace를 통해 처리
	 * 
	 * @param conn
	 * @param id
	 * @param colName
	 * @param newValue
	 * @return
	 * @throws SQLException 
	 */
	public int updateMember(Connection conn, String id, String colName, Object newValue) throws SQLException {
		String sql = "update member set # = ? where id = ?";
		sql = sql.replace("#", colName);
		int result = 0;
	
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, newValue);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} 
	
		return result;
	}


}
