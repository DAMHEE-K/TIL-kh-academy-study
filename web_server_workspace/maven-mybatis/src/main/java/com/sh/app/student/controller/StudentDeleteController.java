package com.sh.app.student.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.app.common.controller.AbstractController;
import com.sh.app.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentDeleteController extends AbstractController {

	private final StudentService studentService;
	
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int result = studentService.deleteStudent(id);
		return "redirect:/student/studentList.do";
	}

}