package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
public class SecurityConfig {
	
	
	//@EnableWebSecurity 애너테이션을 사용하면 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
        http
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
        	// 모든 인증되지 않은 요청을 허가한다
        .csrf((csrf) -> csrf
                .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
    			// /h2-console/로 시작하는 URL은 CSRF 검증을 하지 않는다는 설정을 추가
        .headers((headers) -> headers
        		.addHeaderWriter(new XFrameOptionsHeaderWriter(
        				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
        .formLogin((formLogin)-> formLogin
        		.loginPage("/user/login")
        		.defaultSuccessUrl("/"))
        .logout((logout) -> logout
        		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
        		.logoutSuccessUrl("/")
        		.invalidateHttpSession(true))
        ;
        return http.build();
    
    // 스프링 시큐리티 적용하면 CSRF 작용
    // CSRF?
    // CSRF(cross site request forgery)는 웹 사이트 취약점 공격을 방지를 위해 사용하는
    // 기술. 스프링 시큐리티가 CSRF 토큰 값을 세션을 통해 발행하고 웹 페이지에서는 폼 전송시에 해당 토큰을 함께 전송하여 실제 웹 페이지에서 작성된 데이터가 전달되는지를 검증하는 기술이다.
    
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	// 인증담당
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
