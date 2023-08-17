package com.sh.app.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MenuServiceImpl implements MenuService {

	// 메뉴를 불러올 HOST
	// application.yml 같은 곳에서 관리하는 것도 좋음
	private String MENU_HOST = "http://localhost:10000";
	
	
	
	//RestTemplate : Spring에서 지원하는 객체로 간편하게 Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
	
	@Override
	public ResponseEntity<?> findAll() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = MENU_HOST + "/menus";
		return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>(){});
	}
}
