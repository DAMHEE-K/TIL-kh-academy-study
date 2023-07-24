package p03.statics.variable_field;


// static 사용
// 1. field : 힙 메모리에 저장되는 것이 아니라 메모리 한 곳에서만 저장되고 관리됨
// 2. method : static으로 선언 가능
//    => static으로 선언된 method는 일반 field(힙 메모리에서 선언된 field == 인스턴스 변수)들 사용 불가
//       . static으로 선언된 method는 static field들만 사용 가능
//		 . static method 내에서 instance method 호출 불가
//    => 일반 instance method는 instance field뿐 아니라 static field 사용 가능
public class Circle {
	double radius;  // (==instance field, iv, 인스턴스 변수)
	static int numberOfInstances = 0; // new로 생성된 instance 갯수 관리용 field
	// static 변수는 객체 생성과 관계 없기 때문에, 생성자 메소드 작성할 때 제외하고 만듦
	
	public Circle() {
		this.radius = 1;
		numberOfInstances++; // 객체가 생성될 때 마다 1개씩 증가
	}
	
	public Circle(double radius) {
		super();
		this.radius = radius;
		numberOfInstances++;
	}
	
	// static method : 나는 static 변수, 메소드만 쓸거야
	// 인스턴스 사용하고 싶으면 static 빼야함!
	public static int getNumberOfInstances() {
		// numberOfInstances가 static 변수라고 해서,
		// numberOfInstances 값을 읽어올 메소드까지 꼭 static 메소드일 필요는 없음!
		
		// helloPrint(); static 메소드에서는 인스턴스 메소드 호출 불가
		return numberOfInstances;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
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
		// 굳이 static 메소드에서 인스턴스 변수를 사용하고 싶다면
		// Circle c = new Circle();
		// c.radius = 20;
		// 객체를 생성하여 사용하면 됨
		System.out.println("Hello, static Print");
	}
	

}
