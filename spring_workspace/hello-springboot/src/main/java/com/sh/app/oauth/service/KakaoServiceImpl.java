package com.sh.app.oauth.service;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoServiceImpl implements KakaoService {
	
	@Value("${oauth.kakao.authorize-uri}")
	private String AUTHORIZE_URI;

	@Value("${oauth.kakao.redirect-uri}")
	private String REDIRECT_URI;
	
	@Value("${oauth.kakao.rest-api-key}")
	private String REST_API_KEY;
	
	@Value("${oauth.kakao.token-uri}")
	private String TOKEN_URI;
	
	@Value("${oauth.kakao.scope}")
	private String SCOPE;
	
	@Value("${oauth.kakao.resource-uri-host}")
	private String RESOURCE_URI_HOST;
	

	
	@Override
	public String getAuthorizeUri() {
		return AUTHORIZE_URI
				+"?redirect_uri=" + REDIRECT_URI
				+"&response_type=code"
				+"&client_id=" + REST_API_KEY
				+"&scope=" + SCOPE;
	}
	
	
	/**
	 * 발급된 code로 token(access_token, refresh_token) 발급요청
	 * - client : hello-springboot (요청보내는 애)
	 * - server : kakao authorization server(받는 애)
	 * 
	 * token
	 * - 한정된 권한 정보, 만료 기한이 있는 키
	 * - access_token : 사용자 정보, 특정 권한 수행할 수 있는 키. 만료시간이 짧음. (보통 30초)
	 * - refresh_token : access_token을 재발급하는 용도의 키. 만료시간이 상대적으로 길다(보통 2시간)
	 * - 예전에는 db 등에 token을 보관하고 꺼내썼지만, 최근엔 session에 보관을 추천함.
	 */
	@Override
	public Map<String, Object> getTokens(String code) {
		RestTemplate restTemplate = new RestTemplate(); // 타서버로의 요청객체
		// 요청 header, 사용자입력값
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
		
		String uri = TOKEN_URI
				   + "?grant_type=authorization_code"
				   + "&client_id=" + REST_API_KEY
				   + "&redirect_uri=" + REDIRECT_URI
				   + "&code=" + code;
		ResponseEntity<?> responseEntity = 
				 restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity, Map.class);
		return (Map<String, Object>) responseEntity.getBody();
	}

	
	/**
	 * accessToken으로 사용자 정보 요청하기
	 * - client : hello-springboot
	 * - server : kakao resource server
	 */
	@Override
	public Map<String, Object> getProfile(String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		// 헤더에 Authorization : Bearer XXXXXX 액세스토큰 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBearerAuth(accessToken);
		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
		String uri = RESOURCE_URI_HOST + "/v2/user/me";
		
		ResponseEntity<Map<String, Object>> responseEntity = 
				restTemplate.exchange(
						URI.create(uri), 
						HttpMethod.GET, 
						httpEntity, 
						new ParameterizedTypeReference<Map<String, Object>>(){});
							// 익명 클래스 : 인터페이스나 추상클래스를 객체화할 때 사용함
							// 프로그램에서 일시적으로 한번만 사용되고 버려지기 때문에 이름을 붙이지 않음 
		
		return responseEntity.getBody();
	}
}