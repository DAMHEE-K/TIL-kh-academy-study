package ncs.test4;

public class Calc {
	public Calc() {}
	
	public int calculate(int data) {
		int sum = 0;
		for(int i=1; i<=data; i++) {
			if (i%2 == 0) {
				sum += i;
				System.out.print(i + " ");
			}
		}
		return sum;
	}
}
