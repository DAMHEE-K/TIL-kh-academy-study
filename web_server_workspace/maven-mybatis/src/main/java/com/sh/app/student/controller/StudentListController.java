package com.sh.app.student.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.controller.AbstractController;
import com.sh.app.student.entity.Student;
import com.sh.app.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentListController extends AbstractController {
	
	// final은 상수이기 때문에 반드시 StudentListContoller 객체를 만들 때 세팅이 무조건 필요함
	private final StudentService studentService;
	
	// 생성자를 이용한 의존성 주입(DI)
//	public StudentListController(StudentService studentService) {
//		this.studentService = studentService;
//	}
	
	@Override
public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch(NumberFormatException ignore) {}
		
		int limit = 10; // 한 페이지 당 표시할 게시글 수
		
		Map<String, Object> params = Map.of(
				"page", page,
				"limit", limit
		);
		
		
		// 2. 업무로직
//		List<Student> students = studentService.findAll();
		List<Student> students = studentService.findPage(params);
		request.setAttribute("students", students);
		
		// 마지막 페이지 구하기
		int totalCount = studentService.getTotalCount();
		int lastPage = (int) Math.ceil((double)totalCount / limit);
		request.setAttribute("lastPage", lastPage);
		
		// 3. 응답처리 (FirstServlet에서 해결함)
		return "student/studentList";//   "/WEB-INF/views/student/studentList.jsp"
	}
}
