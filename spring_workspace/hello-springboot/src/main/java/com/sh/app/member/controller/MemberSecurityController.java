package com.sh.app.member.controller;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.app.member.dto.CheckIdDuplicateResponseDto;
import com.sh.app.member.dto.MemberCreateDto;
import com.sh.app.member.dto.MemberUpdateDto;
import com.sh.app.member.entity.Member;
import com.sh.app.member.entity.MemberDetails;
import com.sh.app.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Validated
@RequestMapping("/member")
public class MemberSecurityController {
	
	@Autowired
	private MemberService memberService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/memberCreate.do")
	public void memberCreate() {}

	
	@PostMapping("/memberCreate.do")
	public String create(@Valid MemberCreateDto member, 
			BindingResult bindingResult, RedirectAttributes redirectAttr) {
		
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
	
	// 로그인처리하는 요청 작성 X
	// 로그아웃 처리하는 요청 작성 X
	
	
	@GetMapping("/memberDetail.do")
	public void memberDetail(Authentication authentication, @AuthenticationPrincipal MemberDetails member) {
		log.debug("authentication = {}", authentication);
		
		MemberDetails principal = (MemberDetails) authentication.getPrincipal();
		Object credential = authentication.getCredentials(); // 열람불가
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		log.debug("pricipal = {}", principal);
		log.debug("credential = {}", credential);
		log.debug("authorities = {}", authorities);
		
		log.debug("member = {}",member);
	}
	
	@PostMapping("/memberUpdate.do")
	public String memberUpdate(@AuthenticationPrincipal MemberDetails principal, @Valid MemberUpdateDto _member, BindingResult bindingResult, RedirectAttributes redirectAttr) {
		log.debug("member = {}", _member);
		Member member = _member.toMember();
		String memberId = principal.getMemberId();
		member.setMemberId(memberId);
		
		// 1. db 수정 요청
		int result = memberService.memberUpdate(member);
		
		// 2. security의 authentication 갱신
		UserDetails memberDetails = memberService.loadUserByUsername(memberId);
		// 아이디, 패스워드, 권한을 통해 새로운 인증 객체를 만듦
		Authentication newAuthentication = new UsernamePasswordAuthenticationToken(memberDetails, memberDetails.getPassword(), memberDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		redirectAttr.addFlashAttribute("msg", "회원 정보를 성공적으로 수정하였습니다.");
		return "redirect:/member/memberDetail.do";
	}
	

	/**
	 * ResponseEntity를 반환
	 * - @ReponseBody 기능 포함.
	 * - Generic을 통해 응답 객체의 타입 제어가 가능함
	 * - status code
	 * - header
	 * - body
	 */
	@GetMapping("/checkIdDuplicate.do")
	@ResponseBody // handler의 반환객체를 직접 응답 메세지 바디에 출력(json으로 자동변환)
	public ResponseEntity<?> checkIdDupclite(@RequestParam String memberId) {
		boolean available = false;
		try {
			UserDetails memberDetails = memberService.loadUserByUsername(memberId);
		} catch(UsernameNotFoundException e) {
			available = true;
		}
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("available", available, "memberId", memberId));
	}
	
	/**
	 * jsckson 의존을 통해 json 문자열로 자동변환 후 응답메세지에 출력
	 */
	//	public CheckIdDuplicateResponseDto checkIdDuplicate(@RequestParam String memberId) {
//		boolean available = false;
//		try {
//			UserDetails memberDetails = memberService.loadUserByUsername(memberId); // 조회되지 않으면 예외가 발생하기로 되어 있음
//		} catch(UsernameNotFoundException e) {
//			// 예외가 발생 == 해당 아이디 사용 가능함
//			available = true;
//		}
//		
//		return CheckIdDuplicateResponseDto.builder()
//				.memberId(memberId)
//				.available(available)
//				.build(); 
//	}
//	public Map<String, Object> checkIdDupclite(@RequestParam String memberId) {
//	boolean available = false;
//	try {
//		UserDetails memberDetails = memberService.loadUserByUsername(memberId);
//	} catch (UsernameNotFoundException e) {
//		available = true;
//	}
//	
//	return Map.of(
//		"available", available,
//		"memberId", memberId
//	);
//}
	
	@GetMapping("/terms.do")
	public void getTerms() {}
	
}
