package com.sh.app.menus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sh.app.menus.entity.Menu;
import com.sh.app.menus.entity.MenuTaste;
import com.sh.app.menus.entity.MenuType;

/**
 * Entity 클래스와 1:1 관계의 Repository 인터페이스
 * 
 * - jpa가 제공하는 기본 쿼리 메소드를 사용할 수 있음(CRUD)
 * - 커스텀 쿼리 메소드 작성 가능(특정 컬럼 기준, 연산 종류, 정렬 등등 추가 가능)
 * - jpql 쿼리 작성 가능 (jpa만의 쿼리 문법이 있음, 이게 더 효율적임)
 * - native query (SQL, 오라클 전용 쿼리, 가급적 사용 자제하는게 좋다)
 * - queryDsl java 코드를 통한 쿼리 생성 가능
 */
public interface MenusRepository extends JpaRepository<Menu, Long>{
												// <엔티티, 기본키 타입>
	List<Menu> findAllByOrderByIdDesc();

	List<Menu> findByType(MenuType type);

	List<Menu> findByTaste(MenuTaste taste);

	List<Menu> findByNameLike(String string);
	
	List<Menu> findByTasteAndType(MenuType type, MenuTaste taste);
	// JPA가 이해할 수 있는 선에서 And, Or 사용 가능함
	// JPA가 이해할 수 없으면 서버 시작하면서 컴파일 오류가 남
	
}
