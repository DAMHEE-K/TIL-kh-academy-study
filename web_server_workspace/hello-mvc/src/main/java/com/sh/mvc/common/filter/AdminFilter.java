package com.sh.mvc.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.vo.Member;
import com.sh.mvc.member.model.vo.MemberRole;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
       
    public AdminFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		HttpSession session =  httpReq.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember"); // 현재 session객체에 저장된 loginMember 속성을 불러옴
																		  // 불러온 member객체를 loginMember에 저장
		
		// 만약 loginMember가 null 이거나 (로그인이 되어있지 않거나)
		// 현재 로그인한 member 객체의 memberRole()이 'A'가 아니면 (일반 유저이면)
		if(loginMember == null && (loginMember.getMemberRole() != MemberRole.A)) {
																// enum(열거형) 클래스의 경우 == 비교 연산이 가능함
																// equals("U")로 해도 문제는 없음
			session.setAttribute("msg", "관리자만 접근 가능한 페이지입니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/"); // 인덱스 페이지로 보냄
			return;
		} 
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
