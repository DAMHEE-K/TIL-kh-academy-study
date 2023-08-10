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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * allocationSize : 캐시 값
 */
						// 제너레이터 이름, 실제 시퀀스 이름
@SequenceGenerator(name = "SEQ_MENU_ID", sequenceName = "SEQ_MENU_ID",initialValue = 21, allocationSize = 1)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MENU_ID")
	private Long id; // pk는 long으로 관리
	
	@Column(nullable = false) // not null
	private String restaurant;
	
	@Column(nullable = false) // not null
	private String name;
	
	@Column(nullable = false) // not null
	private int price;
	
	@Enumerated(EnumType.STRING) // Enum.name으로 처리 (기본 값은 숫자 Ordinal)
	private MenuType type;
	
	@Enumerated(EnumType.STRING)
	private MenuTaste taste;
	
	@CreationTimestamp // 자동으로 현재 시각으로 기본값 처리해줌
	private LocalDateTime createdAt;
}
