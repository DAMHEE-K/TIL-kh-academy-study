package p09.access_modifier.pkg1;

// access modifier (접근 제한자)
// 사용하는 곳 : field, constructor, method, class
// 종류 : private < (default) < protected < public

public class A {
	int a = 10; // (default) access modifier
	public int b = 20; // public access modifier
	private int c = 30; // private access modifier
	String d;
	
	// 기본 생성자 (public access modifier)
	public A() {
		
	}
	
	// 매개변수 생성자 (default access modifier)
	A(int a) {
		
	}
	
	// 매개변수 생성자 (private access modifier)
	private A(String d) {
		
	}
	
	public void printPublic() {
		System.out.println("public method");
	}
	
	void printDefault() {
		System.out.println("default method");
	}
	
	private void printPrivate() {
		System.out.println("private method");
	}
	
}
