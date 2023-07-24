<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Javascript Ajax</title>
</head>
<body>
	<h1>Javascript Ajax</h1>
	<button id="btn1">GET</button>
	<button id="btn2">POST</button>

	<script>
	let xhr;
	
	/**
	 * 1. XMLHttpRequest 객체 생성
	 * 2. 응답처리 onreadyStatechange 핸들러 작성
	 * 3. 요청 open - send
	 
	 * Ajax에서 서버로부터의 응답을 확인하기 위해 사용하는 XMLHttpRequest 객체의 프로퍼티
	 1. readyState 프로퍼티 : XMLHttpRequest 객체의 현재 상태를 나타냄
	 2. status 프로퍼티 : 서버의 문서 상태
	 3. onreadystatechange 프로퍼티 : XMLHttpRequest 객체의 readyState 프로퍼티 값이 변할 때마다 자동으로 호출되는 함수를 설정
	 
	 */
	 
	btn2.onclick = () => {
		xhr = getXMLHttpReqeust();
		xhr.onreadystatechange = (e) => {
			if(xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
			}
		}
		
		xhr.open("POST", "<%=request.getContextPath()%>/javascript/test>");
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.send("name=신사임당&age=48")
	};
    btn1.onclick = () => {
	      xhr = getXMLHttpRequest();
	      console.log(xhr);
	      
	      xhr.onreadystatechange = (e) => {
	         console.log(xhr.readyState, e);
	         switch(xhr.readyState){
	         case 0: console.log("uninitialized"); break;
	         case 1: console.log("loading"); break; // open 호출
	         case 2: console.log("loaded"); break; // send 호출
	         case 3: console.log("interactive"); break; // 응답 일부 도착
	         case 4: console.log("completed"); // 모든 응답 완료
	            console.log(xhr.status); // 정상 실행 200
	            if(xhr.status == 200) {
	               const body = xhr.responseText;
	               alert(body);
	            }
	         }
	      };
	      xhr.open("GET", "<%=request.getContextPath()%>/javascript/test?name=홍길동&age=33");      
	      xhr.send();
		   }
		   // const getXMLHttpRequest = () => new XMLHttpRequest();
		   
	function getXMLHttpRequest(){
        // IE일 경우
        if(window.ActiveXObject){
            try{ // IE 9버전 이상일 경우
                return new ActiveXObject("Msxml2.XMLHTTP");
            } catch (ex) {
                // IE 8버전 이하일 경우
                try {
                    return new ActiveXObject("Microsoft.XMLHTTP");
                } catch (ex) {
                    return null;
                }
            }
        } 
        // IE이외의 브라우저일 경우
        else if(window.XMLHttpRequest) {
            return new XMLHttpRequest(); 
        } 
        else {
            return null;
        }
    };
	</script>
</body>
</html>