package p06.constructor_this;

// this : constructor method 의미
// this(1, "플레인피자");를 실행하면, Circle(double radius, String name) 호출함
// Constructor를 coding : 입력값이 valid값인지 반드시 checking하는 code 필요
// - 문제점 : 
//     1) 유사한 constructor들이 많을 경우 constructor마다 중복해서 똑같은 coding 필요
//     2) 유효값을 체크하는 코딩 내용이 변경되면 모든 생성자 코드를 수정해야 하고,
//        수정할 때 잘 못 수정하면 개발 시간이 많이 소요됨
// - 해결책 : constructor this() 사용
//         DRY(Don't Repeat Yourself) => 똑같은 code를 중복 코딩X


public class Circle {
	double radius = 1;
	String name = "플레인피자";
	
	public Circle() {
		this(1, "플레인피자");
		// this(), super()는 무조건 맨 윗 줄에 와야함
		System.out.println("default constructor 호출");
	}
	
	public Circle(double radius) {
		this(radius, "플레인피자");
		System.out.println("constructor Circle(double radius) 호출");
	}
	
	public Circle(String name) {
		this(1, name);
		System.out.println("constructor Circle(String name) 호출");
	}
	
	public Circle(double radius, String name) {
		super();
		this.radius = radius;
		this.name = name;
		System.out.println("constructor Circle(double radius, String name) 호출");
	}
	
	
	// 원의 둘레
	public double getPerimeter() {
		return 2*Math.PI*this.radius;
	}
	
	// 원의 면적구하는 메소드
	public double getArea() {
		return this.radius*this.radius*Math.PI;
	}	
	
	// 피자의 정보를 알려주는 메소드
	public void printCircleInformation() {
		System.out.println("피자 이름 : " + name + ", 반지름 : " + radius +
				", 피자면적 : " + getArea() + ", 피자 둘레 : " + getPerimeter());
	}
	
	

}
