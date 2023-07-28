package com.sh.app.design.pattern.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j2
 * - 프로젝트 콘솔 출력 / 로그 파일 등의 기록을 관리하는 프레임 워크
 * - 설정한 레벨에 따라 제한적인 로깅이 가능
 * 
 * FATAL
 * ERROR
 * WARN
 * INFO
 * DEBUG
 * TRACE
 * 
 */
public class Log4j2Test {
	private static final Logger log = LogManager.getLogger(Log4j2Test.class);
	
	public static void main(String[] args) {
		// 운영중 사용하는 로그
		log.fatal("치명적인 오류!!!!");
		log.error("예외 발생!!!");
		log.warn("경고 - 현재 실행에는 문제 없지만, 잠재적인 오류 경고");
		log.info("정보 - 서비스 운영중에 필요한 정보 제공");
		
		// 개발자용 로그
		int i = 10;
		log.debug("개발에 필요한 출력 i = {}", i);
		log.trace("코드 흐름 파악");
	}

}
