package com.sh.ajax.celeb.model.manager;

import java.util.ArrayList;
import java.util.List;

import com.sh.ajax.celeb.model.vo.Celeb;
import com.sh.ajax.celeb.model.vo.CelebType;

/**
 * db 대신 사용할 메모리 db
 * 싱글톤 패턴 - 프로그램 운영 중에 딱 하나의 객체만 생성해서 사용하는 패턴
 * - private 생성자 : 외부에서 호출 불가(외부에서 객체 생성할 수 없다.)
 * - getInstance static 메소드를 통해서만 객체를 얻어갈 수 있도록 함
 * 
 */
public class CelebManager {
	private static CelebManager INSTANCE;
	private List<Celeb> celebs = new ArrayList<>();
	
	private CelebManager () {
		this.celebs.add(new Celeb(1, "박보검", "parkBogum.jpg", CelebType.ACTOR));
		this.celebs.add(new Celeb(2, "쥴리아 로버츠", "juliaRoberts.jpg", CelebType.ACTOR));
		this.celebs.add(new Celeb(3, "맷 데이먼", "matt_Damon.jpg", CelebType.ACTOR));
		this.celebs.add(new Celeb(4, "차은우", "차은우.png", CelebType.SINGER));
		this.celebs.add(new Celeb(5, "춘리", "춘리.png", CelebType.MODEL));
		this.celebs.add(new Celeb(6, "카리나", "카리나.png", CelebType.SINGER));
		this.celebs.add(new Celeb(7, "정재영", "정재영.jpg", CelebType.ACTOR));
		this.celebs.add(new Celeb(8, "아이유", "아이유.jpg", CelebType.ACTOR));
		this.celebs.add(new Celeb(9, "오킹", "오킹.jpg", CelebType.ENTERTAINER));
		this.celebs.add(new Celeb(10, "김고은", "김고은.jpg", CelebType.ACTOR));
	}
	
	public static CelebManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CelebManager();
		}
		return INSTANCE;
	}

	public List<Celeb> getCelebs() {
		return celebs;
	}
	
}
