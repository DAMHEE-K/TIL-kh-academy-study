package com.sh.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * - servlet class 만들기
 * - doPost 작성
 * - 사용자 입력값 출력
 * 
 * - /web
 * - context-path, context-root, app-name
 * - 언제 context-path를 경로에 포함시켜야 할까?
 * - jsp, html에서는 context-path부터 작성할 것(클라이언트 호출). /web/css/main.css, /web/testPerson.do
 * - xml, java에서는 context-path를 반드시 생략하고 작성할 것(서버내부사용). /testPerson.do, /servlet/view/testPerson.do
 */
public class TestPersonServlet3 extends HttpServlet {
	
	/*
	 * 비즈니스 로직과 뷰(사용자가 보는) 부분 분리함
	 * 비즈니스 로직 -> 서블릿
	 * 뷰 -> JSP
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		
		System.out.println("name = " + name);
		System.out.println("color = " + color);
		System.out.println("animal = " + animal);
		if(foods != null) 
			System.out.println("food = " + Arrays.toString(foods));	
		
		// 업무로직 (비즈니스 로직) - DB
		String result = " 당신은 멋쟁이!"; // DB로부터 가져온 result를 jsp에게 전달하고 싶다면,
		List<String> samples = Arrays.asList("선글라스", "선크림","수영복");
		String[] items = { "선글라스", "선크림", "수영복" };
		
		// servlet에서 jsp로 데이터를 전달하는 방법
		// HttpServletRequest 속성으로 저장하고, jsp에서 꺼내쓰면 된다.
		req.setAttribute("result", result); // 무조건 name:String, value:Object로 저장됨
		req.setAttribute("samples", samples);
		req.setAttribute("items", items);
		
		// 3. 응답 html작성을 jsp에게 위임
		
		// RequestDispatcher 는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할 또는
		// 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 클래스
		
		// path에서 /는 src/main/webapp 디렉토리를 가리키다.
		RequestDispatcher reqDispatcher = req.getRequestDispatcher("/servlet/view/testPerson3.jsp");
		reqDispatcher.forward(req, resp); // forward() 메소드는 대상 자원으로 제어를 넘기는 역할
		// 요청받은 req에 대한 응답 resp를 reqDispatcher(testPerson3.jsp)에게 넘기겠다
	}
}
