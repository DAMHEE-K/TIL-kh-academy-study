package com.sh.app.member.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class MemberDetails extends Member implements UserDetails {
	
	/**
	 * WAS상에서 MemberDetails 객체 단위로 입출력 진행 시의 식별 번호
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SimpleGrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getUsername() { // PK컬럼값을 뜻함
		return this.getMemberId();
	}

	/**
	 * 만료된 사용자인가?
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 잠금(락)된 사용자인가?
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	/**
	 * 비밀번호가 유효한가?
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 활성화된 사용자인가?
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
