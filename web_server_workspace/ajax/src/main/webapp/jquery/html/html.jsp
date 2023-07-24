<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery - html</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<style>
table {
	border: 1px solid #000;
	border-collapse : collapse;
}
th, td {
	border: 1px solid #000;
	padding: 5px;
}
table img {
	width: 150px;
}
</style>
</head>
<body>
	<h1>jQuery - html</h1>
	<button id="btn1">셀럽 조회</button>
	<div class="wrapper"></div>
	<script>
	btn1.onclick = () => {
		
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/html",
			// method : "GET" 이 기본값일 경우 작성하지 않아도 무방
			// dataType : "html" 굳이 안적어줘도 헤더값에 적혀있어서 괜찮음
			success(responseHTML) {
				// 비동기 통신에 성공했을 경우 들어오는 데이터(responseHTML)를 이용해서
				// 실행할 메소드
				console.log(responseHTML);
				document.querySelector(".wrapper").innerHTML = responseHTML;
			}
		});
	}
	</script>
</body>
</html>