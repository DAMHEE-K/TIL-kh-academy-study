package com.sh.app.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.exception.MethodNotAllowedException;

/**
 * 추상메소드가 없어도 추상 클래스를 만들 수 있다
 * - 규격만 제공하려는 경우
 * - 현재 클래스를 객체화하는 것을 방지하고 싶을 때
 */
public abstract class AbstractController {
	
	public String doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		throw new MethodNotAllowedException("GET");
		
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET");
		return null;
	}
	
	public String doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		throw new MethodNotAllowedException("POST");
		
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "POST");
		return null;
	}
}
