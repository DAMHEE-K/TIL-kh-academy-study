package p01.basic;

// 1. Circle c = new Circle();의 의미
// - data type(Circle) + 변수 이름(c) = 데이터값(힙 메모리의 인스턴스 : new Circle())
// - 힙 메모리에 저장되는 class의 인스턴스 내에는 field들만 관리됨(constructor, method 제외)

// 2. Circle class의 인스턴스를 통해 Circle class의 field값과 method들을 사용

// 3. Circle c = new Circle() 명령어를 기계어로 변환되어 내부적으로 실행순서
//    1) new 실행 : heap memory에 Circle class 인스턴스 생성하고, 인스턴스 주소값 획득
//    2) Circle class의 생성자(constructor)를 호출하여 field값들 초기화
//    3) heap memory의 인스턴스 주소값을 변수 c에 넣어줌

public class TestCircle {

	public static void main(String[] args) {
		Circle c = new Circle();
		//   타입이 Circle인 참조변수 c를 선언하고, 새로운 Circle 객체를 생성하여 
		//   객체 주소를 변수 c에 저장
		// ** 매개변수가 없는 기본 생성자를 사용하여 객체 생성함
		
		System.out.println("c의 주소 : " + System.identityHashCode(c));
		System.out.println("c인스턴스의 반지름 : " + c.getRadius());
		System.out.println("c인스턴스의 면적 : " + c.getArea());
		System.out.println("c인스턴스의 원의 둘레 : " + c.getPerimeter());
		
		System.out.println("---------------------------------------");
		
		
		Circle c1 = new Circle(2);
		// 매개변수로 2가 들어왔으니, public Circle(double radius) 생성자를
		// 이용해서 객체를 생성함
		
		System.out.println("c1의 주소 : " + System.identityHashCode(c1));
		System.out.println("c1인스턴스의 반지름 : " + c1.getRadius());
		System.out.println("c1인스턴스의 면적 : " + c1.getArea());
		System.out.println("c1인스턴스의 원의 둘레 : " + c1.getPerimeter());
		
		
		System.out.println("---------------------------------------");
		
		Circle c2 = new Circle("페퍼로니");
		// 매개변수로 "페퍼로니"가 들어왔으니, public Circle(String name) 생성자를
		// 이용해서 객체를 생성함
		c2.setRadius(5);
		System.out.println("c2의 주소 : " + System.identityHashCode(c2));
		System.out.println("c2인스턴스의 반지름 : " + c2.getRadius());
		System.out.println("c2인스턴스의 면적 : " + c2.getArea());
		System.out.println("c2인스턴스의 원의 둘레 : " + c2.getPerimeter());
		
		System.out.println("---------------------------------------");
		
		Circle c3 = new Circle(3, "컴비네이션");
		// 매개변수로 3, "컴비네이션"이 들어왔으니, public Circle(double radius, String name) 생성자를
		// 이용해서 객체를 생성함
		System.out.println("c3의 주소 : " + System.identityHashCode(c3));
		System.out.println("c3인스턴스의 반지름 : " + c3.getRadius());
		System.out.println("c3인스턴스의 면적 : " + c3.getArea());
		System.out.println("c3인스턴스의 원의 둘레 : " + c3.getPerimeter());

	}

}
