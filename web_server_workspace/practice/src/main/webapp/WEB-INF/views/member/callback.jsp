<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login 구현하기기</title>
</head>
<body>
  <%
    String clientId = "YOUR_CLIENT_ID";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "YOUR_CLIENT_SECRET";//애플리케이션 클라이언트 시크릿값";
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    String redirectURI = URLEncoder.encode("YOUR_CALLBACK_URL", "UTF-8");
    String apiURL;
    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
    apiURL += "client_id=" + clientId;
    apiURL += "&client_secret=" + clientSecret;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&code=" + code;
    apiURL += "&state=" + state;
    
    // 출력 포맷이 json인 access_token을 String형식으로 받아야함
    String access_token = ""; // 초기화
    String refresh_token = "";
    System.out.println("apiURL="+apiURL);
    try {
      URL url = new URL(apiURL); //apiURL 주소에 대한 URL 객체 생성
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      // 프로토콜이 http://이기 때문에 HttpURLConnection로 다운캐스팅
      // openConnection() 메소드는 url 객체에 대한 연결을 담당하는 URLConnection 객체를 반환
      
      conn.setRequestMethod("GET");
      
      int responseCode = conn.getResponseCode(); // 응답 메세지 코드 가져오기 200, 300, 404...
      
      BufferedReader br;
      System.out.print("responseCode="+responseCode);
      
      if(responseCode==200) { // 정상 호출
    	br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {  // 에러 발생
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      
      String inputLine;
      
      StringBuffer res = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        res.append(inputLine);
        // res에 추가
      }
      br.close();
      
      if(responseCode==200) {
        out.println(res.toString());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  %>
</body>
</html>