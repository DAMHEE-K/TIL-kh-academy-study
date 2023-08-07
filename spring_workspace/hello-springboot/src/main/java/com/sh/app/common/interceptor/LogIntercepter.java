package com.sh.app.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * HandlerMapping에 따라 요청에 맞는 컨트롤러를 찾아 연결
 * HandlerInterceptor : 중간에 가로채는 것
 * 
 * - preHandle : HandlerAdapter를 통한 controller 호출 전. 컨트롤러 공통코드 구현(인증)
 * - postHandle : HandlerAdapter를 통한 controller 호출 후. 핸들러 반환값에 대한 전 처리
 * - afterCompletion : view단 처리 후. 응답처리관련 공통코드
 */
@Slf4j
public class LogIntercepter implements HandlerInterceptor {

	/**
	 * preHandle의 리턴값은 boolean
	 * - true 이후 핸들러 코드 정상 호출
	 * - false 핸들러 코드 호출없이 afterCompletion 호출
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("{} {}", request.getMethod(), request.getRequestURI());
		log.debug(">>>>>> Handler 호출 전");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, mav);
		log.debug(">>>>>> Handler 리턴 후 mav = {}", mav);
		
		Map<String, Object> model = mav.getModel();
		String viewName = mav.getViewName();
		log.debug("model = {}", model);
		log.debug("viewName = {}", viewName);
	}

	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("<<<<<< view단 처리 완료!\n\n");
	}
}
