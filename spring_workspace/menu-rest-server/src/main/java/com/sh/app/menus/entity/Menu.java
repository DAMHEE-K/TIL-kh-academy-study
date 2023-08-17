package com.sh.app.menus.entity;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * allocationSize : 캐시 값
 * 
 * JPA :
 * @Entity 어노테이션을 달아두면 알아서 테이블을 만들어줌
 * (콘솔창으로 확인 가능)
 */
						// 제너레이터 이름, 실제 시퀀스 이름
@SequenceGenerator(name = "SEQ_MENU_ID", sequenceName = "SEQ_MENU_ID",initialValue = 21, allocationSize = 1)
@Entity
@DynamicUpdate // update시에 변경된 필드만 쿼리문에 포함
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	@Id	// Pk값이라는 뜻임
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_ID") // 시퀀스 설정
	private Long id; // pk는 long으로 관리 (null을 확인하기 위해서 참조형으로)
	
	@Column(nullable = false) // not null
	private String restaurant;
	
	@Column(nullable = false) // not null
	private String name;
	
	@Column(nullable = false) // not null
	private int price;
	
	@Enumerated(EnumType.STRING) // Enum.name으로 처리 (기본 값은 숫자 Ordinal이기 때문에 String 설정해줌)
	private MenuType type;
	
	@Enumerated(EnumType.STRING)
	private MenuTaste taste;
	
	@CreationTimestamp // 자동으로 현재 시각으로 기본값 처리해줌
	private LocalDateTime createdAt;
	
	// 참고로 @Column을 적어주지 않아도 알아서 컬럼으로 설정해줌
	// 따라서 Column에 어떤 설정을 추가해야 할 경우에만 작성해도 된다.
	// 유니크 제약 조건은 Enum Class로 관리함
}
