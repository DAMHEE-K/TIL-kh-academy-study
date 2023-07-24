package ncs.test5;

public class BookArrayTest {

	public static void main(String[] args) {
		/*
		3개의 Book 객체를 배열로 생성하여 각각의 정보와 할인된 가격을 출력한다.
		*/
		
		Book bArray[] = {
				new Book("자바의 정석", "남궁성", 30000, "도우출판", 0.1),
				new Book("열형강의 자바", "구정은", 29000, "프리렉", 0.1),
				new Book("객체지향 JAVA8", "금영욱", 30000, "북스홈", 0.1)
		}; // Book 객체 3개를 생성하여 배열에 넣는다
		
		for(int i=0; i<bArray.length; i++) {
			System.out.printf("%s, %s, %s, %d원, %.0f%% 할인\n",bArray[i].getTitle(),bArray[i].getAuthor(),bArray[i].getPublisher(),
					bArray[i].getPrice(),(bArray[i].getDiscountRate())*100);
			
			double discountPrice = bArray[i].getPrice()-(bArray[i].getPrice())*(bArray[i].getDiscountRate());
			
			System.out.printf("할인된 가격 : %.0f원\n", discountPrice);
		}
		
	}

}
