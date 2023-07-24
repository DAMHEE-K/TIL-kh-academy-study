<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%--
	에러페이지 설정
	- page 지시어에 isErrorPage="true"
	- 발생한 예외 객체에 exception 변수명으로 선언 없이 접근이 가능
 --%>
 <%
 	String message = exception.getMessage();
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러 페이지</title>
<style>
body {text-align: center;}
h1 {font-size:250px; margine:0;}
#message {color:red;}
</style>
</head>
<body>
	<h1>ㅠㅠ</h1>
	<p>이용에 불편을 드려 죄송합니다.</p>
	<p id="message"><%= message %></p>
	<p><a href="<%= request.getContextPath() %>/">홈으로</a></p>
</body>
</html>