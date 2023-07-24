package p1.basic;

public class TestInheritance {

	public static void main(String[] args) {
		Person p = new Person();
		p.name = "아담";
		
		p.sleep();
		p.speak();
		p.eat();
		p.walk();
		
		Student s = new Student();
		s.name = "홍길동 학생";
		s.age = 30;
		s.study();
		s.speak();
		s.walk();
		s.eat();
		s.sleep();
		
		StudentWorker sw = new StudentWorker();
		sw.name = "김학생 워커";
		sw.work();
		sw.walk();
		sw.study();
		sw.speak();
		sw.eat();
		sw.sleep();
		sw.study();
		
		Researcher r = new Researcher();
		r.name="강연구원";
		r.research();
		r.speak();
		r.eat();
		r.sleep();
		r.walk();
		
//		r.work(); // research와 student는 부모 자식 관계가 아니기 때문에 
				  // student 클래스에 있는 work() 메소드는 사용 불가능

	}

}
