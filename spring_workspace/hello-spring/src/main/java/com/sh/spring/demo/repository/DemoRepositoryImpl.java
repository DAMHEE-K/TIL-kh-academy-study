package com.sh.spring.demo.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sh.spring.demo.dto.DevCreateDto;
import com.sh.spring.demo.dto.DevUpdateDto;
import com.sh.spring.demo.entity.Dev;

/**
 * @Repository - @Component 상속 - mvc 구조의 DAO(repository) 클래스 등록
 */
@Repository
public class DemoRepositoryImpl implements DemoRepository {

	@Autowired
	private SqlSessionTemplate session; // 의존주입
	// 트랜잭션 처리를 위해서 서비스에서 만들던 session을 repository에 만듦

	@Override
	public int insertDev(DevCreateDto devDto) {
		return session.insert("demo.insertDev", devDto);
	}

	@Override
	public List<Dev> findAllDev() {
		return session.selectList("demo.findAllDev");
	}

	@Override
	public Dev findDevById(int id) {
		return session.selectOne("demo.findDevById", id);
	}

	@Override
	public int updateDev(DevUpdateDto dev) {
		return session.update("demo.updateDev", dev);
	}
	
	@Override
	public int deleteDev(int id) {
		return session.delete("demo.deleteDev", id);
	}
}
