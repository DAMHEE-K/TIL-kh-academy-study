package com.sh.ajax.classmate.model.service;

import java.util.ArrayList;
import java.util.List;

import com.sh.ajax.classmate.model.manager.ClassmateManager;
import com.sh.ajax.classmate.model.vo.Classmate;

public class ClassmateService {
	private final ClassmateManager classManager = ClassmateManager.getInstance();
	
	public List<Classmate> findAll() {
		return classManager.getClassmates();
	}

	public List<Classmate> findByName(String term) {
		List<Classmate> classmates = findAll(); //  findAll메소드를 사용해서 전체 조회된 값을 classmates변수에 담음
		
		List<Classmate> result = new ArrayList<>(); // 검색 결과값을 저장할 새로운 ArrayList 객체 생성
		
		// findAll 메소드를 통해서 가져온 classmates 배열 순회
		for(Classmate classmate : classmates) {
			// String#contains : boolean 문자열 포함 여부
			if(classmate.getName().contains(term)) { // classmate에서 이름을 가져오고, 검색한 문자열(term)을 포함하고 있으면
				result.add(classmate); // 위에 선언간 결과값 List에 담기
			}
		}
		return result;
	}
}
