package p1.basic;

// StudentWorker는 Person의 손자이고 Student의 자식
// 손자는 부모, 조부모 ... 모든 조상의 field, 메소드 사용 가능함
public class StudentWorker extends Student {
	int arbeit; // 아르바이트해서 버는 돈
	
	void work() {
		study();
		walk();
		System.out.println(name + "는/은 공부하면서 일합니다.");
	}
}
