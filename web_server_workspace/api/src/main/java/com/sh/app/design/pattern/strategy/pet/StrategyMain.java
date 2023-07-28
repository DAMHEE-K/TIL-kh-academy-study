package com.sh.app.design.pattern.strategy.pet;

/**
 * 전략패턴 Strategy Pattern
 * - GoF 디자인 패턴 책에서 소개한 패턴 중 하나
 * 
 * 구성요소
 * 1. Contextv : Person Strategy를 사용하는 클래스
 * 2. Strategy : Pet 규격을 제공하는 클래스 (인터페이스 / 추상클래스)
 * 3. ConcreteStrategy : Dog | Cat Strategy 실제 구현 클래스
 * 
 */
public class StrategyMain {

	public static void main(String[] args) {
		Person person = new Person("홍길동", new Dog("구리구리"));
		person.play(); // 홍길동이 구리구리와 놀고있습니다
		
		Person person2 = new Person("신사임당", new Cat("쁨"));
		person2.play();
		
		Person person3 = new Person("이순신", new Snake("츄릅"));
		person3.play();
	}

}
