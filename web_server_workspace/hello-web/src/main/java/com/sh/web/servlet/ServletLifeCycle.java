package com.sh.web.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * GET /web/servletLifeCycle.do를 처리하기 위한 서블릿
 * 
 * Servlet interface
 *   - GenericServlet abstract class
 *      - HttpServlet abstract class
 *        - CustomServlet class (사용자가 직접 만드는 그 서블릿)
 * 
 *  ~ 서블릿 생명주기 ~
 *  - 싱글톤 패턴으로 관리됨 (하나의 객체만 생성해서 재사용)
 * 1. 첫번째 요청 시 객체 생성
 * 2. @PostConstruct 메소드 호출
 * 3. init(ServletConfig) 설정 메소드 호출
 * 4. GenericServlet#service - 메소드별 핸들러 (doGet/doPost/doPatch/doDelete...) 호출
 * 5. 서버를 종료하면 destory 메소드 호출
 * 6. @PreDestroy 메소드 호출
 * 7. 자원반납
 */
@WebServlet("/servletLifeCycle.do")
public class ServletLifeCycle extends HttpServlet{
	
	public ServletLifeCycle() {
		System.out.println("[생성자 호출!]");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("[@PostConstruct 호출!]");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("[init 호출! " + config + "]");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[doGet 호출!]");
	}
	
	@Override
	public void destroy() {
		System.out.println("[destroy 호출!]");
	}
	
	@PreDestroy
	public void PreDestroy() {
		System.out.println("[@PreDestroy 호출!]");
	}
}
