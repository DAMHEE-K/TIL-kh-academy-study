package com.sh.app.menus.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;
import com.sh.app.menus.repository.MenusRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenusServiceImpl implements MenusService {
	@Autowired
	private MenusRepository menusRepository;

	@Override
	public List<Menu> findAll() {
//		return menusRepository.findAll(); // order by 없는 버전
		return menusRepository.findAllByOrderByIdDesc();
	}

	@Override
	public Menu findById(Long id) {
		return menusRepository.findById(id).orElse(null); // 조회결과가 null이면 null반환
	}

	@Override
	public Menu save(Menu menu) {
		return menusRepository.save(menu);
	}

	@Override
	public void deleteById(Long id) {
		menusRepository.deleteById(id);
	}

	@Override
	public List<Menu> findByType(MenuType type) {
		return menusRepository.findByType(type);
	}

	/**
	 * like 연산자 (포함)
	 */
	@Override
	public List<Menu> findByTaste(MenuTaste taste) {
		return menusRepository.findByTaste(taste);
	}
	
	@Override
	public List<Menu> findByNameLike(String name) {
		return menusRepository.findByNameLike("%" + name + "%");
	}
}
