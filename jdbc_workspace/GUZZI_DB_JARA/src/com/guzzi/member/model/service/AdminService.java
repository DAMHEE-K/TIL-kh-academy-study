package com.guzzi.member.model.service;

import java.sql.Connection;
import java.util.List;

import static com.guzzi.common.JdbcTemplate.*;
import com.guzzi.member.model.dao.AdminDao;
import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.vo.SelectSales;

public class AdminService {

	private AdminDao adminDao = new AdminDao();
	public List<SelectSales> checkTotalSales() {
		// 1. Connection 생성
		Connection conn = getConnection(); // JdbcTemplat static import 사용하여 클래스명 생략
		// 2. Dao 호출
		List<SelectSales> selectSales = adminDao.checkTotalSales(conn);
		// 3. 자원반납
		close(conn);
		return selectSales;
	}
	
	public List<SelectSales> checkLastSales() {
			Connection conn = getConnection(); 
			List<SelectSales> selectSales = adminDao.checkLastSales(conn);
			close(conn);
			return selectSales;
	}

	public List<Product> checkProducts() {
		Connection conn = getConnection(); 
		List<Product> products = adminDao.checkProducts(conn);
		close(conn);
		return products;
	}

	public List<Member> findAllMember() {
		Connection conn = getConnection(); 
		List<Member> memberList = adminDao.findAllMember(conn);
		close(conn);
		return memberList;
	}

}
