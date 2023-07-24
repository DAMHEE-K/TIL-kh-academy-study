package sh.java.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class NetworkStudy {
	public static void main(String[] args) {
		NetworkStudy study = new NetworkStudy();
//		study.test1();
		study.test2();
		study.test3();
	}

	/**
	 * URLConnection
	 * - 특정 URL에 접속 후 응답 받아 처리하는 객체
	 */
	private void test3() {
		
		try {															// ? ~ : 쿼리스트링
			URL url = new URL("https://search.naver.com:443/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%96%BC&oquery=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%97%B4&tqi=ivGX4dp0JXVss4jCahCssssstGo-523776#abc");
			URLConnection conn = url.openConnection();
			
			// 바이트 기반 스트림과 문자 스트림을 연결하기 위해서 InputStreamReader 사용
			try(
					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					BufferedWriter bw = new BufferedWriter(new FileWriter("슈크림라떼 검색.html"));
					
			) {
				String data = null;
				while((data = br.readLine()) != null) {
					System.out.println(data); // html을 출력
					bw.write(data);
					bw.newLine(); // 개행
				}
			} // catch절 생략 (밑에 catch절에서 예외처리가 되었기 때문에 생략 가능)
			
		} catch (IOException e) {
			//MalformedURLException 이 IOException의 후손
			e.printStackTrace();
		}
		
	}

	/**
	 * URL
	 * - url을 parsing해서 각 정보를 관리하는 객체
	 */
	private void test2() {
		try {															// ? ~ : 쿼리스트링
			URL url = new URL("https://search.naver.com:443/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%96%BC&oquery=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%97%B4&tqi=ivGX4dp0JXVss4jCahCssssstGo-523776#abc");
			
			System.out.println(url.getProtocol()); // 프로토콜 구하기 : https 웹 통신규약, ftp | telnet | ssh ...
			System.out.println(url.getHost()); // search.naver.com
			System.out.println(url.getPort());
			System.out.println(url.getPath());
			System.out.println(url.getQuery()); //where=nexearch&query=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%96%BC&oquery=%EC%8A%88%ED%81%AC%EB%A6%BC%EB%9D%BC%EB%97%B4&tqi=ivGX4dp0JXVss4jCahCssssstGo-523776
			System.out.println(url.getRef()); // 북마크 값
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * InetAddress
	 * - ip 주소에 대한 정보를 가진 클래스
	 * - 생성자가 없고, static 메소드만 지원
	 */
	private void test1() {
		try {
			InetAddress naver = InetAddress.getByName("naver.com");
			System.out.println(naver.getHostAddress());
			
			InetAddress[] navers = naver.getAllByName("naver.com");
			// naver.com에 등록된 모든 ip를 배열로 만듦
			
			for(InetAddress ip : navers) {
				System.out.println(ip.getHostAddress());
			}
			
			// 내 컴퓨터에 대한 ip 주소 조회
			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println(localhost.getHostAddress());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
