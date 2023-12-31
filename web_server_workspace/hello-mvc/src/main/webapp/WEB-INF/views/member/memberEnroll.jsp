<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 가입 정보 입력</h2>
	<form 
		name="checkIdDuplicateFrm" 
		action="<%= request.getContextPath()%>/member/checkIdDuplicate">
		<input type="hidden" name="memberId" />
	</form>
	<form name="memberEnrollFrm" action="" method="POST">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="4글자이상" name="memberId" id="_memberId" value="sejong" required>
					<input type="button" value="중복검사" onclick="checkIdDuplicate();" />
					<input type="hidden" id="idValid" value="0" />
					<%-- id검사여부 확인용: 0-유효하지 않음, 1-유효한 아이디 --%>
				</td>
			</tr>
			<tr>
				<th>패스워드<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="_password" value="1234" required><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인<sup>*</sup></th>
				<td>	
					<input type="password" id="passwordConfirmation" value="1234" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="name" id="name" value="세종대왕" required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="1999-09-09"><br />
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="sejong@naver.com"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="01012341234" required><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" checked><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" checked><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form>
</section>
<script>
/**
 * 중복검사 이후 아이디 변경시 #idValid값을 리셋(0)한다
 */
document.querySelector("#_memberId").onchange= () => {
	document.querySelector("#idValid").value = "0";
	// 아이디 중복검사 다시 하게끔 유도
}
/**
 * 아이디 중복검사 함수
 * - 팝업창으로 폼을 제출
 */
const checkIdDuplicate = () => {
	const title = "checkIdDuplicatePopup";
	const popup = open("", title, "width=500px, height=300px"); // checkIdDuplicatePopup라는 name의 팝업창을 만듦
	
	const frm = document.checkIdDuplicateFrm;
	frm.target = title; // 폼의 제출대상을 팝업창으로 연결
	
	// <form> 태그의 target 속성은 폼 데이터(form data)를 서버로 제출한 후 받는 응답이 열릴 위치를 명시함
	
	frm.memberId.value = document.querySelector("#_memberId").value;
	frm.submit();
}

// 비밀번호 일치여부, onblur(포커스되면)
document.querySelector("#passwordConfirmation").onblur = (e) => {
	const pw1 = document.querySelector("#_password");
	const pw2 = e.target;
	
	if(pw1.value !== pw2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		pw1.select();
	}
};

// 폼 유효성검사
document.memberEnrollFrm.onsubmit = (e) => {
	const frm = e.target;
	const memberId = e.target.memberId; // 폼에 있는 memberId
	const password = e.target.password; // 폼에 있는 password
	const passwordConfirmation = e.target.querySelector("#passwordConfirmation"); // 그냥 참조하려면 name값이 있어야 하는데, 
															// 단순 확인용(DB까지 날라가는 정보가 아닌) 데이터는 name값을 지정해 줄 필요는 없다. 대신 querySelector로 가져오기
	const name = e.target.name;
	const phone = e.target.phone;
	const idValid = document.querySelector("#idValid");
	
	// 아이디 중복 검사
	if(idValid.value !== "1") {
		alert("아이디 중복 검사를 해주세요.");
		memberId.select();
		return false;
	}
	
	// 아이디 검사 - 영문자/숫자 4글자 이상
	if (!/^\w{4,}$/.test(memberId.value)) {
		alert("아이디는 영문자/숫자 4글자 이상이어야 합니다.")
		return false;
	}
	// 비밀번호 검사 - 영문자/숫자/특수문자!@#$% 4글자 이상
	if (!/^[\w!@#$%]{4,}$/.test(password.value)) {
		alert("비밀번호는 영문자/숫자/특수문자 !@#$% 4글자 이상이어야 합니다.");
		return false;
	}
	if (password.value !== passwordConfirmation.value) {
		alert("두 비밀번호가 일치하지 않습니다.");
		return false;
	}
	// 이름 검사 - 한글2글자 이상
	if (!/^[가-힣]{2,}$/.test(name.value)) {
		alert("이름은 한글2글자 이상이어야 합니다.");
		return false;
	}
	// 전화번호 검사 - 01012345678 010으로 시작하고 숫자8자리 여부 확인
	if (!/^010\d{8}$/.test(phone.value)) {
		alert("전화번호는 010으로 시작하고 숫자8자리여야 합니다.");
		return false;
	}
	
};


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
