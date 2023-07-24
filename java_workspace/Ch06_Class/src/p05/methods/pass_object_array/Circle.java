package p05.methods.pass_object_array;

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
