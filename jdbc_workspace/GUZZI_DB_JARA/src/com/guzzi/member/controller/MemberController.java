package com.guzzi.member.controller;

import java.util.List;
import java.util.Scanner;

import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.dto.Product;
import com.guzzi.member.model.service.MemberService;
import com.guzzi.member.model.vo.OrderList;

public class MemberController {
private Scanner sc = new Scanner(System.in);
private MemberService memberService = new MemberService();


	public int joinMembership(Member member) {
		int result = 0;
		try {
			result = memberService.joinMembership(member);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int deleteMember(Member member,String pw) {
		int result = 0;
		try {
			result = memberService.deleteMember(member,pw);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public List<Member> findAllMember() {
		List<Member> memberList = null;
		try {
			memberList = memberService.findAllMember();
		} catch (Exception e) {
			System.out.println("> 회원 조회 오류" + e.getMessage());
			e.printStackTrace();
		}
		return memberList;
	}
	
	public Member findMember(String id) {
		Member member = null;
		try {
			member = memberService.findMember(id);
		} catch (Exception e) {
			System.out.println("> 회원 조회 오류" + e.getMessage());
			e.printStackTrace();
		}
		return member;
	}


	public int deposit(String id , int money) {
		int result = 0;
		try {
			result = memberService.deposit(id , money);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public int updateMember(String id, String colName, Object newValue) {
		int result = 0;
		try {
			result = memberService.updateMember(id , colName, newValue);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	public List<OrderList> orderList(String id) {
		List<OrderList> orderList = null;
		try {
			orderList = memberService.orderList(id);
		} catch (Exception e) {
			System.out.println("> 주문 조회 오류" + e.getMessage());
			e.printStackTrace();
		}
		return orderList;
	}


	 public int insertOrderList(Member member,String itemNum) {
	      int result = 0;
	      try {
	         result = memberService.insertOrderList(member,itemNum);
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      return result;
	   }

	   public Product checkProduct(String itemNum) {
	      Product product = null;
	      try {
	         product = memberService.checkProduct(itemNum);
	      } catch (Exception e) {
	         System.out.println("> 구매 오류" + e.getMessage());
	         e.printStackTrace();
	      }
	      return product;
	   }
	}




