<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>성고오오오오오~~~~~~~~ㅇ~~~~~~~~~~</h1>
	<%
	JSONParser parsing = new JSONParser();
	Object obj = parsing.parse(request.toString());
	JSONObject jsonObj = (JSONObject)obj;
	JSONObject resObj = (JSONObject)jsonObj.get("response");
	 
	//왼쪽 변수 이름은 원하는 대로 정하면 된다. 
	//단, 우측의 get()안에 들어가는 값은 와인색 상자 안의 값을 그대로 적어주어야 한다.
	String naverCode = (String)resObj.get("id");
	String email = (String)resObj.get("email");
	String name = (String)resObj.get("name");
	String nickName = (String)resObj.get("nickname");
	%>
	<div>
	당신의 이름 : <span><%= name%></span>
	</div>
</body>
</html>