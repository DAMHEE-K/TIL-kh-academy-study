package p07.singleton_usage;

public class Singleton {
	
	// 싱글톤 패턴 : 하나의 클래스에 오직 하나의 객체 인스턴스만 가지는 패턴
	// static을 사용하여 별도의 메모리 영역을 얻으면서 한번의 new 연산자로 
	// 인스턴스를 사용하기 때문에 메모리 낭비를 방지할수 있음
	
	// * 사용 용도
	// - new 연산자로 인스턴스 1개만 들어어도 되는 경우
	// * 사용 방법
	// - class 내부에서 private으로 선언된 field에 new로 인스턴스 생성
	// - class 사용자는 public으로 선언된 getInstance() method를 통해 Calendar 인스턴스 획득
	// - class 사용자는 singleton class의 나머지 method 사용
	
	private static Singleton singleton = new Singleton();
	//	인스턴스를 가리키는 주소값 singleton 변수는 데이터 영역에 위치
	//  								Singleton 인스턴스는 힙메모리에 생성
	
	// default constructor (private으로 선언됨)
	// 기본 생성자 접근제한자가 private이기 때문에 외부에서 객체 생성은 불가능!
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		System.out.println("singleton class 힙메모리 주소 : " + System.identityHashCode(singleton));
		return singleton;
	}
	
	public void printHello() {
		System.out.println("hello, singleton instance method");
	}

}
