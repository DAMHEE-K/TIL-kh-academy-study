package ncs.test6;

public class Calculate {
	int a;
	int b;
	
	public Calculate() {} // 기본 생성자
	public Calculate(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	
	public int sum(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
}
