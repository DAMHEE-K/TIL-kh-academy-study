package com.sh.app.todo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.app.member.entity.MemberDetails;
import com.sh.app.todo.dto.TodoCreateDto;
import com.sh.app.todo.dto.TodoUpdateDto;
import com.sh.app.todo.entity.Todo;
import com.sh.app.todo.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/todo")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todo.do")
	public void todo(Model model, Authentication authentication, @AuthenticationPrincipal MemberDetails member) {
		MemberDetails principal = (MemberDetails) authentication.getPrincipal();

		if(true)
			throw new RuntimeException("Todododododododoodo");
		
		log.debug("todoService = {} {}", todoService, TodoService.class);
//		if(member.getMemberId() == null)
//			throw new RuntimeErrorException("member is null");
		
		List<Todo> todoList = todoService.findByIdTodo(principal.getMemberId());
		log.debug("dotoList = {}", todoList);
		model.addAttribute("todoList", todoList);
	}
	
	@GetMapping("/todoCreated.do")
	public void todoCreate() {}
	
	
	/* 내가 짠 코드 */
//	@PostMapping("/todoCreate.do")
//	public String todoCreate(@RequestParam("todo") String _todo, RedirectAttributes redirectAttr,
//			Authentication authentication, @AuthenticationPrincipal MemberDetails member) {
//		MemberDetails principal = (MemberDetails) authentication.getPrincipal();
//		Todo todo = Todo.builder()
//				.todo(_todo)
//				.memberId(principal.getMemberId())
//				.build();
//		int result = todoService.todoCreate(todo);
//		redirectAttr.addFlashAttribute("msg", "할 일 등록 성공!!");
//		return "redirect:/todo/todo.do";
//	}
	
	/* 강사님 코드 */
	@PostMapping("/todoCreate.do")
	public String create(
			@Valid TodoCreateDto _todo, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal MemberDetails member, 
			RedirectAttributes redirectAttr) {
		Todo todo = _todo.toTodo();
		todo.setMemberId(member.getMemberId());
		int result = todoService.insertTodo(todo);
		return "redirect:/todo/todo.do";
	}
	
	@PostMapping("/deleteTodo.do")
	public String deleteTodo(@RequestParam("id") String _id,RedirectAttributes redirectAttr,
			Authentication authentication, @AuthenticationPrincipal MemberDetails member) {
		
		log.debug("id = {}", _id);
		int id = Integer.parseInt(_id);
		int result = todoService.deleteTodo(id);
		redirectAttr.addFlashAttribute("msg", "삭제 성공");
		return "redirect:/todo/todo.do";
	}

	
	@PostMapping("/todoUpdate.do")
	public String update(
			@Valid TodoUpdateDto _todo, 
			BindingResult bindingResult, 
			@AuthenticationPrincipal MemberDetails member, 
			RedirectAttributes redirectAttr) { 
		Todo todo = _todo.toTodo();
		todo.setMemberId(member.getMemberId());
		int result = todoService.updateTodo(todo);
		return "redirect:/todo/todo.do";
	}
}
