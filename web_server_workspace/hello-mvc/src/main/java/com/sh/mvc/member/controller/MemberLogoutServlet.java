package com.sh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션ID등이 유효하지 않은 경우, 새로 만들지 말고 null을 반환해라
		HttpSession session = request.getSession(false);
		if(session != null)
			session.invalidate(); // 로그아웃 할거니까 세션 자체를 무효화 (destroyed)
		
		// forward(html) | redirect(url변경)
		// url이 남아있으면 문제가 생길 수 있는 경우 redirect 해야함
		//  Ex) 회원가입의 경우, url이 남아있는 상태에서 새로고침을 하면 똑같은 요청이 또 db에 들어감
		// 그럼 PK 제약조건이 걸려 오류가 나는 상황이 발생할 수 있음
		// 이러한 경우는 방지하기 위해서 redirect 해줘야 함
		
		response.sendRedirect(request.getContextPath() + "/");
		// 새로운 요청이 redirect되며 session이 다시 생성됨(created)
	}

}
