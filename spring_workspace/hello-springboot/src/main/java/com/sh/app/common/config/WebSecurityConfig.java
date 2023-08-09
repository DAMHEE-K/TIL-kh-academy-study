package com.sh.app.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@SuppressWarnings("deprecation")
@EnableWebSecurity // @Configuration 상속
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	// @Configuration : 설정 파일 또는 Bean 등록하기 위한 Annotation
	
	/**
	 * DB에서 사용자 인증 정보를 가져오는 역할
	 */
	@Autowired
	private UserDetailsService memberService;

	@Autowired
	private OAuth2UserService oauth2UserService;
	
	@Autowired
	private DataSource dataSource;

	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl(); // DAO 같은 놈
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}
	
	/**
	 * 비밀번호를 소금쳐서 알아보지 못하도록 바꿔주는 메소드
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Security 없이 처리될 요청 설정
	 * - security bypass 경로 지정
	 * - static파일 (js, css, images, ...)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 별도의 인증 없이 직접 접근이 가능함
		web.ignoring().mvcMatchers("/resources/**");
		// resources 하위 파일 모두 직접 접근이 가능함
	}
	
	
	/**
	 * 요청별 인증여부/권한여부 설정
	 * - permitAll : 무조건 허용
	 * - authenticated : 인증된 사용자의 접근 허용
	 * - anonymous : 익명 사용자의 접근 허용
	 * - hasRole : 사용자가 주어진 역할이 있다면 허용
	 * - hasAuthority : 사용자가 주어진 권한이 있다면 허용
	 * 
	 * configure 실행되면서 HttpSecurity 내부에 설정되어 있는 필터가 실행됨
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 특수한 요청 -> 일반적 요청 순으로 설정함
		http.authorizeRequests() // HTTP 요청을 승인하는 규칙을 지정
			.antMatchers("/", "/index.jsp").permitAll() // 인덱스 페이지는 권한 없이 접근 가능
//			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/member/memberCreate.do", "/member/checkIdDuplicate.do").anonymous()
			.antMatchers("/oauth/**").permitAll()
			.antMatchers("/board/boardList.do").permitAll()
			.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // ROLE_ 접두사 필요		
			.anyRequest().authenticated(); // 나머지 별도 요청들은 인증 필요
		
		// /login
		http.formLogin()
			.loginPage("/member/memberLogin.do") // 사용자 정의 로그인 페이지의 URL을 설정. 사용자가 인증 없이 액세스하려고 하면 이 페이지로 redirect
			.loginProcessingUrl("/member/memberLogin.do") // security 위임, 우리가 작성할 필요가 없음. 
			.usernameParameter("memberId") // 인증용 토큰을 이 값을 사용해서 만들겠다 표명하는 코드
			.defaultSuccessUrl("/") // 인증에 성공하면 /URL로 redirect
			.permitAll(); // loginPage에는 모두 접근 가능
		
		// /logout
		http.logout()
			.logoutUrl("/member/memberLogout.do") // 로그아웃 프로세스의 URL을 설정
			.logoutSuccessUrl("/")
			.permitAll();
		
		// csrf (cross site request forgery) 공격 대비 토큰 사용 (기본값)
		// csrf 공격 방지 토큰 사용
		// 서버쪽에서 유효한 폼만 제출이 가능함
		// 브라우저에서 폼을 get 방식으로 요청하면, 폼을 브라우저에 다시 보내면서
		// csrf 토큰을 함께 발행해줌.
		// 폼이 post 방식으로 제출될 때 해당 토큰에 대한 인증을 걸쳐야 함.
//		http.csrf().disable();
		
		
		// remember-me 관련
		http.rememberMe()
			.tokenRepository(tokenRepository())
			.key("hello-springboot-secret")
			.tokenValiditySeconds(60 * 60 * 24 * 14); // 2주
		
		
		// 소셜로그인 관련
		http.oauth2Login()
		.loginPage("/member/memberLogin.do")
		.userInfoEndpoint()
		.userService(oauth2UserService);
	}
	
	
	/**
	 * 실제 인증/인가를 담당하는 AuthenticationManager에 대한 설정
	 * - in memory
	 * - DB 사용자
	 * 
	 * AuthenticationManager : 사용자 인증을 처리하는 매니저
	 * 사용자가 로그인을 시도할 때 인증 매니저가 memberService를 사용하여 
	 * 사용자 세부 정보를 로드하고 지정된 패스워드 인코더를 사용하여 제공된 
	 * 비밀번호를 저장된 해시된 비밀번호와 비교하여 인증
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthenticati on()
//			.passwordEncoder(passwordEncoder())
//			.withUser("sinsa")
//			.password(passwordEncoder().encode("1234"))
//			.authorities("ROLE_USER")
//			.and()
//			.withUser("admin")
//			.password(passwordEncoder().encode("1234"))
//			.authorities("ROLE_USER", "ROLE_ADMIN");
		
		
		// AuthenticationProvider 생성을 위한 UserDetailService 구현체 등록
		// AuthenticationManager 인터페이스 구현체 ProviderManager가 여러개의 AuthenticationProvider를 가질 수 있다.
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
												// 인증 중(DB를 뒤지는 중) 비밀번호를 해싱해서 찾아야 하니까 사용
		// 인증 매니저를 사용자가 정의한 memberService로 구성함
		// memberService는 UserDetailsService 인터페이스를 구현함
		// 인증 매니저의 역할 : 사용자 이름, 비밀번호 및 권한과 같은 사용자별 데이터를 DB에서 로드하여 사용자를 인증하는 역할
		
		
	}
}
