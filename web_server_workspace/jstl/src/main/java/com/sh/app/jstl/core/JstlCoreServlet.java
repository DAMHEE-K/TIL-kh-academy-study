package com.sh.app.jstl.core;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.item.domain.Item;


@WebServlet("/jstl/core")
public class JstlCoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> names = Arrays.asList("홍길동", "신사임당", "이순신", "강감찬");
		List<Item> items = Arrays.asList(
				new Item(1, "삼성키보드", 20_000),
				new Item(2, "한성키보드", 35_000),
				new Item(3, "레오폴드키보드", 25_000)
		);
		// Map.of는 내용 변경 불가능함!
		Map<String, Object> map = Map.of(
				"name", "홍길동",
				"age", 33,
				"married", true
		);
		request.setAttribute("names", names);
		request.setAttribute("items", items);
		request.setAttribute("map", map);
				
		request.getRequestDispatcher("/jstl/core/core.jsp")
			.forward(request, response);
	}

}
