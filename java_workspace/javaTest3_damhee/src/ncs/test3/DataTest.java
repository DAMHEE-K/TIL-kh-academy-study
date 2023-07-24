package ncs.test3;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DataTest {

	public static void main(String[] args) {
		/*
		LocalDate 클래스를 사용하여, 현재 년도와 비교한 나이를 계산하고 생일의 요일을 출력한다. 출
		력시 DateTimeFormatter를 사용하여 출력한다. 나이계산시에는 Period를 사용한다.
		*/
		
		LocalDate today = LocalDate.now(); 
		
		LocalDate birthday = LocalDate.of(1987, 5, 27);
		
		//dateTimeFormatter를 이용해서 날짜 출력 방식 지정함
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일");
		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		System.out.println(birthday.format(dateTimeFormatter));
		System.out.println(today.format(dateTimeFormatter2));
		
		Period period = Period.between(birthday, today);
		// Period period = birthday.util(today);
		int age = period.getYears(); // 만나이기준
		
		System.out.println("나이 : " + age);
	}

}
