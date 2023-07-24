package com.sh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.vo.Member;

/**
 * Servlet implementation class MemberDatailServlet
 */
@WebServlet("/member/memberDetail")
public class MemberDatailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 0. 로그인 여부 확인
//		HttpSession session = request.getSession(); // 현재 요청과 연결되어 있는 세션을 받아와 session 객체 만들기
//		Member loginMember = (Member)session.getAttribute("loginMember"); // session객체에서 loginMemer라는 속성값을 찾아서 loginMember변수에 저장
//							// Member 타입의 변수 member에 저장해야하니까 강제 형변환
//		
//		if(loginMember == null) { // loginMember가 참조하는 값이 null이면
//			session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
//			response.sendRedirect(request.getContextPath() + "/");
//			return;
//		}
		
		// 1. 입력값 처리
		// 2. 업무로직
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
			.forward(request, response);
	}


}
