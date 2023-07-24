package p09.access_modifier.pkg1;

public class B {
	A a1 = new A(); // A() 생성자는 public이기 때문에 자유롭게 생성 가능
	A a2 = new A(1); // A(int i) 생성자는 default이기 때문에 동일 패키지 내에서 생성 가능
//	A a3 = new A("문자열"); // A(String s) 생성자는 private이기 때문에 같은 클래스 내에서만 가능
	
	public B() {
		a1.a = 1; // default field
		a1.b = 2; // public field
//		a1.c = 3; // private field
		
		a1.printPublic(); // public method
		a1.printDefault(); // default method
//		a1.printPrivate(); // private method
		// The method printPrivate() from the type A is not visible
	}
}
