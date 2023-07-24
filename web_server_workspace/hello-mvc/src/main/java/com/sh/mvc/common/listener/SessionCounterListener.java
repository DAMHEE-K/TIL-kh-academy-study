package com.sh.mvc.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionCounterListener
 *
 */
@WebListener
public class SessionCounterListener implements HttpSessionListener {
	// HttpSessionListener
	// HttpSession 객체가 생성되고 제거될 때 발생하는 이벤트
	// 예를 들어 회원이 로그인을 하면, Session객체가 생성될거고 sessionCreated 이벤트 발생함
	// 로그아웃을 하면 session객체를 삭제 sessionDestroyed 이벤트 발생함
	// 이러한 기능을 이용하여 동시 접속한 로그인 유저를 확인할 수 있음
	
	private int cnt;
	
    /**
     * Default constructor. 
     */
    public SessionCounterListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  {
    	cnt++;
    	System.out.println("[Session Created!]" + cnt);
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	if(cnt > 0)
    		cnt--;
    	System.out.println("[Session Destroyed!]" + cnt);
    	
    }
	
}
