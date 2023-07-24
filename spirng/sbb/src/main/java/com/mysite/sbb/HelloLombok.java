package com.mysite.sbb;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
// @setter
public class HelloLombok {
	// 롬복 : 생성자, getter / setter 자동으로 만들어줌 (착한자식..)
	//@RequiredArgsConstructor 매개변수 생성자 요구됨
	
	private final String hello; // (※ 매개변수 생성자 경우 final이 없는 속성은 생성자에 포함되지 않는다.)
	private final int lombok;

//	
//    public HelloLombok(String hello, int lombok) {
//        this.hello = hello;
//        this.lombok = lombok;
//    }
//	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("ㅎㅇ", 100);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}
}
