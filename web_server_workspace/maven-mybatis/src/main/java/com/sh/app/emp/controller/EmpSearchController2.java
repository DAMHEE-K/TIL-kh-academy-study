package com.sh.app.emp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.controller.AbstractController;
import com.sh.app.emp.entity.Emp;
import com.sh.app.emp.entity.EmpDto;
import com.sh.app.emp.service.EmpService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmpSearchController2 extends AbstractController {
	private final EmpService empService;
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		Integer salary = null;

		try {
			salary = Integer.parseInt(request.getParameter("salary"));
		} catch(NumberFormatException ignore) {}
		
		String salaryCompare = request.getParameter("salaryCompare");
		
		Map<String, Object> filters = new HashMap<>();
		filters.put("gender", gender);
		filters.put("salary", salary);
		filters.put("salaryCompare", salaryCompare);
		
		// 업무로직
		List<EmpDto> emps = empService.search2(filters);
		request.setAttribute("emps", emps);
		return "emp/search2";
	}
}
