<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.main-menu{
	font-size:28px;
	color:red;
}
.side-menu{
	font-size:24px;
	color:blue;
}
.drink-menu{
	font-size:20px;
	color:green;
}
.price {
	font-size:32px;
	color:purple;
}
</style>
</head>
<body>
<%
	String mainMenu = request.getParameter("mainMenu");
	String sideMenu = request.getParameter("sideMenu");
	String drinkMenu = request.getParameter("drinkMenu");
	Integer price = (Integer)request.getAttribute("price");

%>
<h1>감사합니다.</h1>
<span class="main-menu"><%=mainMenu %>,</span>
<span class="side-menu"><%=sideMenu %>,</span>
<span class="drink-menu"><%=drinkMenu %></span>
<span>을/를 주문하셨습니다.</span><br />
<p>총 결제금액은<span class="price"><%=price %>원</span>입니다.</p>
<p>
</body>
</html>