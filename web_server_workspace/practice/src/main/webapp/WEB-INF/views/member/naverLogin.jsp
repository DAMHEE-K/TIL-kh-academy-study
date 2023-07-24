<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 로그인 예시</title>
</head>
<body>
	<h1>네이버 로그인~~~~~~~~</h1>
	<%
	String clientId = "9kBGa_4PSPHg5IPpNrhO"; // 클라이언트 아이디 값
	String redirectURI = URLEncoder.encode("/member/callback", "UTF-8");
	SecureRandom random = new SecureRandom(); // 난수생성
	String state = new BigInteger(130, random).toString();
	String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	apiURL += "&client_id=" + clientId;
	apiURL += "&redirect_uri=" + redirectURI;
	apiURL += "$state" + state;
	session.setAttribute("state", state);
	%>
	<div>
		<a href="<%= apiURL %>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>	
	</div>

</body>
</html>