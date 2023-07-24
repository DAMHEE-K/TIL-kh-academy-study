package p03.statics.variable_field;


// JVM이 main method 실행 방법 : class이름.main() => TestCircleWithStaticFields.main()
public class TestCircleWithStaticFields {
	int a = 10; // iv 선언 가능
	static int b = 20; // cv 선언 가능
	
	public TestCircleWithStaticFields() { // 생성자 가능
		System.out.println("main default constructor");
	}
	
	// static method
	public static void main(String[] args) {
		
		// Circle 생성자에 numberOfInstances++; 이 있기 때문에
		// 객체를 생성 할때마다 static 변수 numberOfInstances가 1개씩 증가됨
		// 모든 객체가 static 변수를 공유하기 때문에
		// Circle.numberOfInstances
		// c1.numberOfInstances
		// c2.numberOfInstances 똑같은 값이 나옴 (똑같은 변수를 공유하고 있는 것!)
		
		System.out.println("new로 인스턴스 생성 전 최초 Circle 인스턴수 갯수 : "
				+ Circle.getNumberOfInstances());
		
		Circle c1 = new Circle();
		System.out.println("c1 인스턴스 생성 후 Circle 인스턴스 갯수 : "
				+ Circle.getNumberOfInstances());
		
		Circle c2 = new Circle(5);
		System.out.println("c2 인스턴스 생성 후 Circle 인스턴스 갯수 : "
				+ Circle.numberOfInstances);
		Circle.numberOfInstances = 10;
		System.out.println("c2 인스턴스 생성 후 Circle 인스턴스 갯수 : "
				+ c2.numberOfInstances);
		
		TestCircleWithStaticFields test = new TestCircleWithStaticFields();
		// a는 인스턴스 변수이기 때문에, 객체 생성한 후 a의 값을 직접 참조하여 20으로 변경하였음
		test.a=20;
		// b는 static 변수이기 때문에 객체 생성 없이 사용 가능함
		b = 30;
		staticPrint();
		System.out.println("a : " + test.a);
		System.out.println("b : " + b);
		
	}
	
	public void printMain() {
		System.out.println("TestCircleWithStaticFields class instance method 호출");
	}
	
	public static void staticPrint() {
		System.out.println("TestCircleWithStaticFields class static method 호출");
	}

}
