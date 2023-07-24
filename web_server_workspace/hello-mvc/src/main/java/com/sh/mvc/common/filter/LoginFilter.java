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

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({ "/member/memberDetail", "/member/memberUpdate", "/member/memberDelete","/board/boardCreate", "/chat/chat" })
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("[Login 체크중...]");
		
		// ServletRequest는 HttpServletRequest의 부모타입. HttpServletRequest로 다운캐스팅 가능
		// ServletResponse는 HttpServletResponse의 부모타입. HttpServletResponse로 다운캐스팅 가능
		
		// 0. 로그인 여부 확인
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		
		HttpSession session =  httpReq.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember"); // session객체에서 loginMemer라는 속성값을 찾아서 loginMember변수에 저장
							// Member 타입의 변수 member에 저장해야하니까 강제 형변환
		
		if(loginMember == null) { // loginMember가 참조하는 값이 null이면
			session.setAttribute("msg", "로그인 후 이용하실 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/");
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
