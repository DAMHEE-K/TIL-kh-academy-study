package p02.method.basic;

public class Computer {
	
	public int sum1(int[] values) {
		int sum = 0;
		for(int i=0; i<values.length; i++) {
			sum += values[i];
		}
		return sum;
	}
	
	
	
	public int sum2(int... values) {
		// int...  의 의미 : 가변인자
		int sum = 0;
		for(int i=0; i<values.length; i++) {
			sum += values[i];
		}
		return sum;
	}

}
