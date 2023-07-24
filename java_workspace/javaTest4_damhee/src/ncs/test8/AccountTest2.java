package ncs.test8;

public class AccountTest2 {
	public static void main(String[] args) {
		Account[] accountArray = new Account[5];
		
		for(int i=0; i<accountArray.length; i++) {
			
			accountArray[i] = new Account();
			accountArray[i].setAccount("221-0101-211"+(i+1));
			accountArray[i].setBalance(100000);
			accountArray[i].setInterestRate(4.5);
			
			accountArray[i].accountInfo();
		}
		System.out.println();
		for(int i=0; i<accountArray.length; i++) {
			accountArray[i].setInterestRate(3.7);
			accountArray[i].accountInfo();
			System.out.printf("이자 : %.0f",accountArray[i].calculateInterest());
		}
	}
}
