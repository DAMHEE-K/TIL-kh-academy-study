package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	@ResponseBody // hello 메서드의 응답 결과가 문자열 그 자체임을 나타낸다.
	public String hello() {
		//URL명과 메서드명은 동일할 필요는 없다. 
		return "Hello 즐먹어~";
		//이처럼 단순한 문자열 보다는 HTML 파일과 같은 템플릿을 주로 사용한다.
	}
}
