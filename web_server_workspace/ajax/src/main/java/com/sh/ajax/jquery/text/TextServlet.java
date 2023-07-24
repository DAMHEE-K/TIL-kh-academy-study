package com.sh.ajax.jquery.text;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
동기식 : 신용카드 결제 (서버와 통신해서 응답을 받을때 까지 움직이거나 벗어날 수 없음)
비동기식 : 서버와 통신하면서도 여러 이벤트를 발생시킬 수 있음

ajax는 웹 전체를 reload할 필요 없기 때문에 비용이나 데이터 적어짐, 
사용자도 현재 보고있는 context를 계속 보고있을 수 있는 장점이 있다

$.ajax(settings)
제이쿼리를 이용한 ajax 통신의 가장 기본적인 API
주요속성
data : 서버에 전송할 테이터(키-벨류 형식의 객체), 
dataType : 서버가 리턴하는 데이터 타입(xml, json, html, script)
type : 서버가 리턴하는 데이터의 타입(POST, GET)
url : 데이터를 전송할 URL (서블릿)
success : ajax 통신에 성공했을 때 호출될 이벤트 핸들러
 */
@WebServlet("/jquery/text")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		response.setCharacterEncoding("utf-8");
		response.getWriter()
			.append("Hello world")
			.append("너의 이름은 " + name + "이고, ")
			.append("나이는 " + age + "살입니다.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");

		// 1. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		System.out.println("memberId = " + memberId + ", password = " + password);
		// 2. 업무로직
		response.getWriter()
			.append(memberId + "님, 회원가입을 축하합니다.");
		
	}

}
