package com.sh.app.ws.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.sh.app.member.service.MemberService;
import com.sh.app.ws.dto.Payload;

import lombok.extern.slf4j.Slf4j;

/**
 * ApplicationDestinationPrefix로 시작하는 ws 요청은 이 핸들러를 거친다.
 */
@Controller
@Slf4j
public class StompMessageController {
	
	@Autowired
	private MemberService memberService; // 빈 생성 가능
										// 디비에서 조회해서 메세지로 출력도 가능하다는 의미
	
	
//	@Autowired
//	private NotificationService notificationService;
	
	/**
	 * prefix를 제외한 url만 작성해야 함
	 * 
	 * 전체 공지
	 */
	@MessageMapping("/notice")
	@SendTo("/app/notice")
	public Payload notice(@RequestBody Payload message) {
		log.debug("message = {}", message);
		// notificationService.insertNotification(message);
		return message;
	}
	
	
	/**
	 * 개개인에게
	 */
	@MessageMapping("/notice/{memberId}")
	@SendTo("/app/notice/{memberId}")
	public Payload noticeEach(@DestinationVariable String memberId, Payload message) {
								// 경로변수
		
		log.debug("memberId = {}", memberId);
		log.debug("message = {}", message);
		return message;
	}
}
