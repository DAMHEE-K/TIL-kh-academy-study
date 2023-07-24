package ncs.test7;

public class AccountTest {
	public static void main(String[] args) {
		Account account = new Account("441-0290-1203", 500000, 7.3);
		
		System.out.printf("계좌정보: %s 현재잔액: %d",account.getAccount(), account.getBalance());
		account.deposit(20000);
		System.out.printf("\n계좌정보: %s 현재잔액: %d",account.getAccount(), account.getBalance());
		System.out.printf("\n이자: %.1f",account.calculateInterest());
	}
}
