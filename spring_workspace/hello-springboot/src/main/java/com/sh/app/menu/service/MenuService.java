package com.sh.app.menu.service;


import org.springframework.http.ResponseEntity;

public interface MenuService {

	// 우리 서버에는 Menu가 없기 때문에 (만들어서 사용하는 것이 베스트)
	// 이지만 귀찮을 경우 Map으로..
	ResponseEntity<?> findAll();

}
