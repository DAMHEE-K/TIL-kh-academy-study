package com.sh.ajax.jquery.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.ajax.celeb.model.service.CelebService;
import com.sh.ajax.celeb.model.vo.Celeb;

/**
 * Servlet implementation class JsonCelebFindAllServlet
 */
@WebServlet("/jquery/json/celeb/findAll")
public class JsonCelebFindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CelebService celebService = new CelebService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		
		// 2. 업무로직
		List<Celeb> celebs = celebService.findAll();
		System.out.println("celebs = " + celebs);
		
		// 3. 응답처리 json (java -> json)
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson(); // Gson으로 파싱하기 위한 객체 생성
		
		// Set List [] ---> json [] 배열로
		// Map vo ---> json {} 객체로
		
		String jsonStr = gson.toJson(celebs); // celebs List를 json 배열로 변환
		System.out.println("jsonStr = " + jsonStr);
		
		response.getWriter().append(jsonStr); // json으로 응답하면 제이쿼리쪽에서 js 객체로 변환해줌
	}



}
