package com.sh.mvc.notification.model.service;

import javax.websocket.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.RemoteEndpoint.Basic;

import com.google.gson.Gson;
import com.sh.mvc.board.model.vo.Board;
import com.sh.mvc.ws.HelloWebSocket;

/**
 * 알림 요청이 있을때마다
 * - 1. db notification테이블 저장
 * - 2. HelloWebSocket.clientMap에서 해당 사용자를 찾아서 실시간 알림처리
 * 
 * honggd 게시글 작성 - sinsa 해당 게시글에 댓글 작성 - 니 게시글에 댓글 달렸어~ honggd한테 댓글 알림
 * 
 *
 */
public class NotificationService {
	
	/**
	 * 1. db 저장
	 * 2. 실시간 알림
	 */
	public int notifyNewBoardComment(Board board) {
		// 1. DB저장
		int result = 0;
//		result = notificationDao.insertNotification(conn, notification);
		
		// 2. 실시간 알림
		// WebSocekt Session 가져오기
		Session wsSession = HelloWebSocket.clientMap.get(board.getWriter()); // 게시글의 작성자를 찾음
		if(wsSession != null) {
			Basic basic = wsSession.getBasicRemote();
			try {
				Map<String, Object> payload = new HashMap<>();
				payload.put("messageType", MessageType.NEW_BOARD_COMMENT);
				payload.put("receiver", board.getWriter());
				payload.put("createdAt", System.currentTimeMillis());
				payload.put("message", board.getTitle() + "(" + board.getNo() +")" + "게시글에 댓글이 달렸습니다.");
				
				// json 형태로 만들어주기
				basic.sendText(new Gson().toJson(payload));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
