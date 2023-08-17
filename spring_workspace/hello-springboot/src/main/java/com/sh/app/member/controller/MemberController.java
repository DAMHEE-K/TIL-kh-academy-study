package com.sh.app.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.dto.MemberLoginDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

//@Controller
@Slf4j
//@RequestMapping("/member")
//@SessionAttributes({"loginMember"})
//@Validated
public class MemberController {
	
	// Autowired : 빈에게 객체를 주입받고 싶을 때 쓰는 어노테이션
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 서버가 켜졌다는 건 의존 주입이 잘 되었다는 이야기
	@GetMapping("/memberCreate.do")
	public void memberCreate() {}
	
	/**
	 * $2a$10$0gTYziPEcS/GD0ZHwmTMguRWWIqgwN79NCQGOti0Ttc0YaKn2CMBq
	 * - 알고리즘 $2a$
	 * - 옵션 10$ round 숫자 (높을수록 보안성 증가, 속도/메모리 사용량 증가)
	 * - 랜덤 솔트(22자리)
	 * - 해싱값 (31자리)
	 */
	@PostMapping("/memberCreate.do")
	public String memberCreate(@Valid MemberCreateDto member, 
			BindingResult bindingResult, RedirectAttributes redirectAttr) {
		// bindingResult : 오류 정보를 담고 있는 객체
		
		log.debug("member = {}", member);
		
		if(bindingResult.hasErrors()) { // 오류 정보가 담겨있다면
			ObjectError error = bindingResult.getAllErrors().get(0);
			redirectAttr.addFlashAttribute("msg", error.getDefaultMessage());
			
			return "redirect:/member/memberCreated.do";
		}
		
		String rawPassword = member.getPassword(); // view단에서 사용자가 입력한 패스워드(1234)
		String encodedPassword = passwordEncoder.encode(rawPassword); // 씨큐리티 처리
		log.debug("{} -> {}", rawPassword, encodedPassword);
		member.setPassword(encodedPassword); // 씨큐리티 처리된 비밀번호를 세팅함
		
		int result = memberService.insertMember(member); // 멤버 객체 저장
		redirectAttr.addFlashAttribute("msg", "🎉🎉회원가입을 축하드립니다🎉🎉");
		return "redirect:/";
	}
	
	@GetMapping("/memberLogin.do")
	public void memberLogin() {}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(@Valid MemberLoginDto _member, 
			BindingResult bindingResult, Model model) {
		
		log.debug("_member = {}", _member); // 뷰에서 멤버가 잘 넘어왔나 체크
		
		// 1. 아이디로 Member 조회
		Member member = memberService.findMemberById(_member.getMemberId());
		log.debug("member = {}", member);
		log.debug("temp = {}", passwordEncoder.encode("1234"));
		
		// 2. 로그인 성공(세션에 로그인 객체 저장) / 실패 분기 처리
		if(member != null && passwordEncoder.matches(_member.getPassword(), member.getPassword())) {
			// 로그인 성공
			// 클래스 레벨의 @SessionAttributes({"loginMember"}) 작성 후 session scope에 저장됨
			model.addAttribute("loginMember", member); // request.setAttribute랑 같은 효과임. 단순히 이거만 써서는 session에 저장되지는 않음
			
		}
		else {
			// 로그인 실패
			return "redirect:/member/memberLogin.do?error";
		}
		return "redirect:/";
	}
	
	/**
	 * HttpSession#invalidate -> SessionStatus#setComplete
	 * - 기존 방식 : 세션 객체를 폐기함
	 * - 스프링 방식(@SessionAttributes) : 세션 객체는 유지하되, 속성만 폐기. 효율성 향상. 객체를 매번 만들지 않고 재사용.
	 */
	@GetMapping("/memberLogout.do")
	public String memberLogout(SessionStatus sessionStatus) {
		if(!sessionStatus.isComplete())  // 사용 완료 처리가 되지 않았다면
			sessionStatus.setComplete();
		return "redirect:/";
	}
}
