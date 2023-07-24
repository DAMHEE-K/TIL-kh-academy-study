package com.guzzi.member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.guzzi.member.controller.AdminController;
import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.vo.SelectSales;


// Main <-> view <-> controller <-> service <-> dao <-> db

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	private AdminController adminController = new AdminController();
	
	public void adminMenu() {
		System.out.println("     [관리자님 환영합니다.]     ");
		String menu = "⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰ 관리자 메뉴 ⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰\n"
				+ "1. 지난 달 매출 내역 조회\n"
				+ "2. 총 매출 조회\n"
				+ "3. 상품 재고 확인\n"
				+ "4. 전체 회원 조회\n"
				+ "0. 프로그램 종료\n"
				+ "⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰ ⋱⋰⋱⋰ ⋱⋰ ⋱⋰\n"
				+ "선택 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			List<SelectSales> selectSales = null;
			List<Product> products = null;
			List<Member> memberList = null;
			
			switch(choice) {
			
			case "1" :
				selectSales = adminController.checkLastSales();
				displaySales(selectSales);
				break;
			case "2" : 
				selectSales = adminController.checkTotalSales();
				displaySales(selectSales);
				break;
			case "3" : 
				products = adminController.checkProducts();
				displayProducts(products);
				break;
			case "4" : 
				memberList = adminController.findAllMember();
				displayMemberList(memberList);
				break;
			case "0" : return;
			default : break;
			}
		}
	}
	
	
	private void displayMemberList(List<Member> memberList) {
		if(memberList == null || memberList.isEmpty()) {
			System.out.println("> 조회된 회원이 없습니다.");
		} else {
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ 회원 조회 ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.printf("%-30s %-10s %-30s %-20s\n", "ID", "이름", "주소", "충전 금액");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			
			for(Member mem : memberList) {
				System.out.printf("%-30s %-10s %-30s [%s]\n",mem.getId(), mem.getName(), mem.getLocation(), mem.getMoney());
			}
		}
	}
	

	/**
	 * 상품 조회 메소드
	 */
	private void displayProducts(List<Product> products) {
		if(products == null || products.isEmpty()) {
			System.out.println("> 등록된 상품이 없습니다.");
		} else {
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ 상품 조회 ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.printf("%-10s %-20s %-10s %-20s\n", "상품번호", "상품명", "재고", "판매가격");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			
			for(Product p : products) {
				System.out.printf("%-10s %-20s %-10s %-20s\n",p.getItemNum(), p.getItemName(), p.getStock(), p.getPrice());
			}
		}
	}


	/**
	 * 판매량 테이블 보여주는 메소드
	 */
	private void displaySales(List<SelectSales> selectSales) {
		
		if(selectSales == null) {
			System.out.println("> 조회된 내역이 없습니다.");
		} else {
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿💸 매출 내역 조회 💸＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.printf("%-10s %-20s %-20s\n", "상품번호", "상품명", "총 판매액");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			
			for(SelectSales sal : selectSales) {
				System.out.printf("%-10s %-20s %-20s\n", sal.getItemNum(), sal.getItemName(), sal.getSum());
			}
		}
	}

}
