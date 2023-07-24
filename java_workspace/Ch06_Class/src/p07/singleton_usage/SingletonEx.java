package p07.singleton_usage;

import java.util.Calendar;

public class SingletonEx {

	public static void main(String[] args) {
		
		// The constructor Singleton() is not visible
		// 이유 : Singleton constructor를 private으로 선언하여, class 외부에서
		// new 연산자로 인스턴스 생성 불가능
		// Singleton s1 = new Singleton();
		
		// Singleton class 내부에서 만든 instance()를 통해 사용
		Singleton s1 = Singleton.getInstance();
		s1.printHello();
		
		Singleton s2 = Singleton.getInstance();
		s2.printHello();
		
		System.out.println("s1 힙메모리 주소 : " + System.identityHashCode(s1));
		System.out.println("s2 힙메모리 주소 : " + System.identityHashCode(s2));
		
		
		
		// Calendar 클래스
		// 추상 클래스이므로 getInstance()를 통해 구현된 객체를 얻어야 함
		Calendar cal = Calendar.getInstance();
		// 현재 날짜와 시간으로 자동 세팅됨
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		
		System.out.println("Year : " + year + ", Month : " + month + ", Date : "
				+ date);
		
		
	}

}
