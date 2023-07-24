package com.sh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbc관련 static메소드를 제공
 * 예외처리를 미리 해서 클라이언트가 사용하기 쉽게 작성
 * 
 * - 드라이버 클래스 등록 (프로그램당 최초 1회) - static 초기화 블럭 사용
 * 
 * - Connection 생성
 * - commit / rollback
 * - close
 */
public class JdbcTemplate {
	
	private static String driverClass = "oracle.jdbc.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "student";
	private static String password = "student";
	
	{
		// 인스턴스 초기화 블럭 - 객체 생성시 마다 실행
		// 복잡한 연산에 의해 세팅해야 할 필드 전용
		// 필드 초기화 순서 : 타입별 기본값 - 명시적을 작성한 값 - 초기화블럭 설정값 - 생성자에게 설정값
	}
	
	static {
		// static초기화 블럭은 클래스가 최초 로딩될 때, 딱 한 번 실행
		// static변수 초기화용으로 사용
		// static필드 초기화 순서 : 타입별 기본값 - 명시적을 작성한 값 - 초기화블럭 설정값
		try {
			// 드라이버 클래스 등록 (프로그램 당 1최초 1번)
			Class.forName(driverClass);
			System.out.println("[드라이버 클래스가 등록되었습니다.]");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		}
	}
	
	
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false); // DQL요청시에도 크게 상관없음
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	// close 오버로딩
	public static void close(Connection conn) { 
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
