package com.sh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GET /web/testPerson1.do
 * - servlet 클래스 : 웹 요청을 처리할 수 있는 자바 클래스
 * - HttpServlet 추상클래스를 상속
 * - GET 방식으로 요청을 처리항다면 doGet 오버라이드하고,
 * - POST 방식으로 요청을 처리한다면 doPost 오버라이드
 * 
 * - HttpServletRequest : 요청 관련 정보를 가진 객체 (요청헤더, 사용자 입력값, 쿠키 등등)
 * - HttpServletResponse : 응답 관련 정보를 가진 객체 (응답메세지 출력을 위한 스트림객체, 응답헤더 등등)
 * 
 * - 특정 주소와 Servlet 클래스 연결하기
 * 1. web.xml 작성
 * 2. @webServlet 작성방식
 */
public class TestPersonServlet1 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 사용자 입력값 확인 - 사용자 입력값은 모두 문자열로 처리된다
		// getParameter() : 넘어온 값이 하나
		// getParameterValues() : 넘어온 값이 여러개
		
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food"); // 체크박스같이 여러개 넘어오는 건 배열로 받아온다
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);

		if(foods != null) { // nullPointException이 나올 수 있기 때문에
			for(int i=0; i<foods.length; i++) {
				System.out.println("foods = " + foods[i]);
			}
		}
		
		// 2. 응답 html 출력
		// 응답 헤더
		resp.setContentType("text/html; charset=utf-8");
		// 응답 출력스트림객체
		PrintWriter out = resp.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>개인취향검사결과 GET</title>");
		out.println("<link rel='stylesheet' href='/web/css/testPerson.css'/>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>개인취향검사결과 GET</h1>");
		out.println("<p class='name'>이름 : " + name + "</p>");
		out.println("<p class='color'>선호 색상: " + color + "</p>");
		out.println("<p class='animal'>선호 동물 : " + animal + "</p>");
		if(foods == null) {
			out.println("<p>선호 음식 : 없음</p>");			
		}
		else {
			out.println("<p class='food'>선호 음식 : " + String.join(" / ", foods) + "</p>");						
		}
		out.println("</body>");
		out.println("</html>");
	}
}
