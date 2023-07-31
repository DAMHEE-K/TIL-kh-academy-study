package com.sh.spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.spring.demo.dto.DevDto;
import com.sh.spring.demo.repository.DemoRepository;

/**
 * @Service
 * - @Component 상속
 * - mvc 구조의 service 클래스 등록(트랜잭션 처리 가능)
 *
 */
@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private DemoRepository demoRepository;

	@Override
	public int insertDev(DevDto devDto) {
		return demoRepository.insertDev(devDto);
	}
}
