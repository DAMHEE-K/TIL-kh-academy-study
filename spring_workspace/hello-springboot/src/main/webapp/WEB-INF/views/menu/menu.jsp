<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="추천메뉴" name="title"/>
</jsp:include>
<style>
div.menu-test{width:50%; margin:0 auto; text-align:center;}
div.result{width:70%; margin:0 auto;}
</style>
<div id="menu-container" class="text-center">
	<!-- 1.GET /menus-->
	<div class="menu-test">
		<h4>전체메뉴조회(GET)</h4>
		<input type="button" class="btn btn-block btn-outline-success btn-send" id="btn-menus" value="전송" />
	</div>
	<div class="result" id="menus-result"></div>
	<script>
	/*
		Access to XMLHttpRequest at 'http://localhost:10000/menus' 
		from origin 'http://localhost:8080' has been blocked by CORS policy: 
		No 'Access-Control-Allow-Origin' header is present on the requested resource.
		
		SOP Same Origin Policy 동일 근원 정책
			- 웹 브라우져 환경에서 비동기 요청은 동일한 Origin에 한 해 허용함
			- http://localhost:8080에 접속시, http://localhost:8080으로만 비동기 요청이 가능함
			
		Origin (http://localhost:8080, http:localhost:10000)
			- protocol  http://
			- host      localhost
			- port      :8080
			- 위 3개의 값이 모두 동일할 때, Origin으로 처리함
		
		CORS Cross Origin Resource Sharing Policy
			- 다른 Origin으로 부터 자원 공유 정책
			- 요청에 대한 응답에 Access-Control-Allow-Origin에 해당 origin이 포함되어 있다면
			  자원 공유 허용
			- 본 요청 전에 예비 요청(pre-flight, OPTIONS 전송 방식 사용) 을 통해서도 헤더값 확인 가능
	*/
	document.querySelector("#btn-menus").onclick = () => {
		$.ajax({
			// url : 'http://localhost:10000/menus',
			url : '${pageContext.request.contextPath}/menu/findAll.do', // proxy 처럼 사용하는 방법
			method : "GET",
			success(menus){
				console.log(menus);
				
				renderMenus(menus, "#menus-result");
			}
		});
	};
	</script>
	<div class="menu-test">
		<h4>추천메뉴(GET)</h4>
		<form id="menuRecommendationFrm">
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="type" id="get-no-type" value="all" checked>
				<label for="get-no-type" class="form-check-label">모두</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="get-kr" value="kr">
				<label for="get-kr" class="form-check-label">한식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="get-ch" value="ch">
				<label for="get-ch" class="form-check-label">중식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="get-jp" value="jp">
				<label for="get-jp" class="form-check-label">일식</label>&nbsp;
			</div>
			<br />
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="taste" id="get-no-taste" value="all" checked>
				<label for="get-no-taste" class="form-check-label">모두</label>&nbsp;
				<input type="radio" class="form-check-input" name="taste" id="get-hot" value="hot" checked>
				<label for="get-hot" class="form-check-label">매운맛</label>&nbsp;
				<input type="radio" class="form-check-input" name="taste" id="get-mild" value="mild">
				<label for="get-mild" class="form-check-label">순한맛</label>
			</div>
			<br />
			<input type="submit" class="btn btn-block btn-outline-success btn-send" value="전송" >
		</form>
	</div>
	<div class="result" id="menuRecommendation-result"></div>
</div>
<script>
const renderMenus = (menus, target) => {
	const container = document.querySelector(target);
	
	let html = `
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>음식점</th>
					<th>메뉴</th>
					<th>가격</th>
					<th>타입</th>
					<th>맛</th>
				</tr>
			</thead>
			</tbody>
			
	`;
	if(menus.length) {
		html += menus.reduce((html, menu) => {
			const {id, restaurant, name, price, type, taste} = menu;
			return `
				\${html}
				<tr>
					<td>\${id}</td>
					<td>\${restaurant}</td>
					<td>\${name}</td>
					<td>￦\${price.toLocaleString()}</td>
					<td>\${type}</td>
					<td>\${taste}</td>
				</tr>
			`;
		}, "");
	} 
	else {
		html += `
			<tr>
				<td colspan="6">조회된 메뉴가 없습니다.</td>
			</tr>
		`;
	}
	html += `
			</tbody>
		</table>
	`;
	
	container.innerHTML = html;
}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
