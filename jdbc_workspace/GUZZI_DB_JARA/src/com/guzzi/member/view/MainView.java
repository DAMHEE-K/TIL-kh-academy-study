package com.guzzi.member.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.midi.SysexMessage;

import com.guzzi.member.controller.MemberController;
import com.guzzi.member.model.dto.Member;

public class MainView {
	private Scanner sc = new Scanner(System.in);

	Member member = null;
	List<Member> memberList = null;
	
	private MemberController memberController = new MemberController();

	private String adminId = "Admin"; 
	private String adminPassword = "230220";

	/**
	 * 로그인 디스플레이
	 */
	public void loginDispaly() {
		String loginMenu = "     온라인 쇼핑몰 굳이디비자라에 오신 것을 환영합니다\n"
				+ "⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂ Guzzi DB Jara ⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂\n" 
				+ "1. 로그인\n" 
				+ "2. 회원가입\n" 
				+ "0. 프로그램종료\n"
				+ "⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂\n" 
				+ "선택 : ";

		while (true) {
			System.out.print(loginMenu);
			String n = sc.next();
			
			switch (n) {
			case "1":
				member = login();
				if (!(member == null) && !((member.getId()).equals(adminId))) { // 만약 아이디/비밀번호가 맞아서 member가 null이 아니라면
					new MemberMenu().memberMenu(member);
				}
				System.out.println("로그인 후 진행해주세요."); // 아이디/비밀번호가 틀려서 member가 null일 경우
				break;
			case "2":
				member = joinMembership();
				memberController.joinMembership(member);
				break;
			case "0":
				return;
			default:
				System.out.println("> 다시 입력해주세요.");
			}
		}
	}

	/**
	 * 회원 가입 메소드
	 */
	private Member joinMembership() {
		Member member = new Member();
		memberList = memberController.findAllMember(); // DB에 저장된 모든 회원 정보를 불러와서 memberList에 저장
		String id = "";
		
		try {
			while(true) {
				System.out.println("\n[ ✎ 회원가입 ]");
				System.out.print("🔹 아이디를 입력하세요 >> ");
				id = sc.next();
				
				boolean flag = true; // 아이디 중복 검사를 위한 flag
				for(Member mem : memberList) {
					if(mem.getId().equals(id)) 
						flag = false; // 만약 memberList에 동일한 id가 있다면 flag flase로 선언
				}
				
				if(!flag) {
					System.out.println("> 사용불가능한 아이디입니다. 다시 입력하세요.");
				} else {
					System.out.println("사용 가능한 아이디입니다. :)");
					member.setId(id);
					break;
				}
			} //while문 끝

			System.out.print("🔹 비밀번호를 입력하세요 >> ");
			String password = sc.next();
			member.setPassword(password);
			
			System.out.print("🔹 이름을 입력하세요 >> ");
			String name = sc.next();
			member.setName(name);
			
			sc.nextLine();
			
			System.out.print("🔹 주소를 입력하세요 >> ");
			String location = sc.nextLine(); 
			member.setLocation(location);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}


	public Member login() {
		Member member = null;
		String id = inputId();
		String password = inputPassword();

		
		while (true) {
			if (id.equals(adminId) && password.equals(adminPassword)) {
				System.out.println("🛠 관리자님 환영합니다 🛠");
				new AdminMenu().adminMenu();
				break;
			}
			
			memberList = memberController.findAllMember();
		
			boolean flag = true;
			for(Member mem : memberList) {
				if(id.equals(mem.getId()) && password.equals(mem.getPassword())) {
					return mem;
				} else {
					flag = false;
				}
			}
			
			if(!flag) {
				System.out.println("> 아이디 또는 비밀번호가 맞지 않습니다.\n");
				break; // 아이디 또는 비밀번호가 틀리면 member = null이됨
			}
		} // while문 끝
		return member;
	}
	
	
	
	/**
	 * 비밀번호 입력받는 메소드
	 */
	private String inputPassword() {
		System.out.print("🔹 비밀번호를 입력하세요 : ");
		return sc.next();
	}

	/**
	 * 아이디 입력 받는 메소드
	 */
	private String inputId() {
		System.out.print("🔹 아이디를 입력하세요 : ");
		return sc.next();
	}

}