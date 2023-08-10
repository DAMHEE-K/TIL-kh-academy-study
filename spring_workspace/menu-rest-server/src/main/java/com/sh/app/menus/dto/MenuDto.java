package com.sh.app.menus.dto;

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
	@NotBlank(message ="가격은 필수입니다.")
	private Integer price;
	private MenuType type;
	private MenuTaste taste;
	
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
