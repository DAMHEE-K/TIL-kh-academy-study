package ncs.test7;

public class Account {
	private String account;
	private int balance;
	private double interestRate; // 이율
	
	public Account() {}
	public Account(String account, int balance, double interestRate) {
		super();
		this.account = account;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	
	// 현재 잔액을 기준으로 이자를 계산 한다
	public double calculateInterest() {
		return this.balance*(interestRate/100);
	}
	
	// 입금을 통해 잔액정보를 증가 시킨다
	public void deposit(int money) {
		this.balance += money;
	}
	
	// 출금을 통해 잔액정보를 감소 시킨다
	public void withdraw(int money) {
		this.balance -= money;
	}
	
	
	// getter / setter
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	};
	
	
	
}
