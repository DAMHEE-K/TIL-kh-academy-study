package com.sh.app.menus.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;

import lombok.Data;

@Data
public class MenuDto {
	
	@NotBlank(message ="음식점명은 필수입니다.")
	private String restaurant;
	
	@NotBlank(message ="메뉴명은 필수입니다.")
	private String name;
	
//	@NotBlank(message ="가격은 필수입니다.")
	@Min(value = 0, message = "가격은 0보다 크거나 같아야 합니다.")
	private Integer price;
	
	private MenuType type;
	private MenuTaste taste;
	
	public Menu toMenu(Menu menu) {
		menu.setRestaurant(restaurant);
		menu.setName(name);
		menu.setPrice(price);
		menu.setType(type);
		menu.setTaste(taste);
		return menu;
	}

	public Menu toMenu() {
		return Menu.builder()
				.restaurant(restaurant)
				.name(name)
				.price(price)
				.type(type)
				.taste(taste)
				.build();
	}
}
