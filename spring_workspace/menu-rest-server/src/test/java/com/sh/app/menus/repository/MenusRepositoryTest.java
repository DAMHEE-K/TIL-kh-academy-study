package com.sh.app.menus.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;


/**
 * @SpringBootTest
 * - spring context 로딩을 지원한다 (즉, 빈 사용 가능)
 * - junit5, assertj, mock 의존 사용 가능
 */
@SpringBootTest
class MenusRepositoryTest {
	
	@Autowired
	MenusRepository menusRepository;
	
	@Test
	void menusRepositoryIsNotNull() {
		assertThat(menusRepository).isNotNull();
	}
	
	@DisplayName("메뉴 전체 조회")
	@Test
	public void findAll() throws Exception {
		List<Menu> menus = menusRepository.findAll();
		assertThat(menus).isNotNull().isNotEmpty();
	}
	
	
	@DisplayName("메뉴 한건 조회")
	@ParameterizedTest
	@ValueSource(longs = {1, 2, 3})
	public void findById(Long id) throws Exception {
		assertThat(id).isNotNull();
		
		// Optional<T> null 값을 포함한 타입. Menu 객체가 있을 수도 있고, null값을 포함할수도 있음
		Optional<Menu> maybeMenu = menusRepository.findById(id);
		Menu menu = maybeMenu.get(); // null인 경우 NoSuchElementException
		assertThat(menu).isNotNull();
		assertThat(menu.getId()).isNotNull();
		assertThat(menu.getRestaurant()).isNotNull();
		assertThat(menu.getName()).isNotNull();
	}
	
	@DisplayName("메뉴 등록")
	@Test
	@Transactional // DML 처리 후 다시 롤백 (테스트 디비가 들어가지 않도록)
	void createMenu() throws Exception {
		Menu menu = Menu.builder()
				.restaurant("사카모토네 해장국")
				.name("천엽텐동")
				.type(MenuType.jp)
				.taste(MenuTaste.mild)
				.build();
		
		menusRepository.save(menu);
		assertThat(menu.getId()).isNotNull();
//		assertThat(menu.getCreatedAt()).isNotNull();
	}
	
	@DisplayName("메뉴 수정(update)")
	@ParameterizedTest
	@ValueSource(longs = {1, 2, 3})
	@Transactional
	void updateMenu(Long id) throws Exception {
		assertThat(id).isNotNull();
		
		Menu menu = menusRepository.findById(id).get();
		String newName = menu.getName() + "@@@@@@";
		String newRestaurant = menu.getRestaurant() + "////";
		int newPrice = menu.getPrice() * 2;
		
		menu.setName(newName);
		menu.setRestaurant(newRestaurant);
		menu.setPrice(newPrice);
		
		menusRepository.save(menu);
		
		// 저장된 메뉴를 새로 조회함
		Menu menu2 = menusRepository.findById(id).get();
		
		assertThat(menu2.getName()).isEqualTo(newName);
		assertThat(menu2.getRestaurant()).isEqualTo(newRestaurant);
		assertThat(menu2.getPrice()).isEqualTo(newPrice);
	}
	
	
	@DisplayName("메뉴 삭제")
	@ParameterizedTest
	@ValueSource(longs = {1, 2, 3})
	@Transactional
	void deleteMenu(Long id) throws Exception {
		assertThat(id).isNotNull();
		menusRepository.deleteById(id); // return타입 void
		
		// 예외가 일어날 것에 대해서도 테스트가 가능함
		// 이 코드가 실행되면 예외가 던져질 것이다
		assertThatThrownBy(() -> {
			// 삭제가 성공적으로 되면 null일테니 NoSuchElementException 발생
			Menu menu = menusRepository.findById(id).get();
		}).isExactlyInstanceOf(NoSuchElementException.class);
	}
	
}
