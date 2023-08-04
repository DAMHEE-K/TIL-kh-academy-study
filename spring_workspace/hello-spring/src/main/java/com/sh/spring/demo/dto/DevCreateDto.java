package com.sh.spring.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.sh.spring.demo.entity.Dev;
import com.sh.spring.demo.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dto 클래스는 요청 / 응답의 규격을 정의한 클래스
 * - 용도별로 여러개 만들어서 사용 가능. DevCreateDto, DevUpdateDto, ...
 *
 * 유효성 검사
 * 문자관련
 * @NotNull null을 허용하지 않음. "" " " 허용
 * @NotEmpty null과 "" 허용하지 않음. " "는 허용
 * @NotBlank null, "", " " 허용하지 않음.
 * @Pattern 정규식 int/Integer타입 사용 불가능(문자열만 가능)
 * 
 * 숫자 관련
 * @Min
 * @Max
 * @Size
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevCreateDto {
	// 기본적으로 message속성을 가지고 있기 때문에, 사용자에게 보내고 싶은 message를 쓰면 됨
	@NotNull(message="이름은 필수입니다.")
	@Pattern(regexp="[가-힣]{2,}", message="이름은 한글 2글자 이상이여야 합니다.")
	private String name;
	
	@NotNull(message="경력은 필수입니다.")
	@Min(value = 1, message="경력은 0이하의 숫자일 수 없습니다.")
	private Integer career;
	
	private Gender gender;
	
	@NotBlank(message="이메일은 필수입니다.")
	private String email;
	
	@NotNull(message="하나 이상의 언어를 선택해주세요.")
	private List<String> lang;
	
}
