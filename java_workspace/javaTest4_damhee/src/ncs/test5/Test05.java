package ncs.test5;

public class Test05 {
	/*
	eclipse에서 argument로 1~5까지의 정수형 데이터 하나를 입력 받아 입력 받은 수부터 10까지 합을 구
	한다.
	단, 3의 배수와 5의 배수는 합에서 제외 한다.
	*/
	public static void main(String[] args) {
		int num = Integer.valueOf(args[0]);
		
		if(!(1<=num&&num<=5)) {
			System.out.println("1부터 5사이의 수를 입력해주세요.");
		} else {
			int sum = 0;
			for(int i=num; i<=10; i++) {
				if(!(i%3==0 || i%5==0)) {
					sum += i;
					System.out.print(i+" ");
				}
			}
			
			System.out.printf("\n결과 : %d",sum);
		}
	}
}
