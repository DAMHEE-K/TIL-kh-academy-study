package com.sh.member.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import com.sh.member.controller.MemberController;
import com.sh.member.model.dto.Address;
import com.sh.member.model.dto.Member;

public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	
	// 의존객체 (view는 dao에 접근하기 위해서 controller가 반드시 필요하기 때문에
	// MemberController를 의존하고 있다고 한다
	private MemberController memberController = new MemberController();
	
	public void mainMenu() {
		
		String menu = "===== 회원관리 프로그램 =====\n" //메뉴 선택용 문자열
                + "1. 전체조회\n"
                + "2. 아이디조회\n"
                + "3. 이름검색\n"
                + "4. 회원가입\n"
                + "5. 회원정보변경\n"
                + "6. 회원탈퇴\n"
                + "7. 탈퇴회원조회\n"
                + "8. 회원주소조회\n"
                + "0. 프로그램종료\n"
                + "=========================\n"
                + "선택 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next(); // 사용자가 menu를 보고 숫자를 선택함
			
			List<Member> memberList = null;
			Member member = null;
			int result = 0;
			String id = null;
			String name = null;
			
			switch(choice) { // 선택한 숫자에 따라서 호출되는 메소드가 다 다름
							// DB에서 데이터를 가져와야 함.
							// 접근은 view - controller - service - dao - DB 순서이기 때문에,
							// 지금은 view 화면이니까 차례대로 넘어가면서 findAll메소드를 호출할 것임!
			case "1" : 
				memberList = memberController.findAll(); 
				displayMemberList(memberList);
				break;
			case "2" : 
				id = inputId();
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
				displayResult("회원가입", result);
				break;
			case "5" : 
				updateMenu();
				break;
			case "6" : 
				id = inputId();
				result = memberController.deleteMember(id);
				displayResult("회원탈퇴", result);
				break;
			case "7" :
				memberList = memberController.findDelMember();
				displayMemberList(memberList);
				break;
			case "8" :
				id = inputId();
				List<Address> addressList = memberController.findMemberAddressByMemberId(id);
				displayMemberAddressList(addressList);
				break;
			case "0" : return; // 현재 메소드를 호출한 곳으로 return
			default: System.out.println("> 잘못 입력하셨습니다.");
			}
		}
	}

	
	/**
	 * 회원 주소록 출력 메소드
	 */
	private void displayMemberAddressList(List<Address> addressList) {
		if(addressList == null || addressList.isEmpty()) {
			System.out.println("> 해당 회원은 등록된 주소가 없습니다.");
		} else {
			System.out.println("---------------------------------------------------------------");
			System.out.printf("%10s %10s %-30s\n", "이름", "기본", "주소");
			System.out.println("---------------------------------------------------------------");
			for(Address addr : addressList) {
				System.out.printf("%10s %10s %-30s\n", addr.getMember().getName(),
						addr.isDefaultAddr(), addr.getAddress());
				System.out.println("---------------------------------------------------------------");
			}
		}
	}

	private void updateMenu() {
		String menu = "+++++++++ 회원 정보수정 +++++++++\n"
				+ "1. 이름수정\n"
				+ "2. 생일수정\n"
				+ "3. 이메일수정\n"
				+ "0. 메인메뉴로 돌아가기\n"
				+ "++++++++++++++++++++++++++++++\n"
				+ "선택 : ";
	
	String id = inputId();
	
	while(true) {
		Member member = memberController.findById(id);
		if(member == null) {
			System.out.println("> 조회된 회원이 없습니다.");
			return;
		}
		else {
			displayMember(member);
		}
		
		System.out.print(menu);
		String choice = sc.next();
		String colName = null;
		Object newValue = null; // String, Date 동시에 처리하기 위해서
		
		switch(choice) {
		case "1" : 
			System.out.print("변경할 이름 : ");
			colName = "name";
			newValue = sc.next();
			break;
		case "2" : 
			System.out.print("변경할 생일 (19990909) : ");
			LocalDate temp = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("yyyyMMdd"));
			colName = "birthday";
			newValue = Date.valueOf(temp);
			break;
		case "3" : 
			System.out.print("변경할 이메일 : ");
			colName = "email";
			newValue = sc.next();
			break;
		case "0" : return;
		default : 
			System.out.println("잘못 입력하셨습니다.");
			continue;
		}
		
		int result = memberController.updateMember(id, colName, newValue);
		System.out.println(result > 0 ? "> 회원정보 수정 성공!" : "> 회원정보 수정 실패!");
	}
	
}
		


	private String inputName() {
		System.out.print("> 이름 입력 : ");
		return sc.next();
	}

	/**
	 * 아이디를 입력받는 메소드
	 * @return
	 */
	private String inputId() {
		System.out.print("> 아이디 입력 : ");
		return sc.next();
	}

	/**
	 * DML 처리 결과를 출력하는 메소드
	 */
	private void displayResult(String name, int result) {
		if(result > 0)
			System.out.println(name + "성공!");
		else
			System.out.println(name + "실패!");
	}

	/**
	 * 자바 객체
	 * 1. 매개변수 생성자
	 * 2. 기본생성자 + setter 값 대입
	 */
	private Member inputMember() {
		Member member = new Member();
		List<Member> memberList = null;
		String id = null;
		System.out.println("🔹 회원 정보를 입력하세요 🔹");
		
		while(true) {
			System.out.print("> 아이디 : ");
			id = sc.next();
			
			memberList = memberController.findAll();
			
			boolean flag = true;
			for(Member mem : memberList) {
				if(mem.getId().equals(id)) 
					flag = false;
			}
			
			if(!flag) {
				System.out.println("> 사용불가능한 아이디입니다. 다시 입력하세요.");
			} else {
				System.out.println("사용 가능한 아이디입니다. :)");
				member.setId(id);
				break;
			}
		}
		System.out.print("> 이름 : ");
		member.setName(sc.next());
		System.out.print("> 성별(M/F) : ");
		member.setGender(sc.next());
		System.out.print("> 생일(19900909) : ");
		String temp = sc.next();
		
		// Calendar 객체 생성 -> java.sql.Date 변환
		int year = Integer.parseInt(temp.substring(0, 4)); //1990 (마지막 인덱스 포함X)
		int month = Integer.parseInt(temp.substring(4, 6));
		int date = Integer.parseInt(temp.substring(6));
		Calendar cal = new GregorianCalendar(year,month-1,date); // 0~11월
		long milis = cal.getTimeInMillis(); // utc초 - 1970년 1월 1일 자정 이후로 누적된 밀리초 반환
		Date birthday = new Date(milis);
		member.setBirthday(birthday);
		
		System.out.print("> 이메일 : ");
		member.setEmail(sc.next());
		
		sc.nextLine();
		
		System.out.print("> 주소 : ");
		Address address = new Address();
		address.setMember(member);
		address.setAddress(sc.nextLine());
		member.addAddress(address);
		
		return member;
	}
	
	/**
     * n건의 조회결과를 출력하는 메소드
     * @param memberList
     */
    private void displayMemberList(List<Member> memberList) {
        if(memberList == null || memberList.isEmpty()) {
            System.out.println("> 조회된 결과가 없습니다.");
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%s    %s    %s    %s    %s    %s    %s\n", 
                              "id", "name", "gender", "birthday", "email", "point", "regDate");
            System.out.println("-------------------------------------------------------------");
            for(Member member : memberList) {
                System.out.printf("%s    %s    %s    %s    %s    %s    %s\n", 
                          member.getId(), member.getName(), member.getGender(),
                          member.getBirthday(), member.getEmail(), member.getPoint(), member.getRegDate());
            }
            System.out.println("-------------------------------------------------------------");
            
        }
    }
    
    private void displayMember(Member member) {
    	if(member == null) {
    		System.out.println("> 조회된 결과가 없습니다.");
    	} else {
    		System.out.println("-------------------------------------------------------------");
            System.out.printf("%s    %s    %s    %s    %s    %s    %s\n", 
                              "id", "name", "gender", "birthday", "email", "point", "regDate");
            System.out.println("-------------------------------------------------------------");
            System.out.printf("%s    %s    %s    %s    %s    %s    %s\n", 
                          member.getId(), member.getName(), member.getGender(),
                          member.getBirthday(), member.getEmail(), member.getPoint(), member.getRegDate());
            
            List<Address> addressList = member.getAddressList();
            
            if(addressList != null && !addressList.isEmpty()) {
            	for(Address addr : addressList)
            		System.out.println("주소 : " + addr.getAddress()
            		+ (addr.isDefaultAddr()? "(기본)" : " "));
            }
            System.out.println("-------------------------------------------------------------");
    	}
    }
    
    /* 선생님의 코드 (아이디 중복 검사)
	private String checkIdDuplicate() {
        String id = null;
        do {
            if(id != null)
                System.out.printf("> [%s]는 이미 사용중입니다.%n", id);
            System.out.print("아이디 : ");
            id = sc.next();
        } while(memberController.findById(id) != null);
        return id;
    }
    */

}
