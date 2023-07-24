package com.guzzi.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.exception.AdminException;
import com.guzzi.member.model.exception.MemberException;
import com.guzzi.member.model.vo.OrderList;
import com.guzzi.member.model.vo.SelectSales;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
	      try {
	         prop.load(new FileReader("resources/member-query.properties"));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	}

	public int joinMembership(Connection conn, Member member) {
		String sql = prop.getProperty("joinMembership");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getLocation());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류", e);
		}
		return result;
	}

	public int deleteMember(Member member,Connection conn, String pw) {
		String sql = prop.getProperty("deleteMember");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, pw);
			pstmt.setString(2, member.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("비밀번호를 확인해주세요", e);
		}
		return result;
	}

	public List<Member> findAllMember(Connection conn) {
		List<Member> memberList = new ArrayList<>();
		String sql = prop.getProperty("findAllMember");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Member member = MemberResultSet(rset);
					memberList.add(member);
				}
			}
		} catch (SQLException e) {
			throw new MemberException("멤버 테이블 조회 오류", e);
		}
		return memberList;
	}
	
	public Member findMember(Connection conn, String id) {
		Member member = new Member();
		String sql = prop.getProperty("findMember");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					member = MemberResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new MemberException("멤버 테이블 조회 오류", e);
		}
		return member;
	}

	
	private Member MemberResultSet(ResultSet rset) {
		Member member = new Member();
		try {
			member.setId(rset.getString("id"));
			member.setPassword(rset.getString("password"));
			member.setName(rset.getString("name"));
			member.setLocation(rset.getString("location"));
			member.setMoney(rset.getInt("money"));
			
		} catch (SQLException e) {
			throw new MemberException("멤버 조회 오류", e);
		}
		return member;
	}

	public int deposit(Connection conn, String id, int money) {
		String sql = prop.getProperty("deposit");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, money);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("캐시 충전 오류", e);
		}
		return result;
	}

	public int updateMember(Connection conn, String id, String colName, Object newValue) {
		String sql = prop.getProperty("updateMember");
		sql = sql.replace("#", colName);
		int result = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, newValue);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("회원정보 수정오류", e);
		}
		return result;
	}

	public List<OrderList> orderList(Connection conn, String id) {
		List<OrderList> orderList = new ArrayList<>();
		String sql = prop.getProperty("orderList");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					OrderList order = orderListResultSet(rset);
					orderList.add(order);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("지난 달 판매 테이블 조회 오류", e);
		}
		return orderList;
	}

	private OrderList orderListResultSet(ResultSet rset) {
		OrderList order = new OrderList();
		try {
			order.setOrderNum(rset.getString("orderNum"));
			order.setItemNum(rset.getString("itemNum"));
			order.setName(rset.getString("name"));
			order.setLocation(rset.getString("location"));
			order.setAmount(rset.getInt("amount"));
			order.setSaleDate(rset.getDate("sale_date"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	public int insertOrderList(Member member, Connection conn, String itemNum) {
		String sql = prop.getProperty("insertOrderList");
		//insert into tbl_orderList values(?,?,?,?,?,?,default)
		int result = 0;
		int index = 100;
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setObject(1, "DJY01SP"+(index++));
			pstmt.setObject(2, member.getId());
			pstmt.setObject(3, itemNum);
			pstmt.setObject(4, member.getName());
			pstmt.setObject(5, member.getLocation());
			pstmt.setObject(6, 1);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("구매 내역 불러오기 오류", e);
		}
		return result;
	}
	
	/**
	 * 상품 구매할 때 아이템 정보를 불러오기 위한 메소드
	 */
	public Product checkProduct(Connection conn, String itemNum) {
		Product product = new Product();
		String sql = prop.getProperty("checkProduct");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, itemNum);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					product = productResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new AdminException("상품 구매 오류", e);
		}
		return product;
	}
	
	
	private Product productResultSet(ResultSet rset) {
		Product prod = new Product();
		try {
			prod.setItemNum(rset.getString("itemNum"));
			prod.setItemName(rset.getString("itemName"));
			prod.setStock(rset.getInt("stock"));
			prod.setPrice(rset.getInt("price"));
		} catch (SQLException e) {
			throw new AdminException("상품 불러오기", e);
		}
		return prod;
	}
	
}
