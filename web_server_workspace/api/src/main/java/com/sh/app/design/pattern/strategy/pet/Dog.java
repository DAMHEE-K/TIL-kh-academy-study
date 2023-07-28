package com.sh.app.design.pattern.strategy.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog implements Pet {
	// @에 의해서 getter/setter가 만들어져 있기 때문에
	// 자동 오버라이드
	private String name;
}
