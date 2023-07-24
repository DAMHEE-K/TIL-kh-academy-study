package p01.basic;

// 1. Constructor 만드는 기준
// - 기본 생성자(default constructor)를 반드시 만드는 것을 권장
//   이유 : spring 등을 사용한 web application에서 class 만들 때 필수라고 생각하는 것이 좋음
// - 본인이나 다른 사용자(개발자)의 요구사항에 의해서 만듦
// 생성자 이름 == class 이름
// - method signature = method 이름 + parameter를 합친 것을 의미
// - 생성자 오버로딩 : 생성자의 이름은 동일하지만 생성자의 parameter의 data type은 틀린 경우 
//				    생성자를 중복 선언 가능. 
//					(다른 용어로 표현하면 생성자 signature가 틀린 경우에만 중복 선언 가능)
//				    단, field이름이 틀리더라도 data type이 동일하면 중복 선언 불가
//					error 내용 : Duplication method Circle(double) in type Circle

// 2. this의 정의 : 힙메모리에서 생성된 인스턴스의 주소
//	  => this라는 이름은 동일하지만 실제로 사용되는 인스턴스의 주소를 실시간으로 가짐
public class Circle {
	
	// 속성
	double radius = 1;
	double id = 1;
	String name = "플레인피자";
	
	// Default Constructor(생성자)
	public Circle() {
		System.out.println("1. this 주소 : " + System.identityHashCode(this));
		System.out.println("default constructor 호출됨");
	} //  기본 생성자, 매개변수가 없는 생성자
	
	public Circle(double radius) { // 매개변수 생성자
		super();
		System.out.println("2. this 주소 : " + System.identityHashCode(this));
		System.out.println("Circle(double radius) constructor 호출됨");
		this.radius = radius;
	}
	
//	public Circle(double id) { // 위 생성자와 data type이 동일하므로 생성자 오버로딩 성립 불가능
//		this.id = id;
//	}
	
	public Circle(String name) { // 매개변수 생성자
		super();
		System.out.println("3. this 주소 : " + System.identityHashCode(this));
		System.out.println("Circle(String name) constructor 호출됨");
		this.name = name;
	}
	
	public Circle(double radius, String name) { // 매개변수 생성자
		super();
		System.out.println("4. this 주소 : " + System.identityHashCode(this));
		System.out.println("Circle(double radius, String name) constructor 호출됨");
		this.radius = radius;
		this.name = name;
	}
	
	// getter / setter
	// getter : field값을 가져오는 method
	public double getRadius() {
		return radius;
	}
	// setter : field값을 설정하는 method 
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// 원의 면적
	public double getArea() {
		// 원의 면적 = 반지름*반지름*3.141592...
		return radius*radius*Math.PI;
							// Math 클래스의 상수 PI 
	}
	
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}

}
