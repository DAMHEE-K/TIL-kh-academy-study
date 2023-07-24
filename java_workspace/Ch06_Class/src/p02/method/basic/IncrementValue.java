package p02.method.basic;

public class IncrementValue {
	
	public static void main(String[] args) {
		int n = 1;
		
		System.out.println("increment method 호출 전 n : " + n);
		increment(n);
		System.out.println("increment method 호출 후 n : " + n);
		
		System.out.println();
		
		System.out.println("newIncrement method 호출 전 n : " + n);
		n = newincrement(n);
		System.out.println("newIncrement method 호출 후 n : " + n);
		
	}
	
	// 프로그램 실행과 동시에 static 영역에 할당되므로, 객체 생성없이 사용 가능함
	public static void increment(int n) {
		n++;
		System.out.println("increment method내의 n : " + n);
	}
	
	public static int newincrement(int n) {
		n++;
		System.out.println("newIncrement method내의 n : " + n);
		return n;
	}
	
}
