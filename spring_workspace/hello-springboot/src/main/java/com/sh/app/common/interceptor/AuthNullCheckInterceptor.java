package com.sh.app.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sh.app.member.entity.MemberDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthNullCheckInterceptor implements HandlerInterceptor{

	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			Authentication authentication = securityContext.getAuthentication();
			Object principal = authentication.getPrincipal();
			log.debug("principal = {}", principal);
			if(principal instanceof MemberDetails) {
				MemberDetails member = (MemberDetails) principal;
				if(member.getMemberId() == null) {
					response.sendRedirect(request.getContextPath() + "/member/memberLogin.do");
					return false; // Handler를 호출하지 않고 afterCompletion 처리 후 응답함.
				}
			}
			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
}
