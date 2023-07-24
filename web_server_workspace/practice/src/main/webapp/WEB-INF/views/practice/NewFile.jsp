<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>NCS테스트</title>

</head>

<body>

	<h1>NCS테스트</h1>

	<form name="productCreateFrm">

		<fieldset>

			<legend>제품 등록폼</legend>

			<table>

				<tbody>

					<tr>

						<th><label for="prod-name">상품명</label></th> 얘랑

						<td><input type="text" name="prod-name" id="prod-name" /></td>

					</tr>

					<tr>

						<th><label for="prod-img">상품이미지</label></th> 얘가 넘어가겟네 서블릿으로

						<td><input type="file" name="prod-img" id="prod-img" /></td>

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

</body>
<script>
document.productCreateFrm.onsubmit = (e) => {
	const Data = new FormData(e.target);
	
	$.ajax(
		url : "<%= request.getContextPath() %>/product/productCreate>",
		data : frmData,
		processData : false,
		contentType : false,
		method : "POST",
		dataType : "json",
		success(responseData) {
			console.log(responseData);
			const {result, message} = responseData;
			alert(message);
			
			findAllCeleb();
		},
		complete() {
			e.target.reset(); // 폼 동기적 제출 방지
		}
		
	})
	
};
</script>

</html>