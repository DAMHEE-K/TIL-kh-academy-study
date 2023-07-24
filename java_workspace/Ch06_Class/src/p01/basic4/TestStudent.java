package p01.basic4;

// 초기값 설정없이 new Student()로 힙 메모리에 인스턴스 생성하면 모든 field들에 대하여
// Java가 자동으로 초기값을 설정해줌
// - int, short, byte, char, long : 0으로 초기화
// - boolean : false
// - double, float : 0.0으로 초기화
// - String, 배열 등 참조 객체 : null
public class TestStudent {
	public static void main(String[] args) {
		Student s = new Student();
		
		System.out.println("name : " + s.name);
		System.out.println("age : " + s.age);
		System.out.println("컴퓨터 전공 여부 : " + s.isComputerMajor);
		System.out.println("성별 : " + s.gender);
		
	}

}
