package com.sh.app.common;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "mybatis-config.xml";
		
		try {
			// FactoryBuilder
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			
			// Factory
			// 파일을 읽어서 inputStream 객체로 반환
			SqlSessionFactory factory = builder.build(Resources.getResourceAsStream(resource));

			// Session
			session = factory.openSession(false); // autoCommit false로 지정 트랜잭션 처리를 위해서 false로 설정
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return session;
	}
}
