package com.sh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Gender;
import com.sh.mvc.member.model.vo.Member;


@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private final MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("utf-8");
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session =  httpReq.getSession();
		Member setMember = (Member)session.getAttribute("loginMember");
		
		
		// 사용자 수정된 정보값 불러오기
		// 이름, 생일, 이메일, 휴대폰, 성별, 취미
		String name = request.getParameter("name");
		String _birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String _gender = request.getParameter("gender");
		String[] _hobbies = request.getParameterValues("hobby");
		
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		Date birthday = _birthday != null && !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
		String hobby = _hobbies != null ? String.join(",", _hobbies) : null;

		// 사용자 정보값 재새팅
		setMember.setBirthday(birthday);
		setMember.setEmail(email);
		setMember.setPhone(phone);
		setMember.setName(name);
		setMember.setGender(gender);
		setMember.setHobby(hobby);
		
		
		int result = memberService.updateMember(setMember);
		
		System.out.println("update result = " + result);
		
		if(result > 0) {
			session.setAttribute("msg", "회원정보 수정에 성공하였습니다.");
			
		}
		else
			session.setAttribute("msg", "수정 실패!");
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
		.forward(request, response);
		
		/* 강사님 코드
		 
		 // 1. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		String _gender = request.getParameter("gender");
		String _birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String[] _hobbies = request.getParameterValues("hobby");
		
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		Date birthday = _birthday != null && !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
		String hobby = _hobbies != null ? String.join(",", _hobbies) : null;
		
		// Member객체로 변환
		// update member set name = ?, gender = ?, birthday = ?, email = ?, phone = ?, hobby = ? where member_id = ?
		Member member = new Member(memberId, null, name, null, gender, birthday, email, phone, hobby, 0, null);
		System.out.println(member);
		
		// 3.업무로직
		int result = memberService.updateMember(member); 

		// session의 속성 loginMember도 바로 갱신
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", memberService.findById(memberId));

		// 4. 사용자피드백 및 리다이렉트 처리
		session.setAttribute("msg", "성공적으로 회원정보를 수정했습니다.");

		response.sendRedirect(request.getContextPath() + "/member/memberDetail");
		 
		*/
	}

}
