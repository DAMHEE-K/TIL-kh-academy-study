package p1.basic;

// 조상 : Person
// 자손 : Student
public class Student extends Person {
	String ssn = "123456"; // 학번
	
	void study() {
		System.out.println(name + "는/은 공부를 합니다.");
	}
}
