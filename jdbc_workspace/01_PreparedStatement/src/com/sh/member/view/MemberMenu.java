package com.sh.member.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.sh.member.controller.MemberController;
import com.sh.member.model.dto.Member;

/**
 * View 클래스
 * - 사용자 인터페이스(메뉴, 입력 값 처리) 제공
 * - 사용자 입력에 따라 controller 클래스에 요청 (메소드 호출)
 */
public class MemberMenu {

	private MemberController memberController = new MemberController();
	private Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		String menu = "===== 회원 관리 프로그램 =====\n"
				+ "1. 전체조회\n"
				+ "2. 아이디 조회\n"
				+ "3. 이름검색\n"
				+ "4. 회원가입\n"
				+ "5. 회원정보변경\n"
				+ "6. 회원탈퇴\n"
				+ "0. 프로그램종료\n"
				+ "========================\n"
				+ "선택 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			Member member = null;
			int result = 0;
			List<Member> memberList = null;
			String id = null;
			String name = null;
			
			switch(choice) {
			case "1" : 
				memberList = memberController.findAll();
				displayMemberList(memberList);
				break;
			case "2" : 
				id = inputId(); //honggd
				member = memberController.findById(id);
				displayMember(member);
				break;
			case "3" : 
				name = inputName();
				memberList = memberController.findByName(name);
				displayMemberList(memberList);
				break;
			case "4" : 
				member = inputMember();
				result = memberController.insertMember(member);
				System.out.println("> 회원가입성공!");
				break;
			case "5" : 
				updateMenu();
				break;
			case "6" :
				id = inputId();
				result = memberController.deleteMember(id);
				System.out.println("> 회원삭제 성공!");
				break;
			case "0" : break;
			default : System.out.println("> 잘못 입력하셨습니다.");
			}
			break;
		}
	}
	
	private String inputName() {
		System.out.print("> 이름 : ");
		return sc.next();
	}

	/**
	 * 수정메뉴
	 * 회원정보변경 서브메뉴를 제공해서 처리하세요
	 * 메인메뉴에서 회원정보변경 메뉴 선택시, 수정할 회원아이디를 입력받고, 해당 회원정보(회원아이디조회)를 우선 보여준 후 서브메뉴출력
	 *
	 * (메인메뉴에서 5번 입력 시)
	 * > 수정할 아이디 입력 : 
	 * 조회에 성공한 경우 - (회원정보출력 : displayMember 사용)
	 * 조회에 실패한 경우 - 메인메뉴로 리턴
	 * 
	 * 회원정보 수정은 선택된 필드를 각각 처리할 것.
	 * 회원정보변경 수정완료 시 수정된 회원정보를 출력후, 서브메뉴를 다시 출력.
	 * 수정할 회원아이디를 조회해서 해당하는 회원이 없다면, 메인메뉴를 다시 출력할 것.
	 * 
	 */
	private void updateMenu() {
		Member member = null;
		
		String id = inputId();
		
		displayMember(memberController.findById(id)); // 원래의 회원 정보 출력 
		
		String menu = "+++++++ 회원정보 수정 +++++++\n"
					+ "1. 이름수정\n"
					+ "2. 생일수정\n"
					+ "3. 이메일 수정\n"
					+ "0. 메인메뉴 돌아가기\n"
					+ "+++++++++++++++++++++++++++\n"
					+ "선택 : ";
		
		while(true) {
			member = memberController.findById(id);
			displayMember(member);
			if(member == null)
				return; // 메인메뉴로 돌아감.
			
			System.out.print(menu);
			String choice = sc.next();
			
			switch(choice) {
			case "1" : 
				System.out.print("> 수정할 이름을 입력하세요 : ");
				String name = sc.next();
				memberController.updateName(id, name);
				break;
			case "2" : 
				System.out.print("> 수정할 생일을 입력하세요(yyyy-mm-dd) : ");
				String _birthday = sc.next();
				Date birthday = Date.valueOf(_birthday);
				memberController.updateBirthday(id, birthday);
				break;
			case "3" : 
				System.out.print("> 수정할 이메일을 입력하세요 : ");
				String email = sc.next();
				memberController.updateEmail(id, email);
				break;
			case "0" : return;
			default : System.out.println("> 잘못 입력하셨습니다.");
			}
		}
	}

	/**
	 * 1건의 조회결과를 출력하는 메소드
	 */
	private void displayMember(Member member) {
		if(member == null) {
			System.out.println("> 조회된 회원이 없습니다.");
		} else {
			System.out.println("--------------------------------");
			System.out.println("아이디 : " + member.getId());
			System.out.println("이름 : " + member.getName());
			System.out.println("성별 : " + member.getGender());
			System.out.println("생일 : " + member.getBirthday());
			System.out.println("이메일 : " + member.getEmail());
			System.out.println("포인트 : " + member.getPoint());
			System.out.println("등록일 : " + member.getRegDate());
			System.out.println("--------------------------------");
		}
	}

	/**
	 * 아이디를 입력하는 메소드
	 */
	private String inputId() {
		System.out.print("> 아이디 : ");
		return sc.next();
	}

	/**
	 * n건의 조회결과를 출력하는 메소드
	 */
	private void displayMemberList(List<Member> memberList) {
		if(memberList.isEmpty()) {
			System.out.println("> 조회된 결과가 없습니다.");
		} else {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.printf("%s	%s	%s	%s	%s	%s	%s\n",
					"id","name","gender","birthday", "email", "point", "regDate");
			System.out.println("-----------------------------------------------------------------------------------");
			
			for(Member member : memberList) {
				System.out.printf("%s	%s	%s	%s	%s	%s	%s\n",
						member.getId(), member.getName(), member.getGender(), member.getBirthday(), member.getEmail(),
						member.getPoint(), member.getRegDate());
			}
			System.out.println("-----------------------------------------------------------------------------------");
		}
	}

	/**
	 * 회원가입 메소드
	 * - 사용자 입력값을 Member객체로 변환
	 */
	private Member inputMember() {
		System.out.println("> 회원 정보를 입력하세요.");
		System.out.print("> 아이디 : ");
		String id = sc.next();
		System.out.print("> 이름");
		String name = sc.next();
		System.out.print("> 성별(M/F) : ");
		String gender = sc.next();
		System.out.print("> 생일(yyyy-mm-dd) : ");
		String _birthday = sc.next();
		Date birthday = Date.valueOf(_birthday);
		System.out.print("> 이메일 : ");
		String email = sc.next();
		
		return new Member(id, name, gender, birthday, email, 0, null);
											// insert할 때 기본값 처리할 것이기 때문에
											// 0이나 null로 냅둠
	}
	
}
