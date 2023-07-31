package com.sh.spring.demo.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sh.spring.demo.dto.DevDto;

/**
 * @Repository
 * - @Component 상속
 * - mvc 구조의 DAO(repository) 클래스 등록
 */
@Repository
public class DemoRepositoryImpl implements DemoRepository {

	@Autowired
	private SqlSessionTemplate session; // 의존주입
	// 트랜잭션 처리를 위해서 서비스에서 만들던 session을 repository에 만듦

	@Override
	public int insertDev(DevDto devDto) {
		return session.insert("demo.insertDev", devDto);
	}
	
	
}
