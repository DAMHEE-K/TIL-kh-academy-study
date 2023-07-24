<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// ㅋㅋㅋ
	String mode = request.getParameter("mode");
	System.out.println("mode = " + mode);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분기처리</title>
</head>
<body>
<!--  html 주석은 servlet으로 변환되어 client까지 전달됨 -->

<%-- jsp 주석은 servlet으로 변환되지 않는다

jsp -> servlet 변환됨(html코드도 모두 자바코드 안에 들어감)
out.write("<body>\r\n"); 이런식으로~
 --%>
<%
	// 응답 출력스트림 객체(별도의 선언 없이 사용 가능하다)
	// PrintWriter out = response.getWriter();
	// out (내장객체)
	if("en".equals(mode)) {
		// 영어모드
		out.println("<h1>Hello</h1>");
	}
	else {
		// 한글모드 (기본값)
		out.println("<h1>안녕</h1>");
	}

%>

<%
	if("en".equals(mode)) {
		// 영어모드
%>
	<h1>Hello</h1>
<%
	}
	else {
		// 한글모드 (기본값)
%>
	<h1>안녕</h1>
<%
	}
	
%>
	<div>
		<button onclick="location.href='/web/jsp/condition.jsp?mode=en';">영어</button>
		<button onclick="location.href='/web/jsp/condition.jsp?mode=ko';">한글</button>
	</div>
</body>
</html>