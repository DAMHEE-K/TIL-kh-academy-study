package com.sh.mvc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Member;

@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력값 처리
		String memberId = request.getParameter("memberId"); // dami
		String memberRole = request.getParameter("memberRole");
		
		// 2. 업무로직
		Member member = memberService.findById(memberId);
		int result = memberService.updateMemberRole(member, memberRole);
		
		if(result > 0)
			request.setAttribute("msg", "성공적으로 회원권한을 변경했습니다.");
		// 3. 응답 redirect (POST니까 URL 변경 필요)
		response.sendRedirect(request.getContextPath()+"/admin/memberList");
		
	}

}
