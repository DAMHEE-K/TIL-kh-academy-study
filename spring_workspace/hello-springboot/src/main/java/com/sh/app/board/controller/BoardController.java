package com.sh.app.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sh.app.board.dto.BoardCreateDto;
import com.sh.app.board.entity.Attachment;
import com.sh.app.board.entity.Board;
import com.sh.app.board.entity.BoardDetails;
import com.sh.app.board.service.BoardService;
import com.sh.app.commons.HelloSpringUtils;
import com.sh.app.member.entity.MemberDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@Controller
@Validated
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue="1") int page,
			Model model) {
		
		int limit = 10; // 한 페이지당 게시글 10개
		
		Map<String, Object> params = Map.of(
				"page", page,
				"limit", limit
			);
		List<BoardDetails> boardList = boardService.findAllBoard(params);
		model.addAttribute("boardList", boardList);
	}
	
	@GetMapping("/boardCreate.do")
	public void boardCreate() {}
	
	/**
	 * input[type-file]을 작성하지 않아도 빈 

	 */
	@PostMapping("/boardCreate.do")
	public String boardCreate(
			@Valid BoardCreateDto _board, 
			BindingResult bindingResult,
			@AuthenticationPrincipal MemberDetails member,
			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles)
					throws IllegalStateException, IOException {
		
		// 1. 파일저장
		List<Attachment> attachments = new ArrayList<>();
		for(MultipartFile upFile : upFiles) {			
			if(!upFile.isEmpty()) {
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename); // 20230807_142828888_123.jpg
				File destFile = new File(upFile.getOriginalFilename()); // 부모 디렉토리 생략가능. spring.servlert.multipart.location 값 생략가능
				upFile.transferTo(destFile);
				
				Attachment attach = 
						Attachment.builder()
						.originalFilename(originalFilename)
						.renamedFilename(renamedFilename)
						.build();
							
				attachments.add(attach);
			}
		}
		
		// 2. db저장
//		Board board = _board.toBoard();
//		board.setMemberId(member.getMemberId());
		BoardDetails board = BoardDetails.builder()
				.title(_board.getTitle())
				.content(_board.getContent())
				.memberId(member.getMemberId())
				.attachments(attachments)
				.build();
		
		log.debug("board = {}", board);
		int result = boardService.insertBoard(board);
		return "redirect:/board/boardList.do";
	}
	
	@GetMapping("/boardDetail.do")
	public void boardDetail(@RequestParam int id, Model model) {
		BoardDetails board = boardService.findById(id);
		log.debug("board = {}", board);
		model.addAttribute("board", board);
	}
}
