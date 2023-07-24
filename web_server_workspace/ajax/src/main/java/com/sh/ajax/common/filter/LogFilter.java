package com.sh.ajax.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter : Client로 부터 Server로 요청이 들어오기 전에 서블릿을 거쳐서 필터링 하는 것
 * 공통적인 기능들을 서블릿이 호출되기 전에 수행(전처리)되게 하고 싶거나
 * 서블릿이 호출 되고 난 뒤에 수행(후처리) 하고 싶으면 공통적인 기능들을 서블릿 필터로 구현하면 된다.
 * 
 * Filter 작성하기
 * - Filter인터페이스 구현
 * - doFilter 메소드 오버라이드
 * 
 * - 서블릿 호출 전 작업
 * - 응답 메세지 발송 전(jsp 작업 이후) 작업
 * 
 * 처리되는 필터의 순서를 변경하려면 web.xml에 등록
 * - 필터 처리 순서
 * - 1. web.xml에 등록된 순서
 * - 2. @webFilter로 등록된 클래스
 */
@WebFilter("/*") // 그 어떤 요청 이전/이후에 무조건 시행
public class LogFilter implements Filter{

	/**
	 * ServletRequest는 HttpServletRequest의 부모타입. HttpServletRequest로 다운캐스팅 가능
	 * ServletResponse는 HttpServletResponse의 부모타입. HttpServletResponse로 다운캐스팅 가능
	 * FilterChain 필터 묶음 객체. FilterChain#doFilter를 호출하면, 다음 필터 또는 서블릿으로 연결
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 서블릿 가기 전 작업 공간
		HttpServletRequest httpReq = (HttpServletRequest) request;
		String uri = httpReq.getRequestURI();
		String method = httpReq.getMethod(); // 전송 방식을 알려주는 메소드 (Get, post)
		
		System.out.println("===================================================");
		System.out.printf("%s %s\n", method, uri);
		System.out.println("---------------------------------------------------");
		
		
		chain.doFilter(request, response);
		// 응답 메세지 발송 전 작업 공간
		
		HttpServletResponse httpRes = (HttpServletResponse) response;
		int status = httpRes.getStatus(); // http status (404, 200 같은것들)
		System.out.println("____________________________________________________");
		System.out.println(status); 
		System.out.println("____________________________________________________");

	}

}
