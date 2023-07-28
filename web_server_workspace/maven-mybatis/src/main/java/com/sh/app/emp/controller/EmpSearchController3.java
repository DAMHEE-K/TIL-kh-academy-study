package com.sh.app.emp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.controller.AbstractController;
import com.sh.app.emp.entity.EmpDto;
import com.sh.app.emp.service.EmpService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmpSearchController3 extends AbstractController {
	private final EmpService empService;
	
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		// mtbatis.foreach 태그의 collection 속성에는 반복 접근 가능한 객체면 모두 가능함
		// T[], List<T>, Set<T>, Map<K, V> 모두 가능
		String[] _jobCodes = request.getParameterValues("jobCode");

		List<String> jobCodes = null;
		if(_jobCodes != null) {
			jobCodes = Arrays.asList(_jobCodes); // String 배열을 List로 바꿔주는 메소드 적용
		}
		
		Map<String, Object> filters = new HashMap<>();
		filters.put("jobCodes", jobCodes);

		List<EmpDto> emps = empService.search3(filters);
		request.setAttribute("emps", emps);
		
		return "emp/search3";
	}
}
