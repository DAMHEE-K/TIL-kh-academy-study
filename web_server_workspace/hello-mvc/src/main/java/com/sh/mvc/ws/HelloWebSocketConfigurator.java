package com.sh.mvc.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.sh.mvc.member.model.vo.Member;

public class HelloWebSocketConfigurator extends Configurator {

	/**
	 * 사용자 연결을 위한 WebSocket용 Session객체 생성 시, 아래 메소드 호출함
	 * 웹 소켓 HTTP 세션 세팅하기 위한 클래스 Configurator
	 * 
	 * - HttpSession에 등록된 loginMember 객체의 memberId를 가져와서
	 * - ServerEndPointConfig 객체의 properties 맵 객체에 저장
	 * - EndPoint클래스의 @onOpen 메소드에서 사용할 수 있다
	 * 
	 * 
	 * Endpoint
	 * 엔드포인트는 서비스를 사용 가능하도록 하는 서비스에서 제공하는 커뮤니케이션 채널의 한쪽 끝.
	 * 즉 요청을 받아 응답을 제공하는 서비스를 사용할 수 있는 지점을 의미
	 * 서비스를 이용하기 위한 요청이 향하는 URI가, 엔드포인트
	 * 
	 */
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession(); // 반환타입이 Object이기 때문에 다운캐스팅
		// 웹소켓용 세션 객체는 따로 존재함, 브라우저 세션을 가져옴
		
		System.out.println("HelloWebSocketConfigurator#modifyHandshake 실행");
		
		// 웹소켓 properties에 로그인 멤버 객체가 없어서 세팅 필요함
		// memberId 관리
		Member loginMember = (Member) httpSession.getAttribute("loginMember"); // 현재 세션에 저장된 loginMember객체 가져옴
		String memberId = loginMember.getMemberId();
		
		Map<String, Object> configProperties = sec.getUserProperties(); // 문서에서 사용자 속성 값을 가져와서 저장
		// Server와 Client Endpoint끼리 같아야 연결이 됨
		configProperties.put("memberId", memberId); // 현재 문서의 속성에 로그인한 멤버의 아이디를 저장
		
		
		// 채팅방 접속 시 chatroomId 관리
		String chatroomId = (String) httpSession.getAttribute("chatroomId");
		if(chatroomId != null) {
			configProperties.put("chatroomId", chatroomId);
			// 채팅방을 벗어나는 경우
			httpSession.removeAttribute("chatroomId");
		}
	}
}
