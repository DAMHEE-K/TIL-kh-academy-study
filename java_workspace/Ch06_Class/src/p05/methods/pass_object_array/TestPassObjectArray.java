package p05.methods.pass_object_array;

public class TestPassObjectArray {
	
	public static void main(String[] args) {
		
		Circle myCircle = createCircleInstance(1);
		// 매개변수 1(반지름)을 가지고 createCircleInstace를 통해서 생성된 객체 주소를
		// myCircle에 저장함 (왜? createCircleInstance는 객체 주소값을 리턴하니까)
		
		System.out.println("myCircle 힙메모리 주소 : " + System.identityHashCode(myCircle));
		// myCircle 객체 주소값
		
		int loop_times = 5;
		printAreas(myCircle, loop_times);
		System.out.println("Radius \t\t Area");
		
		while (loop_times>=1) {
			System.out.println(myCircle.getRadius() + "\t\t" + myCircle.getArea());
			myCircle.setRadius(myCircle.getRadius()+1);
			loop_times--;
		}
		
	}
	
	public static Circle createCircleInstance(double radius) {
		// 만약 매개변수로 들어온 반지름(radius)가 0보다 작으면
		if(radius <0) {
			return null;
		} // null을 반환하고 그렇지 않으면 아래 코드 시행
		
		Circle c = new Circle(radius); // 새로운 Circle 객체 생성
		
		// 새롭게 생성된 c의 주소를 identityHashCode()를 통해서 얻어온 후, 
		// 아래 프린트 구문을 통해서 출력함
		System.out.println("c 힙메모리 주소 : " + System.identityHashCode(c));
		
		return c; // c(=힙 메모리의 주소값)를 호출부로 반환한다
	}
	
	public static void printAreas(Circle c, int loop_times) {
		System.out.println("printAreas 힙 메모리 주소 : " + System.identityHashCode(c));
		System.out.println("Radius\t\tArea");
		while(loop_times>=1) {
			System.out.println(c.getRadius() + "\t\t" + c.getArea());
			c.setRadius(c.getRadius()+1);
			loop_times--;
		}
		
	}

}
