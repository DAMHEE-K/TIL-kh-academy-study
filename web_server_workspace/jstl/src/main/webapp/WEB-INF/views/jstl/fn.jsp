<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - fn</title>
</head>
<body>
	<h1>JSTL - fn</h1>
	<ul>
		<li>${str1}</li>
		<li>${fn:toUpperCase(str1)}</li>
		<li>${fn:toLowerCase(str1)}</li>
		<li>${fn:length(str1)}</li>
		<li>${fn:contains(str1, 'world')}
			<c:if test="${fn:contains(str1, 'world')}"><mark>world 있다</mark></c:if>
			<c:if test="${not fn:contains(str1, 'world')}"><mark>world 없다</mark></c:if>
		</li>
		<li>
			${fn:indexOf(str1, 'jstl')} /
			${fn:indexOf(str1, 'jjjjjstl')} <%-- -1반환 --%>
		</li>
		<li>${fn:replace(str1, 'Hello', 'Goodbye')}</li>
		<li>${fn:substring(str1, 6, 11)}</li>
		<%-- <li>${fn:str1 + str2}</li> --%>
		<li>${str1}${str2}</li>
		<li>${fn:escapeXml(str3)}</li> <%-- <script> alert('ㅋㅋㅋㅋ 너 XSS해킹당한 거야~') </script> --%> 
		<li><c:out value="${str3}" escapeXml="true"/></li>
	</ul>
</body>
</html>