<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date" %>
<%-- page 지시어(directives) --%>
<%--
클라이언트 요청 -> 서버로 전송됨 -> 서버단 코드 -> 요청값을 처리한 후 반환 -> html 통해서 클라이언트에게 보여짐
그렇기 때문에 서버단의 코드가 먼저 시행되고 후에 프론트단의 코드가 시행됨 (시간 차이가 존재함)
 --%>
<%
	System.out.println("Hello world");

	int sum = 0;
	for(int i=1; i<=10; i++)
		sum += i;
	System.out.println("sum = " + sum);
	
	// long millis = System.currentTimeMillis();
	long millis = new Date().getTime();
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Basics</title>
</head>
<body>
	<h1>JSP Basics</h1>
	<h2>1부터 10까지의 합</h2>
	<ul>
		<li>Server : <%= sum %></li>
		<li>Client : <span id="sum"></span></li>
	</ul>
	
	<h2>시각 출력</h2>
	<ul>
		<li>Server Time : <%= millis %></li>
		<li>Client Time : <span id="now"></span></li>
	</ul>
		
	<script>
	console.log('Hello world');
	(()=>{
		let sum = 0;
		for(let i=1; i<=10; i++) 
			sum += i;
		document.querySelector("#sum").innerHTML = sum;
		
		document.querySelector("#now").innerHTML = Date.now();
	})();
	</script>
</body>
</html>