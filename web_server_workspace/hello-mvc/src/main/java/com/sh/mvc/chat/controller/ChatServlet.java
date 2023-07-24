package com.sh.mvc.chat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/chat/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전체 채팅방
		String chatroomId = "global";
		request.getSession().setAttribute("chatroomId", chatroomId);
		
		
		
		request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp").forward(request, response);
	}


}
