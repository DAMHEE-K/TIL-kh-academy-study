package com.sh.web.servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menuOder.do")
public class MenuOrderServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		int price = 0;
		String mainMenu = req.getParameter("mainMenu");
		String sideMenu = req.getParameter("sideMenu");
		String drinkMenu = req.getParameter("drinkMenu");
		
		Map<String, Integer> map = new HashMap<>();
		map.put("한우버거", 5000);
		map.put("밥버거", 4500);
		map.put("치즈버거", 4000);
		map.put("감자튀김", 1500);
		map.put("어니언링", 1700);
		map.put("콜라", 1000);
		map.put("사이다", 1000);
		map.put("커피", 1500);
		map.put("밀크쉐이크", 2500);
		
		price = (map.get(mainMenu) + map.get(sideMenu) + map.get(drinkMenu));
		
		req.setAttribute("price", price);
		
		RequestDispatcher reqDispatcher = req.getRequestDispatcher("/menu/menuOrder.jsp");
		reqDispatcher.forward(req, resp);
	}
}
