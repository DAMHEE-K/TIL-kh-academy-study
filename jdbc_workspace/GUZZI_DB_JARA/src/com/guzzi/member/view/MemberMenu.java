package com.guzzi.member.view;

import java.util.List;
import java.util.Scanner;

import com.guzzi.member.controller.MemberController;
import com.guzzi.member.model.dto.Member;
import com.guzzi.member.model.vo.OrderList;


public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	Member loginMember = null;
	List<OrderList> orderList = null;

	
	/**
	 * 로그인 시 보이는 멤버 메뉴
	 */
	public void memberMenu(Member member) {
		loginMember = member;
		String id = loginMember.getId();
		int result = 0;

		while (true) {
			String menu = "\n                [ " +loginMember.getName() + " ] 님 환영합니다.\n"
					+ "⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂ 멤버 메뉴 ⠂⠁⠈⠂⠄⠄⠂⠁⠁⠂⠄⠄⠂⠁⠁⠂\n" + "1. 구매하기\n" + "2. 회원 정보 변경\n" + "3. 캐시충전\n" + "4. 주문리스트 보기\n"
					+ "5. 회원탈퇴\n" + "6. 내 정보 조회\n" + "0. 프로그램 종료\n" + "메뉴를 선택하세요 : ";
			System.out.print(menu);
			String num = sc.next();

			switch (num) {
			case "1":
				buyProduct(loginMember);
				break;
			case "2":
				result = updateMemu(loginMember);
				break;
			case "3":
				deposit(loginMember);
				break;
			case "4":
				orderList = memberController.orderList(id);
				displayOrderList(orderList);
				break;
			case "5":
				result = deleteMember(loginMember);
				if (result > 0)
					return;
				break;
			case "6":
				loginMember = memberController.findMember(loginMember.getId());
				displayMemberInfo(loginMember);
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}


	/**
	 * 멤버 정보 출력하는 메소드
	 */
	private void displayMemberInfo(Member loginMember) {
		if(loginMember == null) {
			System.out.println("> 조회된 회원이 없습니다.");
		} else {
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿"+loginMember.getId() +"님 회원 조회" +"＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.printf("%-20s %-10s %-30s %-20s\n", "ID", "이름", "주소", " 캐시");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");

			System.out.printf("%-20s %-10s %-30s [%s 원]\n", loginMember.getId(), loginMember.getName(),
					loginMember.getLocation(), loginMember.getMoney());
			System.out.println();
		}
	}

	/**
	 * 주문 내역 확인창을 출력하는 메소드
	 */
	private void displayOrderList(List<OrderList> orderList) {
		if (orderList == null || orderList.isEmpty()) {
			System.out.println("> 등록된 상품이 없습니다.");
		} else {
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ 구매 상품 조회 ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
			System.out.printf("%-10s %-10s %-10s %-20s %s, %-10s\n", "주문번호", "상품번호", "이름", "주소지", "수량", "주문날짜");
			System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");

			for (OrderList order : orderList) {
				String date = String.valueOf(order.getSaleDate());
				System.out.printf("%-10s %-10s %-10s %-20s %d, %-10s\n", order.getOrderNum(), order.getItemNum(),
						order.getName(), order.getLocation(), order.getAmount(), date);
			}
		}
	}

	/**
	 * 캐시 충전 메소드
	 */
	private int deposit(Member member) {
		loginMember = member;
		int result = 0;
		System.out.print("> 아이디를 입력하세요 : ");
		String id = sc.next();

		if (!id.equals(loginMember.getId()))
			System.out.println("본인의 아이디를 입력해주세요.");
		else {
			sc.nextLine();
			System.out.print("> 충전할 캐시 : ");
			int money = sc.nextInt();
			result = memberController.deposit(id, money);
			System.out.println(result > 0 ? "> 캐시충전 성공!" : "> 캐시충전 실패!");
			System.out.println("> 멤버 메뉴로 돌아갑니다....");
		}
		return result;
	}


	/**
	 * 상품 구매 메뉴 출력 메소드
	 */
	private void buyProduct(Member member) {
		loginMember = member;
		int result = 0;
		boolean keepShopping = true;
		String itemNum = null;

		String menuCase1 = "\n" + "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿ 👕👖🩳 상품을 선택 하세요 👕👖🩳 ＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n" + "1. 트렌치코트 (가격 : 73,000원)\n" + "2. 티셔츠 (가격 : 13,000원)\n"
				+ "3. 모자 (가격 : 14,500원)\n" + "4. 카고팬츠 (가격 : 170,000원)\n" + "5. 와이드팬츠 (가격 : 73,900원)\n"
				+ "6. 양말 (가격 : 12,000원)\n" + "7. 쇼핑 종료하기\n" + "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n"
				+ "구매할 제품번호 : ";

		while (keepShopping) {
			System.out.print(menuCase1);
			int product = sc.nextInt();
			switch (product) {
			case 1:
				itemNum = "A736";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 트렌치 코트");
				break;
			case 2:
				itemNum = "A651";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 티셔츠");
				break;
			case 3:
				itemNum = "B065";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 모자");
				break;
			case 4:
				itemNum = "C327";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 카고팬츠");
				break;
			case 5:
				itemNum = "C960";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 와이드팬츠");
				break;
			case 6:
				itemNum = "B432";
				result = memberController.insertOrderList(member, itemNum);
				System.out.println("구매물품 : 양말");
				break;
			case 7:
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			} // switch 끝
			if (result > 0)
				checkLocation(loginMember);

			System.out.println("쇼핑을  계속하시겠습니까? => Y" + "\n 그만두기 => 아무키나 입력하세요");
			String check = sc.next().toUpperCase();
			if (check.equals("Y")) {
				keepShopping = true;
			} else {
				System.out.println("메인 화면으로 이동합니다.");
				keepShopping = false;
			}
		} // while문 끝
		return;
	}
	
	/**
	 * 주소지 확인 메소드
	 */
	private void checkLocation(Member member) {
		loginMember = member;
		System.out.println("> 배송 받으실 주소를 확인하세요");
		System.out.println(" 🏚 [ "+ loginMember.getLocation() + " ] 🏚 ");
		System.out.print("> 위 주소가 맞다면 Y 를 입력해주세요. 다른 주소를 입력하시려면 아무키나 누르세요. : ");
		String checkLocation = sc.next().toUpperCase();
		if (checkLocation.equals("Y")) {
			System.out.println("\n 🏚 주소지 : [ " + member.getLocation() + " ] (으)로 배송합니다.");
		} else {
			sc.nextLine();
			System.out.print("> 배송지 정보를 변경합니다. :");
			String newLocation = sc.nextLine();
			memberController.updateMember(loginMember.getId(), "location", newLocation);
			System.out.println(" 🏚 입력하신 주소 : [ " + newLocation + " ] 로 배송합니다");
		}
	}
	
	
	
	
	/**
	 * 회원 정보 수정 메소드
	 */
	private int updateMemu(Member member) {
		int result = 0;
		loginMember = member;
		
		String menu = "✎＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿회원 정보수정 \n" 
				+ "1. 이름수정 \n" 
				+ "2. 주소수정 \n" 
				+ "0. 메인메뉴로 돌아가기\n"
				+ "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿\n" 
				+ "선택 : ";

		while (true) {
			System.out.print(menu);
			String choice = sc.next();
			sc.nextLine();
			
			String colName = null;
			Object newValue = null;
			String id = loginMember.getId();
			
			switch (choice) {
			case "1":
				System.out.print("🔹 변경할 이름 : ");
				colName = "name";
				newValue = sc.next();
				break;
			case "2":
				System.out.println("🔹 변경할 주소 :");
				colName = "location";
				newValue = sc.nextLine();
				break;
			case "0":
				return result;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
				continue;
			}
			if(!choice.equals("0"))
			result = memberController.updateMember(id, colName, newValue);
			loginMember = memberController.findMember(id);
			
			if (result > 0) {
				displayUpdateInfo(loginMember);
			}
			
			return result;
		}
	}

	/**
	 * 회원 정보 수정 확인 메소드
	 */
	private void displayUpdateInfo(Member loginMember) {
		System.out.println("회원정보 수정성공!");
		loginMember = memberController.findMember(loginMember.getId());
		System.out.println("변경된 정보 : [" + loginMember.getName() + "]님의 주소지는 [" + loginMember.getLocation() + "] 입니다.");
	}
	
	
	/**
	 * 회원 탈퇴 메소드
	 */
	private int deleteMember(Member member) {
		loginMember = member;
		int result = 0;
		System.out.println("> 비밀번호를 입력하세요 : ");
		String pw = sc.next();
		result = memberController.deleteMember(loginMember, pw);
		System.out.println(result > 0 ? "> 회원삭제 성공!" : "> 회원삭제 실패!");
		return result;
	}

}
