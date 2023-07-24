package com.sh.ajax.javacript;

import java.io.IOException;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavascriptTestServlet
 */
@WebServlet("/javascript/test")
public class JavascriptTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("doGet : name = " + name + ", age = " + age);
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Hello world").append(age + "살" + name + "🎂");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
//		throw new RuntimeException("메롱~~");
	}

}
