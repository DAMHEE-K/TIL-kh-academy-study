package ncs.test3;

public class TvTest {
	public static void main(String[] args) {
		
		// Tv 객체를 3개 생성하여 배열에 넣는다.
		// 배열에 있는 객체 정보를 모두 출력 한다. – for 문을 이용 할 것
		// 실행 결과와 같이 출력

		Tv tvArray[] = new Tv[3];
		
		tvArray[0] = new Tv("INFNIA", 1500000, "LED TV");
		tvArray[1] = new Tv("XCANVAS", 1000000, "LCD TV");
		tvArray[2] = new Tv("CINEMA", 2000000, "3D TV");
		
		Tv max = tvArray[0];
		Tv min = tvArray[0];
		
		for(int i=0; i<tvArray.length; i++) {
			System.out.println(tvArray[i].toString());
			
			// 가격 비교를 위한 if문
			if(tvArray[i].getPrice() < min.getPrice()) {
				min = tvArray[i];
			} else if(tvArray[i].getPrice() > max.getPrice()) {
				max = tvArray[i];
			}
		}
		
		
		System.out.println("가장 가격이 비싼 제품 : " + max.getName());
		System.out.println("가장 가격이 싼 제품 : " + min.getName());
		
	}
}
