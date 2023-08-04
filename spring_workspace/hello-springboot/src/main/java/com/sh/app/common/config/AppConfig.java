package com.sh.app.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sh.app.common.interceptor.AuthNullCheckInterceptor;
import com.sh.app.common.interceptor.LogIntercepter;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogIntercepter())
				.addPathPatterns("/**")
				.excludePathPatterns("/resources/**");
		
		registry.addInterceptor(new AuthNullCheckInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/resources/**", "/member/memberLogin.do");
		
	}
		
}