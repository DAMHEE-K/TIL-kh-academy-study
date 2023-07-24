package com.sh.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

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
	
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	
	static {
		
		// resource/datesource.properties 설정값 읽어오기
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("resources/datasource.properties"));
			driverClass = prop.getProperty("driverClass"); // datasource.properties 파일과 변수명 똑같이!!! 주의
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
			/*
			System.out.printf("[%s]\n","초기값을 설정했습니다.");
			System.out.printf("[driverClass = %s]\n",driverClass);
			System.out.printf("[url = %s]\n",url);
			System.out.printf("[user = %s]\n",user);
			System.out.printf("[password = %s]\n",password);
			*/
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
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
