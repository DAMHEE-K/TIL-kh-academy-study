<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="할일" name="title" />
</jsp:include>
<%
	System.out.println("todo.jsp!!!!!!!!!!!!!!!!!!!!!!!!!!!");	
%>
<style>
div#todo-container {
	width: 60%;
	margin: 0 auto;
	text-align: center;
}
</style>
<div id="todo-container">
	<form:form
		action="${pageContext.request.contextPath}/todo/todoCreate.do"
		class="form-inline" method="post">
		<input type="text" class="form-control col-sm-10 ml-1" name="todo"
			placeholder="할일" required />&nbsp;
        <button class="btn btn-outline-success" type="submit">저장</button>
	</form:form>

	<br />
	<!-- 할일목록 : 완료된 할 일이 나중에 출력 -->
	<!-- 목록 가져오기, 새로 만들기(저장), 완료, 삭제 처리 -->
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>
				<!-- todo.id -->
				<th>완료여부</th>
				<!-- input:checkbox.isCompleted -->
				<th>할일</th>
				<th>생성일</th>
				<th>완료일</th>
				<th>삭제</th>
				<!-- button.btn.btn-danger -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todoList}" var="todo">
				<tr>
					<td>${todo.id}</td>
					<td><input type="checkbox" value="${todo.id}"
						class="isCompleted form-check-input"
						${not empty todo.completedAt ? 'checked' : ''} /></td>
					<td>${todo.todo}</td>
					<td><fmt:parseDate value="${todo.createdAt}"
							pattern="yyyy-MM-dd'T'HH:mm" var="createdAt" /> <fmt:formatDate
							value="${createdAt}" pattern="yy-MM-dd HH:mm" />
					</td>
					<td><c:if test="${not empty todo.completedAt}">
							<fmt:parseDate value="${todo.completedAt}"
								pattern="yyyy-MM-dd'T'HH:mm" var="completedAt" />
							<fmt:formatDate value="${completedAt}" pattern="yy-MM-dd HH:mm" />
						</c:if>
					</td>
					<td>
					<button class="btn btn-danger" onclick="deleteTodo('${todo.id}');">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<form:form
	action="${pageContext.request.contextPath}/todo/todoUpdate.do"
	name="todoUpdateFrm" method="post">
	<input type="hidden" name="id" />
	<input type="hidden" name="completed" />
</form:form>
<form:form name="deleteTodoFrm"
	action="${pageContext.request.contextPath}/todo/deleteTodo.do"
	method="POST">
	<input type="hidden" name="id" value="">
</form:form>
<script>
const deleteTodo = (todoId) => {
	const frm = document.deleteTodoFrm;
	frm.id.value = todoId;
	if(confirm("정말로 삭제하시겠습니까?")) {
		document.deleteTodoFrm.submit();
	}
};

document.querySelectorAll(".isCompleted").forEach((checkbox) => {
	checkbox.addEventListener('change', (e) => {
		const {value, checked} = e.target;
		const frm = document.todoUpdateFrm;
		frm.id.value = value;
		frm.completed.value = checked;
		frm.submit();
	});
});
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
