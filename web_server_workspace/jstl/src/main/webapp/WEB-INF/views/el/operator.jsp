<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 연산자</title>
</head>
<body>
	<h1>EL 연산자</h1>
	<h2>산술연산</h2>
	<ul>
		<li>${big} ${small}</li>
		<li>+ : ${big + small}</li>
		<li>- : ${big - small}</li>
		<li>* : ${big * small}</li>
		<li>/ : ${big / small} ${big div small }</li> <%-- 실수 연산이 지원됨 --%>
		<li>% : ${big % small} ${big modh; small }</li>
	</ul>
	
	<h2>비교연산</h2>
	<ul>
		<li> > ${big > small} ${big gt small}</li>
		<li> < ${big < small} ${big lt small}</li>
		<li> >= ${big >= small} ${big ge small}</li>
		<li> <= ${big <= small} ${big le small}</li>
		<li> == ${big == small} ${big eq small}</li>
		<li> != ${big != small} ${big ne small}</li>
		
		<%-- 객체간 eq, == 연산은 모두  equals를 호출해 처리됨 --%>
		<li>eq ${str1 == str2} ${str1 eq str2}</li>
		<li>eq ${item1 == item2} ${item1 eq item2}</li>https://www.wconcept.co.kr/Product/303200026
		
		<li>empty ${empty list1} ${not empty list1}</li>
		<li>empty ${empty list2} ${not empty list1}</li>
		<li>empty ${empty list3} ${not empty list1}</li>
		
		<%-- && || 모두 사용 가능 --%>
		<li>${true && true} ${true && false}</li>
		<li>${true || true} ${true || false}</li>
	</ul>
</body>
</html>