package com.sh.app.demo.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sh.app.demo.dto.DevCreateDto;
import com.sh.app.demo.dto.DevUpdateDto;
import com.sh.app.demo.entity.Dev;

/**
 * @Repository
 * - @Component 상속
 * - mvc구조의 dao(repository) 클래스 등록
 *
 */
// @Repository
public class DemoRepositoryImpl implements DemoRepository {
	@Autowired
	private SqlSessionTemplate session;
	
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
