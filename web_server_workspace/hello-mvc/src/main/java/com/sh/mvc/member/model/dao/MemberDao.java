package com.sh.mvc.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.mvc.member.model.exception.MemberException;
import com.sh.mvc.member.model.vo.Gender;
import com.sh.mvc.member.model.vo.Member;
import com.sh.mvc.member.model.vo.MemberRole;

public class MemberDao {
	// Properties class : "Key = Value" 형태로 된 "파일이름.properties" 파일 또는 Xml 파일
	// 					   key를 주면 Value를 반환하는 기능을 가짐
	private Properties prop = new Properties();
	
	
	
	public MemberDao() {	
		// src/main/resources/sql/member/member-query.properties 작성
		// build/classes/sql/member/member-query.properties 톰캣용 읽기파일 생성됨
		
		String filename = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
//			System.out.println("prop@dao = " + prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Member findById(Connection conn, String memberId) {
		String sql = prop.getProperty("findById");
		// member-query.properties 파일 에서 findById라는 키값을 가진 sql을 읽어온다
		
		Member member = null; // 일단 null 값으로 설정
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, memberId); // ?를 채우기 (아이디 조회니까 가져온 id를 가지고 db 조회 쿼리를 날림)
			
			try(ResultSet rset = pstmt.executeQuery()) { // 쿼리가 정상적으로 실행되면 결과를 ResultSet에 담기
				while(rset.next()) {
					member = handleMemberResultSet(rset); // DB에서 조회된 멤버의 특징들(아이디, 이름..)을 자바 객체로 변환하기 위한 메소드
				}
			}
		} catch (SQLException e) {
			throw new MemberException(e); // MemberException 라는 런타임 예외를 만들어서 던짐
		}
		// handleMemberResultSet()를 통해서 생성한 member객체를 반환
		return member; // 이 member를 들고 다시 service로 감
	}

	
	
	/**
	 * 테이블 1행 -> VO 객체 한 개와 대조함
	 * DB에서 읽어온 멤버 객체를 자바 객체로 변환하기 위한 메소드
	 */
	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		String memberId = rset.getString("member_id"); // DB 컬럼명을 읽어와서 그 값을 변수에 저장
		String password = rset.getString("password");
		String name = rset.getString("name");
		
		MemberRole memberRole = MemberRole.valueOf(rset.getString("member_role")); // not null일 때
		// member_role을 String 타입으로 읽어온 후, MemberRole 타입으로 변환한 것임
		// enum(열거형) 으로 선언한 경우 이렇게 하면 편함 (뭐가 편한지는 아직 모름ㅎ)
		
		String _gender = rset.getString("gender"); // gender도 읽어온다. 근데 gender는 null일수도 있다.
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		// 삼항연산자 뜻
		// 만약 DB에서 읽어온 _gender가 null이 아니니? null이 아니라면 gender타입으로 변환하고, null이면 null로 냅둬라
		
		Date birthday = rset.getDate("birthday");
		String email = rset.getString("email");
		String phone = rset.getString("phone");
		String hobby = rset.getString("hobby");
		int point = rset.getInt("point");
		Timestamp enrollDate = rset.getTimestamp("enroll_date");
		
		// 입력받은 값을 토대로 생성자를 통해서 자바의 Member 객체를 만들어준 후, 반환한다.
		return new Member(memberId, password, name, memberRole, gender, birthday, email, phone, hobby, point, enrollDate);
	}

	
	/**
	 * 
	 */
	public int insertMember(Connection conn, Member newMember) {
		int result = 0; // DML은 int 반환
		String sql = prop.getProperty("insertMember");
		
		// insert into member values(?, ?, ?, default, ?, ?, ?, ?, ?, default, default)
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newMember.getMemberId());
			pstmt.setString(2, newMember.getPassword());
			pstmt.setString(3, newMember.getName());
			pstmt.setString(4, newMember.getGender().name());
			pstmt.setDate(5, newMember.getBirthday());
			pstmt.setString(6, newMember.getEmail());
			pstmt.setString(7, newMember.getPhone());
			pstmt.setString(8, newMember.getHobby());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입오류", e);
		}
		return result;
	}


	/**
	 * 수정
	 */
	public int updateMember(Connection conn, Member setMember) {
		int result = 0;
		// update member set name=?, birthday=?, email=?, phone=?, gender=?, hobby=? where member_id = ?
		String sql = prop.getProperty("updateMember");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, setMember.getName());
			pstmt.setDate(2, setMember.getBirthday());
			pstmt.setString(3, setMember.getEmail());
			pstmt.setString(4, setMember.getPhone());
			pstmt.setString(5, String.valueOf(setMember.getGender()));
			pstmt.setString(6, setMember.getHobby());
			pstmt.setString(7, setMember.getMemberId());
			result = pstmt.executeUpdate();
			
			System.out.println("result Dao = " + result);
			
		} catch (SQLException e) {
			throw new MemberException("회원 업데이트 오류", e);
		}
		return result;
	}



	public int deleteMember(Connection conn, Member delMember) {
		int result = 0;
		// delete from member where member_id = ?
		String sql = prop.getProperty("deleteMember");
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, delMember.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입오류", e);
		}
		return result;
	}



	public List<Member> findById(Connection conn) {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try (
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery(); // select 한 행들이 rset에 담김
		) {
			while(rset.next()) { // rset이 다음에도 있으면 true (rset을 모두 순회할 수 있는 반복문임)
				Member member = handleMemberResultSet(rset); // rset을 자바의 member객체로 변환하여 member에 담음
				members.add(member); // 변환된 member를 memberList에 담음
			}
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return members;
	}



	public int updateMemberRole(Connection conn, Member member, String memberRole) {
		int result = 0;
		// updateMemberRole = update member set member_role=? where member_id = ?
		String sql = prop.getProperty("updateMemberRole");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, memberRole);
			pstmt.setString(2, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 권한 변경 오류", e);
		}
		return result;
	}



	public List<Member> searchMember(Connection conn, String searchType, String searchKeyword) {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("searchMember");
		sql = sql.replace("#", searchType);
		System.out.println("sql@dao = " + sql);
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + searchKeyword + "%");
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Member member = handleMemberResultSet(rset);
					members.add(member);
				}
			}
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		
		return members;
	}
}
