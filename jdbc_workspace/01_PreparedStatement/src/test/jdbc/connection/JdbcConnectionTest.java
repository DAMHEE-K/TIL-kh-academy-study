package test.jdbc.connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnectionTest {

	private String driverClass = "oracle.jdbc.OracleDriver";
								  // 패키지.패키지.클래스
								  // 오라클 드라이버를 DriverManager에 등록
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속 프로토콜@ip:port:sid 
	private String user = "student"; // 등록한 DB 계정의 정보
	private String password = "student";
	
	public static void main(String[] args) {
		
		JdbcConnectionTest instance = new JdbcConnectionTest();
		instance.test();
		instance.test2();
		
	}

	/**
	 * DML 버전 (try-catch-resource문 사용해서 자원반납)
	 */
	private void test2() {
		try {
			// 1. jdbc driver class 등록 (프로그램 1회)
			Class.forName(driverClass);
			// 2. Connection 생성(url, user, password) - 자동커밋 false 설정
			
			try(Connection conn = DriverManager.getConnection(url, user, password)) {
				conn.setAutoCommit(false); // 자동커밋 false 설정 (기본값은 true)
										   // DML 한 줄씩 실행될때마다 자동커밋되는거 방지
				
				// 3.PreparedStatement 생성
				// ? : 사용자 입력값이 들어갈 부분
				String sql = "insert into member values(?, ?, ?, ?, ?, default, default)";
				try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
					// 미완성 쿼리(?인 부분) 값대입
					pstmt.setString(1, "yoonbg"); // id
					pstmt.setString(2, "윤봉길"); // name
					pstmt.setString(3, "M");
					pstmt.setDate(4, Date.valueOf("1999-09-09")); // 문자열 sql.Date로 형변환
					pstmt.setString(5, "yoonbg@naver.com");
					
					// 4. 쿼리 실행 및 결과값(int) 받기
					int result = pstmt.executeUpdate(); // 행 단위로 처리되기 때문에 결과값이 int로 반환됨
					System.out.println(result + "행이 삽입되었습니다.");
					
					// 5. 트랜잭션 처리
					if(result > 0) conn.commit();
					else conn.rollback();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	

	/**
	 * DQL 버전
	 */
	private void test() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. jdbc driver class 등록 (프로그램 상 최초 1회) - JVM에 해당 클래스를 사용하겠다고 선언
			Class.forName(driverClass); 
			System.out.println("드라이버 클래스 등록 완료!");
			
			// 2. Connection 생성 (url, user, password)
			conn = DriverManager.getConnection(url, user, password);
			// DriverManager 클래스 : 데이터 원본(DBMS)에 JDBC 드라이버를 통하여 커넥션 만드는 역할
			System.out.println("Connection 객체 생성 성공!!");
			
			// 3. PreparedStatement 생성 (쿼리)
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 쿼리 실행 및 ResultSet 처리
			rset = pstmt.executeQuery(); // 조회한 결과를 담은 객체
			System.out.println("PreparedStatement 쿼리 실행 완료!");
			
			while(rset.next()) { // 반복문을 통해서 한 행씩 읽어와야 함
				// 현재 행의 정보를 가져옴
				// get타입명("컬럼명")
				String id = rset.getString("id");
				String name = rset.getString("name");
				Date birthday = rset.getDate("birthday"); // Date는 util이 아닌 sql 패키지의 Date 클래스를 사용해야 함
				int point = rset.getInt("point");
				
				System.out.printf("%s	%s	%s	%s\n", id, name, birthday, point);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //ojdbc 등록이 안되어있으면 예외 발생할 수 있음
		} catch (SQLException e) { // DB 접속 관련 최상위 예외 클래스
			e.printStackTrace();
		} finally {
			// 5. 모든 자원 반환 (생성 역순으로 반환)
			try {
				rset.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
