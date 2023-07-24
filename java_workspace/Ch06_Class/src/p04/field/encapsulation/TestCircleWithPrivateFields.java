package p04.field.encapsulation;

// Circle class에서
// 1. radius를 private으로 선언했기 때문에 다른 class에서 직접 사용 불가

public class TestCircleWithPrivateFields {

	public static void main(String[] args) {
		
		Circle c1 = new Circle(5);
		
		// c1.printPrivate();
		// The method printPrivate() from the type Circle is not visible
		// private 메소드이기 때문에, 다른 클래스에서 사용 불가능
		
	}

}