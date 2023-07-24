package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	// 롬복에 의하여 final 붙은 놈들 생성자 자동 생성
//	private final QuestionRepository questionRepository; // 목록 조회 메소드 사용하기 위해
	private final QuestionService questionService;
	private final UserService userService;
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		//Model 객체는 자바 클래스와 템플릿 간의 연결고리 역할을 한다. 
		// Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있다.
		
		// http://localhost:8080/question/list?page=0 GET 방식으로 요청시
		// URL에 페이지 파라미터 page가 전달되지 않은 경우 디폴트 값으로 0이 되도록 설정
		
		Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
		return "question_list";
	}
	
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	
	@PreAuthorize("isAuthenticated()") // 로그인을 했을 때만 쓸 수 있음
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		// QuestionForm 객체에 subject와 content 포함
		if(bindingResult.hasErrors()) {
			return "question_form"; // 에러가 있다면 폼에 그대로
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		
		// 유효 체크에 통과했다면 등록
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}
}
