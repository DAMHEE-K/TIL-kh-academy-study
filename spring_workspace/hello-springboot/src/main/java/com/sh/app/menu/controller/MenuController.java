package com.sh.app.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.app.menu.service.MenuService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menu.do")
	public void menu() {}
	
	/**
	 * proxy 처럼 조회하는 방법
	 */
	@GetMapping("/findAll.do")
	public ResponseEntity<?> findAll() {
		//  하나의 업무로직 처럼 사용하기
		return menuService.findAll();
	}
}
