package com.sh.mvc.ws;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value = "/helloWebSocket", configurator = HelloWebSocketConfigurator.class)
public class HelloWebSocket {
	
	
	private static void log() {
		System.out.println("[현재 접속자 수 : " + clientMap.size() + " 명] " + clientMap);
	}
	
	// WebSocket 세션을 관리할 맵 (멀티스레드에 사용하므로 동기화 처리가 필수)
	// memberId:String = webSocketSession:Session (키:벨류)
	public static Map<String, Session> clientMap = 
			Collections.synchronizedMap(new HashMap<>());
			// 자바에서의 동기화 = 스레드간의 순서를 정해주는 것 (꼬이지 않게..)

	// chatroomId:String = Set<String> 해당 chatroom에 접속한 사용자들의 memberId 집단(Set)
	public static Map<String, Set<String>> chatroomClientMap =
			Collections.synchronizedMap(new HashMap<>());
	
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		// 서버의 끝점을 알아야 클라이언트와 서버 웹소켓을 연결할 수 있음
		
		System.out.println("open");
		Map<String, Object> configProperties = config.getUserProperties();
												// application data안에 있는 정보들을 Map으로 반환
		String memberId = (String) configProperties.get("memberId");
		
		// 1. client Map에 저장
		clientMap.put(memberId, session);
		
		// 2. WenSocket Session객체 properties에 맵 객체에 memberId 저장 (@OnClose에서 사용)
		Map<String, Object> sessionProperties = session.getUserProperties();
		sessionProperties.put("memberId", memberId);
		
		// 채팅방 접속시 사용자 관리
		String chatroomId = (String) configProperties.get("chatroomId");
		if(chatroomId != null) {
			addToChatroom(chatroomId, memberId);
		}
		
		log();
	}

	/**
	 * 특정 chatroom에 사용자 추가
	 * @param chatroomId
	 * @param memberId
	 */
	private void addToChatroom(String chatroomId, String memberId) {
		Set<String> chatroomClients = chatroomClientMap.get(chatroomId);
		// 특정 챗 룸(chatroomId) 안에 있는 클라이언트들
		
		if(chatroomClients == null) { // 첫 번째 입장이라면 (막 생성된 채팅이라면)
			chatroomClients = new HashSet<>();
			chatroomClientMap.put(chatroomId, chatroomClients); // 현재 접속자들 저장
		}
		chatroomClients.add(memberId);
		chatroomLog();
		
	}
	
	private void removeFromChatroom(String chatroomId, String memberId) {
		Set<String> chatroomClients = chatroomClientMap.get(chatroomId);
		chatroomClients.remove(memberId);
		if(chatroomClients.isEmpty()) {
			chatroomClientMap.remove(chatroomId);
		}
		chatroomLog();
	}
	

	/**
	 * 현재 채팅방에 어떤 사용자들이 접속되어 있는지 출력하는 메소드
	 */
	private void chatroomLog() {
		System.out.println("[현재 채팅방 현황 : ");
		for(String chatroomId : chatroomClientMap.keySet()) {
			System.out.print(chatroomId + "=" + chatroomClientMap.get(chatroomId) + " ");
		}
		System.out.print("]");
		
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("message : " + message);
		Map<String, Object> payload = new Gson().fromJson(message, Map.class);
		System.out.println("payload from message : " + payload);
		String chatroomId = (String) payload.get("chatroomId");
		try {
			// 같은 채팅방 사용자에게 전송
			Set<String> chatroomClients = chatroomClientMap.get(chatroomId);
			for(String memberId : chatroomClients) {
				Session wsSession = clientMap.get(memberId); // chatrooms에 접속중인 멤버들의 memberId를 통해서 session 객체를 얻어와 저장
				
				// Session 인스턴스의 getBasicRemote() 매서드는 WebSocket의 상대 쪽인 RemoteEndpoint를 리턴
				Basic basic = wsSession.getBasicRemote();  // 상대의 Endpoint(연결고리)를 가져와서 basic 변수에 저장
				
				// Basic 인터페이스는 WebSocket 메시지에 대한 기본 전송 기능을 제공
				
				basic.sendText(message); // 입력된 메세지 전송
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@OnError
	public void onError(Throwable e) {
		System.out.println("error");
		e.printStackTrace();
	}
	@OnClose
	public void onClose(Session session) {
		System.out.println("close");
		Map<String, Object> sessionProperties = session.getUserProperties();
		String memberId = (String) sessionProperties.get("memberId");
		clientMap.remove(memberId); // 해당 memberId의 WebSocket Session객체 제거
		log();
		
		String chatroomId = (String) sessionProperties.get("chatroomId");
		
		if(chatroomId != null) {
			removeFromChatroom(chatroomId, memberId);
		}
		
		
	}


}
