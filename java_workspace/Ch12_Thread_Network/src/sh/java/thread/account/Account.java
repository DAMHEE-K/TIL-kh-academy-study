package sh.java.thread.account;

public class Account {
	private int balance;
	
	public Account(int balance) {
		this.balance = balance;
	}
	
	/**
	 * - 임계영역 : 한 번에 하나의 쓰레드만 사용할 수 있는 공유자원
	 * - synchronized 메소드는 현재 객체를 임계영역으로 지정
	 * - synchronized 블럭을 지정한 객체를 임계영역으로 지정
	 */
	public void withdraw(int money) {
		synchronized (this) {
			String threadName = Thread.currentThread().getName();
			System.out.printf("[%s] 현재 잔액 : %d원, 출금시도 : %d원\n", threadName, this.balance, money);
			if(money <= balance) {
				balance -= money;
				System.out.printf("[%s] 출금성공! 현재 잔액 : %d원\n", threadName, this.balance);
			} else {
				System.out.printf("[%s] 잔액부족! 현재 잔액 : %d원\n", threadName, this.balance);
			}
		}
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
}
