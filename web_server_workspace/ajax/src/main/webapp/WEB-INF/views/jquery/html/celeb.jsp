<%@page import="java.util.List"%>
<%@page import="com.sh.ajax.celeb.model.vo.Celeb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Celeb> celebs = (List<Celeb>) request.getAttribute("celebs");
%>
<table>
	<thead>
		<tr>
			<th>No</th>
			<th>프로필</th>
			<th>이름</th>
			<th>타입</th>
		</tr>
	</thead>
	<tbody>
		<% for(Celeb celeb : celebs) {%>
		<tr>
			<td>No</td>
			<td>프로필
				<img src="<%= request.getContextPath() %>/images/<%= celeb.getProfile() %>" />
			</td>
			<td>이름</td>
			<td>타입</td>
		</tr>
		<% } %>
	</tbody>
</table>
