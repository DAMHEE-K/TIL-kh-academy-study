package com.guzzi.member.controller;

import java.util.List;

import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.service.AdminService;
import com.guzzi.member.model.vo.SelectSales;

public class AdminController {

	private AdminService adminService = new AdminService();
	
	public List<SelectSales> checkTotalSales() {
		List<SelectSales> selectSales = null;
		try {
			selectSales = adminService.checkTotalSales();
		} catch (Exception e) {
			System.out.println("> 총 매출액 조회 오류." + e.getMessage());
			e.printStackTrace();
		}
		return selectSales;
	}

	public List<SelectSales> checkLastSales() {
		List<SelectSales> selectSales = null;
		try {
			selectSales = adminService.checkLastSales();
		} catch (Exception e) {
			System.out.println("> 지난 매출 조회 오류." + e.getMessage());
			e.printStackTrace();
		}
		return selectSales;
	}

	public List<Product> checkProducts() {
		List<Product> products = null;
		try {
			products = adminService.checkProducts();
		} catch (Exception e) {
			System.out.println("> 상품 조회 오류." + e.getMessage());
			e.printStackTrace();
		}
		return products;
	}

	public List<Member> findAllMember() {
		List<Member> memberList = null;
		try {
			memberList = adminService.findAllMember();
		} catch (Exception e) {
			System.out.println("> 회원 조회 오류" + e.getMessage());
			e.printStackTrace();
		}
		return memberList;
	}

}
