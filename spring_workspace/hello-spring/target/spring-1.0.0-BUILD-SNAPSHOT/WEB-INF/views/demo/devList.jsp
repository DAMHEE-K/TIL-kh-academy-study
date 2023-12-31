﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 목록" name="title"/>
</jsp:include>
<table class="table w-75 mx-auto">
	<thead>
		<tr>
		  <th scope="col">번호</th>
		  <th scope="col">이름</th>
		  <th scope="col">경력</th>
		  <th scope="col">이메일</th>
		  <th scope="col">성별</th>
		  <th scope="col">개발가능언어</th>
		  <th scope="col">등록일시</th>
		  <th scope="col">수정</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty devs}">
			<tr>
				<td colspan="7" class="text-center">조회된 Dev 정보가 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty devs}">
		<c:forEach items="${devs}" var="dev">
			<tr>
				<td scope="col">${dev.id}</td>
				<td scope="col">${dev.name}</td>
				<td scope="col">${dev.career}</td>
				<td scope="col">${dev.email}</td>
				<td scope="col">${dev.gender}</td>
				<td scope="col">
					<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
						${lang} ${not vs.last ? "/" : "" }
					</c:forEach>
				</td>
				<td scope="col">
					<fmt:parseDate value="${dev.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createdAt"/>
					<fmt:formatDate value="${createdAt}" pattern="yyyy/MM/dd HH:mm"/>
				</td>
				<td>
					<button class="btn-update btn btn-outline-primary" value="${dev.id}">수정</button>
				</td>
			</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<script>
document.querySelectorAll(".btn-update").forEach((btn) => {
	btn.onclick = (e) => {
		console.log(e.target.value);
		location.href =`${pageContext.request.contextPath}/demo/updateDev.do?id=\${e.target.value}`
	};
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>