package com.sh.app.emp.service;

import java.util.List;
import java.util.Map;

import com.sh.app.emp.entity.Emp;
import com.sh.app.emp.entity.EmpDto;

public interface EmpService {
	List<Emp> findAll();
	List<Emp> search1(Map<String, Object> filters);
	List<EmpDto> search2(Map<String, Object> filters);
	List<EmpDto> search3(Map<String, Object> filters);
}
