<%@page import="java.sql.Date, java.util.*" %>
<%@page import="com.sh.mvc.member.model.vo.Gender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	// 로그인한 멤버 객체의 정보를 읽어서 변수에 저장함
	// not null / null 구분하기(null인 애들은 조치를 또 해줘야함)
	String name = loginMember.getName();
	String memberId = loginMember.getMemberId();
	
	String email = loginMember.getEmail();
	email = email != null ? email : "";
	
	String phone = loginMember.getPhone();
	int point = loginMember.getPoint();
	
	Date _birthday = loginMember.getBirthday(); // Date
	String birthday = _birthday != null ? _birthday.toString() : "";
	
	Gender gender = loginMember.getGender();

	// 취미 List로 처리하기
	String hobby = loginMember.getHobby();
	List<String> hobbies = null;
	if(hobby != null) {
		hobbies = Arrays.asList(hobby.split(","));
	}
	
	
%>
<section id=enroll-container>
	<h2>회원 정보</h2>
	<form name="memberUpdateFrm" method="post" action="<%= request.getContextPath() %>/member/memberUpdate">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" name="memberId" id="memberId" value="<%= memberId %>" readonly>
				</td>
			</tr>
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="name" id="name" value="<%= name %>"  required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= birthday %>"><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= email %>"><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%= phone %>" required><br>
				</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td>	
					<input type="text" placeholder="" name="point" id="point" value="<%= point %>" readonly><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
			       		 <input type="radio" name="gender" id="gender0" value="M" <%= gender == Gender.M ? "checked" : "" %> />
						 <label for="gender0">남</label>
						 <input type="radio" name="gender" id="gender1" value="F">
						 <label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" <%= hobbies != null && hobbies.contains("운동") ? "checked" : "" %>><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" <%= hobbies != null && hobbies.contains("등산") ? "checked" : "" %>><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" <%= hobbies != null && hobbies.contains("독서") ? "checked" : "" %>><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임" <%= hobbies != null && hobbies.contains("게임") ? "checked" : "" %>><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" <%= hobbies != null && hobbies.contains("여행") ? "checked" : "" %>><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
        <input type="submit" value="정보수정" />
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
</section>
<script>
// deleteMember 함수가 url을 요청(Post 방식으로)
const deleteMember = () => {
	const frm = document.memberUpdateFrm;
	const servletUrl = "<%= request.getContextPath()%>/member/memberDelete";
	
	if(confirm("정말 탈퇴하시겠습니까?")) {
		frm.action = servletUrl;
		frm.submit();
		
		//document.memberDelFrm.submit();
	}
	
	// fetch써서 이어주면 좋다
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
