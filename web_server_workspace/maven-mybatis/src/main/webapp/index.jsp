<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8"/>
</head>
<body>
	<h2>Hello World!</h2>
	<h2>Student</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/student/studentList.do">학생목록</a>
		<li><a href="${pageContext.request.contextPath}/student/studentCreate.do">학생등록</a>
	</ul>
	<h2>Emp</h2>
	<ul>
		<li><a href="${pageContext.request.contextPath}/emp/search1.do">사원검색1</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/search2.do">사원검색2</a></li>
		<li><a href="${pageContext.request.contextPath}/emp/search3.do">사원검색3</a></li>
		<li></li>
	</ul>
</body>
</html>
