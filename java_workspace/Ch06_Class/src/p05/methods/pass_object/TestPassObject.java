package p05.methods.pass_object;

public class TestPassObject {
	
	public static void main(String[] args) {
		Circle[] CircleArray;
		CircleArray = createCircleArray();
		
		System.out.println("CircleArray 힙메모리 주소 : " + System.identityHashCode(CircleArray));
		
		printCircleArrayAreas(CircleArray);
		
	}
	
	public static Circle[] createCircleArray() {
		Circle[] c = new Circle[5];
		
		for(int i=0; i<c.length; i++) {
			c[i] = new Circle(Math.random()*100);
		}
		
		System.out.println("c 힙메모리 주소 : " + System.identityHashCode(c));
		
		return c; 
	}
											// 타입   변수명
	public static void printCircleArrayAreas(Circle[] c) {
		System.out.println("printArray 힙 메모리 주소 : " + System.identityHashCode(c));
		System.out.println("Radius\t\tArea");
		for(int i=0; i<c.length; i++) {
			System.out.println(c[i].getRadius() + "\t\t" + c[i].getArea());
		}
	}
	
	

}
