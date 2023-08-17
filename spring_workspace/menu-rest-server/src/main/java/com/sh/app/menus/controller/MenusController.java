package com.sh.app.menus.controller;

import java.net.URI;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sh.app.menus.dto.MenuDto;
import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;
import com.sh.app.menus.service.MenusService;

import lombok.extern.slf4j.Slf4j;

// @CrossOrigin(origins ="http://localhost:8080")
@RestController // @Controller 모든 핸들러에 @ResponseBody 어노테이션 적용해줌
				// @ResponseBody 핸들러에 반환된 자바 객체를 Response Body에 써줌
@RequestMapping("/menus")
@Slf4j
public class MenusController {
	
	@Autowired
	private MenusService menusService;
	
	
	// "localhost:1000/menus" 요청이 오면 무조건 findAll 실행
	@GetMapping // 뒤에 url 없는 경우에 생략 가능함
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(menusService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Menu menu = menusService.findById(id);
		if(menu == null)
				return ResponseEntity.notFound().build(); // 없는 번호 조회하면 404 뜨도록 설정
		return ResponseEntity.ok(menusService.findById(id));
	}
	
	/**
	 * @RequestBody
	 * - json으로 객체를 전달 받을시 사용함
	 * - 요청 메세지 body로 json데이터를 읽어서 해당 dto로 변환
	 * @param dto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> createMenu(@Valid @RequestBody MenuDto dto) {
		log.debug("dto = {}", dto);
		// dto -> entity 변환후 요청
		Menu menu = dto.toMenu();
		return ResponseEntity.ok(menusService.save(menu));
//		return ResponseEntity.created(URI.create("/menus/" + menu.getId())).build();
	}
	
	
	/**
	 * 업데이트 쿼리
	 * JSON 으로 값을 받기 위해서 @RequestBody 역시 추가
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<?> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuDto dto) {
		// 한 건 조회
		Menu menu = menusService.findById(id);
		if(menu == null)
			return ResponseEntity.notFound().build();
		
		// 수정
		dto.toMenu(menu); // 요청에서 넘어온 dto값으로 조회된 메뉴의 속성들을 덮어씌움
		menusService.save(menu); // JPA에서는 save라는 용어 사용
								 // JPA 특징 : 모든 컬럼을 업데이트 해버림
		return ResponseEntity.ok(menusService.findById(id));
	}
	
	/**
	 * 삭제 쿼리
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
		menusService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/type/{type}")
	public ResponseEntity<?> findByType(@PathVariable MenuType type) {
		return ResponseEntity.ok(menusService.findByType(type));
	}
	
	@GetMapping("/taste/{taste}")
	public ResponseEntity<?> findByTaste(@PathVariable MenuTaste taste) {
		return ResponseEntity.ok(menusService.findByTaste(taste));
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(String name) {
		return ResponseEntity.ok(menusService.findByNameLike(name));
	}
}
