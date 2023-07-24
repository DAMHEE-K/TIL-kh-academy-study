package p08.import_ex.mycompany;

import java.util.Calendar;

import p08.import_ex.Kumho.BigWidthTire;
import p08.import_ex.hahkook.SnowTire;
import p08.import_ex.hyundai.Engine;

// 1. 다른 package에 있는 class를 사용하기 위해서는 import 키워드 사용
//   * 형식 : import package 이름 + class 이름 
// 2. package 이름은 class를 구분 짓는 단위
//   => class 이름이 동일하지만 package 이름이 다르면 다른 class로 인식함
//   => 즉, class 이름은 package 이름을 포함한 개념임

public class Car {
	Engine engine; // Engine 타입의 객체를 쓰기 위해서 hyundai 패키지 import
	SnowTire snowTire; // SnowTire 타입의 객체를 사용하기 위해서 hahkook import
	BigWidthTire bigTire; //BigWidthTire 타입의 객체를 사용하기 위해서 Kumho import
	
	// Tire tire1; 
	// Tire cannot be resolved to a type
	// hankook과 kumho 둘 다 Tire 클래스가 있기 때문에, 무엇을 선택해야 할지 모르니 에러가 나옴
	
	p08.import_ex.Kumho.Tire tire2; 
	// 앞에 패키지 명을 명시해주면 오류 없이 객체 생성 가능함
	
	Calendar calrendar; // Calendar 라이브러리 사용하기 위해서는 
						// import java.util.Calendar; 필요
	
	
	
}
