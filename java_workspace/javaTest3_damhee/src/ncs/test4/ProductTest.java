package ncs.test4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductTest {
	/*
	Product 클래스를 작성하고, 키보드로 각 필드에 기록할 값을 입력받아 객체 초기화에 사용한다.
	가격과 수량을 계산하여 구매가격을 출력한다.
	*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("제품명 입력 : ");
		String name = sc.nextLine();
		
		int price;
		int quantity;
		
		while(true) {
			try {
			System.out.print("제품 가격 입력 : ");
			price = sc.nextInt();
			
			System.out.print("수량 입력 : ");
			quantity = sc.nextInt();
			break;
			
			} catch(InputMismatchException e) {
				System.out.println("정수를 입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
		
		Product product = new Product(name, price, quantity);
		
		System.out.println(product.information());
		
		int value = price * quantity;
		System.out.println("총 구매 가격 : " + value + "원");
	}
}
