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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.exception.AdminException;
import com.guzzi.member.model.vo.SelectSales;

public class AdminDao {
	
	private Properties prop = new Properties(); 
	
	public AdminDao() {
		try {
			prop.load(new FileReader("resources/admin-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<SelectSales> checkTotalSales(Connection conn) {
		List<SelectSales> selectSales = new ArrayList<>();
		String sql = prop.getProperty("selectSales");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					SelectSales sal = salesResultSet(rset);
					selectSales.add(sal);
				}
			}
		} catch (SQLException e) {
			throw new AdminException("총 판매 테이블 조회 오류", e);
		}
		return selectSales;
	}

	
	public List<SelectSales> checkLastSales(Connection conn) {
		List<SelectSales> selectSales = new ArrayList<>();
		String sql = prop.getProperty("checkLastSales");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					SelectSales sal = salesResultSet(rset);
					selectSales.add(sal);
				}
			}
		} catch (SQLException e) {
			throw new AdminException("지난 달 판매 테이블 조회 오류", e);
		}
		return selectSales;
	}





	public List<Product> checkProducts(Connection conn) {
		List<Product> products = new ArrayList<>();
		String sql = prop.getProperty("checkProducts");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					Product prod = productResultSet(rset);
					products.add(prod);
				}
			}
		} catch (SQLException e) {
			throw new AdminException("상품 조회 테이블 조회 오류", e);
		}
		return products;
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
			throw new AdminException("멤버 테이블 조회 오류", e);
		}
		return memberList;
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
			
			e.printStackTrace();
		}
		return member;
	}


	/**
	 * 상품 정보 ResultSet을 읽어오는 메소드
	 */
	private Product productResultSet(ResultSet rset) {
		Product prod = new Product();
		try {
			prod.setItemNum(rset.getString("itemNum"));
			prod.setItemName(rset.getString("itemName"));
			prod.setStock(rset.getInt("stock"));
			prod.setPrice(rset.getInt("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}

	/**
	 * 판매량 ResultSet을 읽어오는 메소드
	 */
	private SelectSales salesResultSet(ResultSet rset) throws SQLException {
		SelectSales selectSales = new SelectSales();
		selectSales.setItemNum(rset.getString("itemNum"));
		selectSales.setItemName(rset.getString("itemName"));
		selectSales.setSum(rset.getInt("sum(price)"));
		
		return selectSales;
	}


}
