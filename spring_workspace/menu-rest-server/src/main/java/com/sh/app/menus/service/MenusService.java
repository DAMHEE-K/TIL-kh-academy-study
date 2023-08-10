package com.sh.app.menus.service;

import java.util.List;

import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;

public interface MenusService {
	List<Menu> findAll();

	Menu findById(Long id);

	Menu save(Menu menu);

	void deleteById(Long id);

	List<Menu> findByType(MenuType type);

	List<Menu> findByTaste(MenuTaste taste);

	List<Menu> findByNameLike(String name);
}
