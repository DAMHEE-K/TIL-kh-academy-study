package p04.field.encapsulation;

// 1. field에서 private으로 선언
//    - class 내부의 모든 method에 내에서는(생성자 포함) 자유롭게 사용 가능
//    - class 외부의 다른 class에서는 직접 사용 불가

//    - private 사용 목적
//      1) class 내부에서만 사용하고 외부의 다른 고객(다른 개발자)에게 알리고 싶지 않은 경우
//      2) 외부 사용자가 잘못 사용하는 것을 방지하고 싶은 경우 (Ex : 외부 사용자가 c.radius = -10; 등으로 잘 못 사용)
//         => 외부 사용자가 radius를 다른 방법으로 사용 가능하게 해줌 : getter/ setter
//    - public
//      1) 모든 사람(개발자)들이 사용 가능

// 2. data(field) encapsulation : field나 method들을 private으로 선언하여
//                                class 외부에서 직접 사용 못하게 하는 것

public class Circle {
	private double radius;  
	private static int numberOfInstances = 0; // new로 생성된 instance 갯수 관리용 field
	
	public Circle() {
		this.radius = 1;
		numberOfInstances++; // 객체가 생성될 때 마다 1개씩 증가
	}
	
	public Circle(double radius) {
		super();
		this.radius = radius;
		numberOfInstances++;
	}
	
	
	// getter method
	public double getRadius() {
		return radius;
	}

	public static int getNumberOfInstances() {
		return numberOfInstances;
	}
	
	// setter method
	public void setRadius(double radius) {
		if(radius >= 0) {
			this.radius = radius;
		} else {
			this.radius = 0;
		}
	}
	
	// 원의 면적구하는 메소드
	public double getArea() {
		return this.radius*this.radius*Math.PI;
	}
	
	// 원의 둘레구하는 메소드
	public double getPerimeter() {
		return 2*Math.PI*this.radius;
	}
	
	public void helloPrint() {
		staticHelloPrint(); // 인스턴스 메소드에서는 static 메소드 호출 가능
		System.out.println("Hello, Instance Print");
	}
	
	public static void staticHelloPrint() {
		System.out.println("Hello, static Print");
	}
	
	// instance method
	private void printPrivate() {
		System.out.println("Hello, private");
	}
	

}
