package edu.kh.oop.method.run;

import edu.kh.oop.method.model.service.MemberService;

public class MemberRun {
	 public   static   void main(String[] args) {
// [접근 제한자] [예약어]  반환형 메서드명 ([매개변수])
		 
		 MemberService service = new MemberService();
		 // MemberService에 기본 생성자가 작성되어 있지 않지만
		 // 문제없이 사용 가능
		 
		 // 왜? 컴파일러가 코드를 번역할 때
		 // 클래스 내부에 생성자가 하나도 없다면 자동으로 기본 생성자 추가해줌
	
		 // (주의) 생성자가 하나라도 있으면 자동 생성 안해줌
		 // The constructor MemberService() is undefined
		 
		 service.desplayMenu();
		 
	 }
}
