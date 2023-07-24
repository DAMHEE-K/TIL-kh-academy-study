package sh.java.thread;

import java.nio.file.spi.FileSystemProvider;
import java.util.Scanner;

/**
 * program - 저장 장치에 보관된 상태의 프로그램
 * process - 실행중인 프로그램. pid를 부여받고 os에 의해 제어된다.
 * thread - process 하위의 실제 작업단위
 */
public class ThreadStudy {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		ThreadStudy study = new ThreadStudy();
//		study.test1();
		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
//		study.test6();
		
		System.out.println("----" + Thread.currentThread().getName() + "종료 ----");
		// Thread.currentThread().getName() : 현재 작업중인 스레드 이름을 확인하는 방법
	}
	

	/**
	 * 데몬 쓰레드 daemon thread
	 * - 백그라운드 쓰레드
	 * - 일반 쓰레드가 종료되면 따라서 자동 종료하는 쓰레드
	 */
	private void test6() {
		Thread thread = new Thread(new CountDown(10));
		thread.setDaemon(true); // 쓰레드를 백그라운드 쓰레드로 돌릴 수 있음
		thread.start();
		System.out.println("10초 안에 아무글자를 입력하고 엔터를 누르세요...");
		new Scanner(System.in).next();
	}


	/**
	 * @실습문제 : 카운트 쓰레드 만들기
	 * - 초수 지정가능해야함
	 * - new CountDown(10)
	 */
	private void test5() {
		new Thread(new CountDown(10), "카운트 다운 알바").start();
				
	}
	
	static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	static class CountDown implements Runnable {
		private int sec;
		
		public CountDown(int sec) {
			this.sec = sec;
		}
		
		@Override
		public void run() {
			for(int i=sec; i>=0; i--) {
				System.out.println(i);
				delay(1000); // 예외처리가 되었기 때문에 간결히 호출 가능!
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	
	
//	static class CountDown extends Thread {
//		private int num;
//		public CountDown(int num) {
//			this.num = num;
//		}
//		
//		@Override
//		public void run() {
//			for(int i=num; i>0; i--) {
//				System.out.println(i);
//			}
//		}
//	}


	/**
	 * 쓰레드 상태 제어 - sleep
	 */
	private void test4() {
		for(int i=0; i<10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);  // 현재 쓰레드는 1초 동안 일시정지
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * 쓰레드 생성하는 방법2 - Runnable 구현 ** 추천 **
	 * - 쓰레드의 순번을 제어할 수 있다
	 * - 우선순위 부여 정도는 가능
	 */
	private void test3() {
		Runnable run1 = new BarThread('*');
		Runnable run2 = new BarThread('$');
		Thread th1 = new Thread(run1);
		Thread th2 = new Thread(run2);
		th1.setName("일꾼1");
		th2.setName("일꾼2");
		
		// 메소드는 있으나 크게 의존하지 않는 것이 좋음
		th1.setPriority(Thread.MAX_PRIORITY); // 10
		th2.setPriority(Thread.MIN_PRIORITY); // 1
		
		th1.start();
		th2.start();
		
	}

	static class BarThread implements Runnable {
		private char ch;
		public BarThread(char ch) {
			this.ch = ch;
		}
		@Override
		public void run() {
			new ThreadStudy().print(ch);
			System.out.println("<" + Thread.currentThread().getName()+ " 종료>");
			// currentThread() - 현재 실행중인 스레드 반환
		}
	}



	/**
	 * 쓰레드 생성하는 방법 1 - Thread 상속
	 */
	private void test2() {
		Thread th1 = new FooThread('+');
		Thread th2 = new FooThread('|');
		th1.start(); // 새로운 call stack에 Thread#run을 호출
		th2.start(); // run() 호출X
	}

	static class FooThread extends Thread {
		private char ch;
		public FooThread(char ch) {
			this.ch = ch;
		}
		
		
		/**
		 * 쓰레드가 할 일 정의
		 */
		@Override
		public void run() {
			new ThreadStudy().print(ch);
		}
	}


	/**
	 * 메인쓰레드
	 */
	private void test1() {
		print('+'); // +를 100번 출력하고
		print('|'); // 그 후에 |를 100번 출력함 (순차적)
		
	}
	
	public void print(char ch) {
		for(int i=0; i<100; i++) {
			System.out.print(ch);
		}
	}

}
