package com.practice.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.practice.member.model.vo.Product;


@WebServlet("/product/productCreate")
public class ProductCreatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1.  사용자 입력값 처리
		String prodName = request.getParameter("prod-name");
		String prodImg = request.getParameter("prod-img");
		
		Product prod = new Product();
		
		prod.setProdName(prodName);
		prod.setProdImg(prodImg);
		
		// 2. 업무로직
		int result = productService.insertProduct(prod);
		
		HttpSession session = request.getSession();
		session.setAttribute("result", "성공");
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
