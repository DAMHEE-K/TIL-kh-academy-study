package com.guzzi.member.model.service;

import static com.guzzi.common.JdbcTemplate.*;
import static com.guzzi.common.JdbcTemplate.close;
import static com.guzzi.common.JdbcTemplate.commit;
import static com.guzzi.common.JdbcTemplate.getConnection;
import static com.guzzi.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.guzzi.member.model.dao.MemberDao;
import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.exception.MemberException;
import com.guzzi.member.model.vo.OrderList;
import com.guzzi.member.model.vo.SelectSales;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public int joinMembership(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.joinMembership(conn, member);
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public int deleteMember(Member member,String pw) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.deleteMember(member ,conn, pw);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public List<Member> findAllMember() {
		Connection conn = getConnection(); 
		List<Member> memberList = memberDao.findAllMember(conn);
		close(conn);
		return memberList;
	}
	
	public Member findMember(String id) {
		Connection conn = getConnection(); 
		Member member = memberDao.findMember(conn, id);
		close(conn);
		return member;
	}
	
	

	public int deposit(String id, int money) {
		int result = 0;
		Member member = null;
		Connection conn = getConnection();
		try {
			result = memberDao.deposit(conn, id , money); // money update 쿼리
			member = memberDao.findMember(conn, id); // DB에서 update된 member 객체의 정보를 다시 갱신함(select * from member where id = ?)
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	
	
	

	public int updateMember(String id, String colName, Object newValue) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.updateMember(conn, id, colName, newValue);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);			
		}
		return result;
	}

	public List<OrderList> orderList(String id) {
		Connection conn = getConnection(); 
		List<OrderList> orderList = memberDao.orderList(conn, id);
		close(conn);
		return orderList;
	}

	 public int insertOrderList(Member member,String itemNum) {
	      Connection conn = getConnection();
	      Product product = null;
	      int result = 0;
	      try {
	         result = memberDao.insertOrderList(member, conn, itemNum); // insert into tbl_orderList
	         product = memberDao.checkProduct(conn,itemNum); // product의 price를 읽어오기 위해서 itemNum로 조회하여 객체에 저장함
	         member = memberDao.findMember(conn, member.getId()); // 현재 로그인한 멤버의 정보를 갱신함 (캐시 충전금액을 자바에 갱신함)
	         
	         if(member.getMoney() < product.getPrice()) { 
	        	 throw new MemberException("금액이 부족합니다. 충전 후 이용해주세요.");
	         }
	         commit(conn);
	      } catch (Exception e) {
	         rollback(conn);
	         throw e;
	      } finally {
	         close(conn);         
	      }
	      return result;
	   }
	
	public Product checkProduct(String itemNum) {
		Connection conn = getConnection(); 
		Product product = memberDao.checkProduct(conn, itemNum);
		close(conn);
		return product;
	}
}
