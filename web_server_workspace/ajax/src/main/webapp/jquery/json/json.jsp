<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery - json</title>
<style>
table {
	margin: 20px 0;
	border: 1px solid #000;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #000;
	padding: 5px;
}

table img {
	width: 150px;
}
</style>
<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
</head>
<body onload="findAllCeleb();">
	<h1>jQuery - json</h1>
	<button id="btn1">전체 조회</button>
	<div class="wrapper">
		<table>
			<thead>
				<tr>
					<th>No</th>
					<th>프로필</th>
					<th>이름</th>
					<th>타입</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<script>
	btn1.onclick = () => {
		findAllCeleb();
	}
	
	const findAllCeleb = () => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/json/celeb/findAll",
			dataType : "json",
			success(responseDate) {
				console.log(responseDate); // json 문자열 -> js object 파싱처리
				
				const tbody = document.querySelector(".wrapper table tbody");
				
				tbody.innerHTML = responseDate.reduce((html, celeb) => {
				
					const {no, name, profile, celebType} = celeb;
					
					return html + `
					<tr>
						<td>\${no}</td>
						<td>
							<img src="<%= request.getContextPath()%>/images/\${profile}" />
						</td>
						<td><a href="javascript:findOneCeleb(\${no});">\${name}</a></td>
						<td>\${celebType}</td>
					</tr>
					`;
				}, "");
				
			}
		});
	}
	</script>
	
		<form name="celebEnrollFrm">
		<fieldset>
			<legend>Celeb 등록폼</legend>
			<table>
				<tbody>
					<tr>
						<th>
							<label for="celeb-enroll-name">Name</label>
						</th>
						<td>
							<input type="text" name="name" id="celeb-enroll-name" />
						</td>
					</tr>
					<tr>
						<th>
							<label for="celeb-enroll-type">Type</label>
						</th>
						<td>
							<select name="type" id="celeb-enroll-type">
								<option value="ACTOR">ACTOR</option>
								<option value="SINGER">SINGER</option>
								<option value="MODEL">MODEL</option>
								<option value="COMEDIAN">COMEDIAN</option>
								<option value="ENTERTAINER">ENTERTAINER</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<label for="celeb-enroll-profile">Profile</label>
						</th>
						<td>
							<input type="file" name="profile" id="celeb-enroll-profile" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button>등록</button>
						</td>
					</tr>
				</tbody>
			</table>		
		</fieldset>
	</form>
	<script>
	/**
	* 동기식 POST 요청(DML 처리) - redirect(URL 변경을 위해) 처리
	* 비동기식 POST 요청 (DML 처리) - redirect 없음. 결과로는 map/생성된 객체등 반환
	*/
	<%
	
	%>
	document.celebEnrollFrm.onsubmit = (e) => {
		// 파일 업로드를 포함한 비동기 요청
		// 1. FormData 객체를 사용
		// 2. ajax 속성 - processData : false, contentType : false
		const frmData = new FormData(e.target);
	
		// FormData는 <form>과 같은 효과를 가져다 주는, key/value가 저장되는 객체
		// XHR 에 실어서 서버에 보내면 마치 <form> 이 전송된 것과 같은 효과
		// processData : 일반적으로 서버에 전달되는 데이터는 query string 이라는 형태로 전달함 (직렬화)
		// 직렬화 전송을 방지하는 것
		// content-type : 보내는 자원의 형식을 명시하기 위해 헤더에 실리는 정보
		
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/json/celeb/enroll",
			data : frmData,
			processData : false, // 데이터 직렬화 처리 방지
			contentType : false, // 기본 Content-Type : application/x-www-form-urlencoded 사용안함
			method : "POST",
			dataType : "json",
			success(responseData) {
				console.log(responseData);
				const {result, message} = responseData;
				alert(message);
				
				findAllCeleb();
			},
			complete() {
				e.target.reset(); // 폼 초기화
			}
			
		});

		e.preventDefault(); // 동기적 폼 제출 방지를 위해 작성
	};
	</script>
	<br />
	<form name="celebUpdateFrm">
		<fieldset>
			<legend>Celeb 수정폼</legend>
			<table>
				<tbody>
					<tr>
						<th>
							<label for="celeb-update-no">No</label>
						</th>
						<td>
							<input type="text" name="no" id="celeb-update-no" />
						</td>
					</tr>
					<tr>
						<th>
							<label for="celeb-update-name">Name</label>
						</th>
						<td>
							<input type="text" name="name" id="celeb-update-name" />
						</td>
					</tr>
					<tr>
						<th>
							<label for="celeb-update-type">Type</label>
						</th>
						<td>
							<select name="type" id="celeb-update-type">
								<option value="ACTOR">ACTOR</option>
								<option value="SINGER">SINGER</option>
								<option value="MODEL">MODEL</option>
								<option value="COMEDIAN">COMEDIAN</option>
								<option value="ENTERTAINER">ENTERTAINER</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							<label for="celeb-update-profile">Profile</label>
						</th>
						<td>
							<div>
								<img src="" id="celeb-update-profile-viewer"/>
							</div>
							<input type="file" name="profile" id="celeb-update-profile" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button>수정</button>
							<button type="button" id="btn-celeb-delete">삭제</button>
						</td>
					</tr>
				</tbody>
			</table>		
		</fieldset>
	</form>
	<script>
	const findOneCeleb = (no) => {
		console.log(no);
		
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/json/celeb/findOne",
			data : {no},
			success(celeb) {
				console.log(celeb);
				const {no, name, profile, celebType} = celeb;
				// document.celebUpdateFrm에 데이터 채우기
				const frm = document.celebUpdateFrm;
				frm.no.value = no;
				frm.name.value = name;
				frm.type.value = celebType;
				document.querySelector("#celeb-update-profile-viewer").src = 
					`<%= request.getContextPath() %>/images/\${profile}`;
			}
		});
	};
	
	/**
	* 폼 동기적 제출 방지
	* 1. FormData
	2. $.ajax({processData, contentType})
	*/
	document.celebUpdateFrm.onsubmit  = (e) => {
		e.preventDefault(); // 동기적 제출 방지
		
		const frmData = new FormData(e.target);
		
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/json/celeb/update",
			data : frmData,
			processData : false,
			contentType : false,
			method : "POST",
			success(responseData) {
				console.log(responseData);
			},
			complete() {
				e.target.reset();
				findAllCeleb();
			}
		});
	}
	</script>
</body>
</html>