package com.sh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.common.util.HelloMvcUtils;
import com.sh.mvc.member.model.service.MemberService;
import com.sh.mvc.member.model.vo.Member;
/*
 ~ 코드흐름 ~
1. header.jsp가 처음 화면에 보여진다. 로그인폼에 아이디와 비밀번호를 입력하면, action="<%= request.getContextPath() %>/member/login" 이 부분이 작동된다. 
action = "서블릿"의 의미는 로그인 요청이 들어오면 해당 서블릿으로 데이터와 요청을 넘기라는 뜻이다.

2. /member/login 서블릿은 src/main/java 폴더 하위에 있는 com.sh.mov.member.controller에 들어있는 MemberLoginServlet이다.

3. 서블릿에서 넘어온 데이터값(request)을 읽어 변수에 저장(여기서는 id랑 비밀번호 저장)하고, 그 사용자가 입력한 값을 가지고 DB로 접근한다.

4. MVC 패턴에 따라서 서비스-DAO-DB 순서로 넘어가서 데이터를 읽어와야 한다.
MemberLoginServlet 클래스에 있는 findById(memberId)를 보면 값을 가지고 서비스, Dao, db 순서로 넘어가는거 확인할 수 있다.
db에서 조회에 성공했다면 해당 멤버 객체를 반환받는다.

5. 반환받은 멤버 객체를 다시 뷰(JSP)로 보내서 사용자가 확인 할 수 있게 한다.
 */




/**
 * 컨텍스트 객체
 * 웹 애플리케이션이 실행되면서 애플리케이션 공통 자원이나 정보를 담은 객체
 * - pageContext : PageContext, JSP에서 사용되는 객체
 * - request : HttpServletRequest, Servlet이나 JSP에서 클라이어트 요청을 처리하기 위해 사용
 * - session : HttpSession, 클라이언트가 웹 애플리케이션과 상호작용 할 때 상태를 유지하는데 사용
 * - application : ServletContext, 웹 애플리케이션 전체에 공유되는 데이터나 설정 정보를 저장
 * 
 * 위 객체는 모두 setAttribute(String, Object), getAttribute(String) : Object를 반환한다
 * 
 * Session | cookie
 * - http 통신의 상태관리 메커니즘
 * - 기본적으로 http통신은 stateless하다. (한 번 요청후, 응답하면 서버와의 연결이 끊어진다.)
 * - 이전 사용자가 다음 요청 시, 동일한 사용자인 것을 구분 할 수가 없다. (로그인했다가 페이지 이동 시 로그인 풀리는 등)
 * - Session 사용자 정보를 서버쪽에 보관하는 기술
 * - Cookie 사용자 정보를 클라이언트(브라우져)에 보관하는 기술
 * 
 * - 발급 및 사용과정
 * 1. 클라이언트 최초 요청(JSESSIONID는 발급되지 않은 상태) 시 서버는 세션 객체를 생성
 * 2. 발급된 세션아이디를 응답에 전송 (Set-Cookie: 세션ID)
 * 3. 클라이언트는 JSESSIONID에 전달받은 세션ID로 쿠키를 저장함
 * 4. 클라이언트는 다음 요청부터 매번 요청 헤더에 Cookie 항목으로 JSESSIONID를 함께 요청함.
 * 5. 서버에서는 JSESSIONID 검증 후 해당 session 객체 사용하도록 해줌. (물품보관함에 실명인증하면 쓰게 해주듯이)
 * 
 * - Session 객체는 클라이언트 별로 생성해 관리한다.
 * - 기본적으로 세션 객체의 유효시간은 30분이다.
 * 
 * - request.getSession(create: boolean)
 * - 세션이 유효하지 않은 경우, 생성여부확인하여 boolean 값으로 반환
 * - true(기본값) : 세션id가 유효하지 않거나 없는 경우 session객체를 새로 생성해서 반환(없으면 새로 만들어서라도 반환해라)
 * - false : 세션id가 유효하지 않거나 없는 경우 null을 반환
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	// 데이터를 가지고 service를 통해 dao로 들어가야 하니까 service 객체 생성
	private final MemberService memberService = new MemberService();
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청헤더의 값은 request 객체에서 꺼내쓸 수 있다
		
		// 0. 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		// 1. 사용자 입력값 (memberId, password) 가져오기
		String memberId = request.getParameter("memberId");
		String password = HelloMvcUtils.getEncryptedPassword(request.getParameter("password"), memberId);
		System.out.println(password);
		
		String savedId = request.getParameter("savedId"); // 아이디 저장을 누르면 쿠키를 하나 만들어서 현재 아이디를 저장해놓을 것임
		// 제출된 폼에 작성된 id와 password를 변수에 저장한다

		System.out.println("memberId = " + memberId);
		System.out.println("password = " + password);
		System.out.println("savedId = " + savedId);
		
		// 2. 업무로직 실행 - 로그인 확인
		// 아이디로 db에서 조회 select * from member where member_id = ?
		// member객체가 null이 아니면서 비밀번호가 일치하면 로그인 성공
		// member객체가 null이거나 비밀번호가 일치하지 않으면 로그인실패

		// service - dao - db(조회) - 조회된 값을 가지고 다시 dao - service로 나온다
		Member member = memberService.findById(memberId); // memberId를 가지고 DB에 접근한다
		// 조회된 member 객체가 member 변수에 저장됨
		
		// System.out.println("member@service = " + member);
		
		HttpSession session = request.getSession(); // session의 기능은 request랑 똑같다 (session이 생명주기가 좀 더 길다)
		System.out.println(session.getId()); // request.getSession(true)랑 똑같음.
		// 로그인한 회원 객체의 정보를 현재 sessionid랑 같이 날리고, session객체 중에서 sessionid 값과 동일한 객체를 찾아
		// 로그인 멤버를 저장함
		
		
		// member == null 조회된 회원이 없으면
		// member != null 조회된 회원이 있으면서 비밀번호가 일치하면
		if(member != null && password.equals(member.getPassword())) {
			// 로그인 성공
			session.setAttribute("loginMember", member);
			session.setAttribute("msg", "로그인에 성공했습니다.");
			
			// 아이디 저장을 누르면 쿠키 처리
			// - Path : 쿠키를 사용할 url. 서버 전송 시 부모 경로만 지정하면 자식까지 사용가능
			//			  /  :  설정 시 모든 요청에 사용 가능
			//			/mvc : /mvc로 시작하는 모든 요청에 사용 가능
			// - Persistent Cookie : setMaxAge 설정한 경우. 지정한 시각까지만 클라이언트에 보관
			// - Session Cookie : setMaxAge 설정하지 않은 경우. 접속한 동안만 클라이언트에 보관
			
			if(savedId != null) {
				Cookie cookie = new Cookie("saveId", memberId);
				cookie.setPath(request.getContextPath()); // 쿠키를 사용할 url
				cookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효기간 7일(초 단위로 넘겨야 함)
				response.addCookie(cookie); // 응답 헤더에  Set-Cookie : savaId = honggd
			} 
			else {
				// 기존에 있는 쿠키를 삭제하는 절차를 거쳐야함!
				Cookie cookie = new Cookie("saveId", memberId); // 쿠키만들고
				cookie.setPath(request.getContextPath()); 
				cookie.setMaxAge(0); // 클라이언트에 있던 쿠키의 만료기간을 0으로 변경함과 동시에 삭제
				response.addCookie(cookie); 
			}
		} else {
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		// 3. 응답처리
		// redirect를 통한 url 변경 (작업을 수행한 후 지정한 페이지로 이동시키고 싶을 때 사용)
		// Location이라는 header값을 () 안에 지정한 url로 변경하는 것
//		response.sendRedirect(request.getContextPath() + "/");
		
		String referer = request.getHeader("Referer");
		System.out.println("referer = " + referer);
		response.sendRedirect(referer);
	}

}
