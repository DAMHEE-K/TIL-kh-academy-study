package com.sh.mvc.member.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Member;


@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private final MemberService memberService = new MemberService();
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 1. 사용자 입력값 처리
		HttpSession session =  request.getSession();
		Member delMember = (Member)session.getAttribute("loginMember");
		String memberId = delMember.getMemberId();
		
		// 2. 서비스 로직 호출
		int result = memberService.deleteMember(delMember);
		
		// 모든 속성을 종료하기
		// 열거형은 반복문 처리 가능
		Enumeration<String> names = session.getAttributeNames(); // 저장된 모든 속성명 열람가능한 타입으로 반환
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			session.removeAttribute(name);
		}
		
		// 쿠키 삭제
		Cookie cookie = new Cookie("saveId", memberId);
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie); // Set-Cookie 응답헤더에 전송
		
		// 리다이렉트
		session.setAttribute("msg", "이용해주셔서 감사합니다.");
		response.sendRedirect(request.getContextPath() + "/");
	}
}
