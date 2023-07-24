package com.sh.app.el;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.app.item.domain.Item;


@WebServlet("/el/basics")
public class ElBasicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		
		// 2. 업무로직 요청
		
		// jsp에서 사용할 data 설정
		// request 객체 : HttpServletRequest
		request.setAttribute("name", "홍길동");
		request.setAttribute("age", 33);
		request.setAttribute("married", true);
		
		request.setAttribute("item", new Item(1, "아이스 아메리카노", 2500));
		
		request.setAttribute("names", Arrays.asList("신사임당", "이순신", "이몽룡", "세종대왕"));
		request.setAttribute("map", Map.of("name", "하종수", "age", 44, "married", false));
		request.setAttribute("book", "나미야 잡화점의 기적");
		
		// session 객체 : HttpSession 객체
		HttpSession session = request.getSession();
		session.setAttribute("book", "죽음의 수용소에서");
		
		// application 객체 : ServletContext
		ServletContext application = getServletContext();
		application.setAttribute("movie", " 오펜하이머");
		
		
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/el/basics.jsp")
			.forward(request, response);
	}


}
