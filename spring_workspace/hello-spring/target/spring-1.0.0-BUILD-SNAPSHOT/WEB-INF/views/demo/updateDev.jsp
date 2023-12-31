<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 수정" name="title"/>
</jsp:include>
<style>
div#demo-container{
	width:550px;
	padding:15px;
	border:1px solid lightgray;
	border-radius: 10px;
}
</style>
<div id="demo-container" class="mx-auto">
	<!-- https://getbootstrap.com/docs/4.1/components/forms/#readonly-plain-text -->
	<form name="devUpdateFrm"
		  method="post" 
		  action="${pageContext.request.contextPath}/demo/updateDev.do">
		<div class="form-group row">
		  <label for="name" class="col-sm-2 col-form-label">이름</label>
		  <div class="col-sm-10">
		    <input type="text" class="form-control" id="name" name="name" value="${dev.name}" required>
		  </div>
		</div>
		<div class="form-group row">
		  <label for="career" class="col-sm-2 col-form-label">개발경력</label>
		  <div class="col-sm-10">
		    <input type="number" class="form-control" id="career" name="career" value="${dev.career}" required>
		  </div>
		</div>
		<div class="form-group row">
		  <label for="email" class="col-sm-2 col-form-label">이메일</label>
		  <div class="col-sm-10">
		    <input type="email" class="form-control" id="email" name="email" value="${dev.email}" required>
		  </div>
		</div>
	  	<!-- https://getbootstrap.com/docs/4.1/components/forms/#inline -->
	    <div class="form-group row">
	    	<label class="col-sm-2 col-form-label">성별</label>
	    	<div class="col-sm-10">
			    <div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="gender"
				  		 id="gender0" value="M" ${dev.gender eq 'M' ? 'checked' : ''}>
				  <label class="form-check-label" for="gender0">남</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="gender" 
				  		 id="gender1" value="F" ${dev.gender eq 'F' ? 'checked' : ''}>
				  <label class="form-check-label" for="gender1">여</label>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<label class="col-sm-2 col-form-label">개발언어</label>
			<div class="col-sm-10">
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="lang" id="Java" value="Java" ${dev.lang.contains('Java') ? 'checked' : ''}>
				  <label class="form-check-label" for="Java">Java</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="lang" id="C" value="C" ${dev.lang.contains('C') ? 'checked' : ''}>
				  <label class="form-check-label" for="C">C</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="lang" id="Javascript" value="Javascript" ${dev.lang.contains('Javascript') ? 'checked' : ''}>
				  <label class="form-check-label" for="Javascript">Javascript</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="checkbox" name="lang" id="Python" value="Python"${dev.lang.contains('Python') ? 'checked' : ''}>
				  <label class="form-check-label" for="Python">Python</label>
				</div>
			</div>
		</div>
		<input type="hidden" name="id" value="${dev.id}">
		<button type="submit" class="list-group-item list-group-item-action">dev 수정</button>
		<button type="button" onclick="deleteDev();" class="list-group-item list-group-item-action">dev 삭제</button>
	</form>
</div>
<form 
	name="devDeleteFrm" 
	action="${pageContext.request.contextPath}/demo/deleteDev.do"
	method="POST">
	<input type="hidden" name="id" value="${dev.id}" />	
</form>
<script>
const deleteDev = () => {
	if(confirm("정말 삭제하시겠습니까?")) {
		document.devDeleteFrm.submit();
	}
}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
