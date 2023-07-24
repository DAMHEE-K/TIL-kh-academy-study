package com.sh;

import com.sh.member.view.MemberMenu;

public class Run {

	public static void main(String[] args) {
		new MemberMenu().mainMenu(); // stack 메모리에 main()가 올라감
									// main()가 MemberMenu객체가 가지고 있는 mainMenu메소드를 호출함.
		
		System.out.println("------ 😆이용해주셔서 감사합니다😆 -----");
	}

}
