package sh.java.collections.list;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sh.java.collections.student.Student;

public class ArrayListStudy {

	public static void main(String[] args) {
		ArrayListStudy study = new ArrayListStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
		study.test6();
		study.test7();
		
	}
	
	

	/**
	 * LinkedList
	 * - 내부적으로 배열을 관리하지 않고, 요소와 요소를 체인형식으로 연결해서 관리
	 * - 중간에 요소를 삽입/삭제하는 일이 빈번하다면 ArrayList보다 LinkedList를
	 *   사용하는 것이 더 효율적!
	 */
	private void test7() {
		List<Integer> list = new LinkedList<>();
		list.add(100);
		list.add(200);
		list.add(300);
		list.add(1,150);
		list.add(1,125);
		
		System.out.println(list);
		
	}




	/**
	 * List 요소의 정렬
	 * - 저장된 순서가 바뀌므로 주의할 것!
	 * 
	 * 기본정렬 (클래스당 1가지) - Comparable 인터페이스 구현 (기본 정렬기준을 구현하는데 사용)
	 * 						- 자기 자신과 매개변수 객체를 비교하는 것
	 * 
	 * 그 외 정렬 (클래스당 n가지) - Comparator 인터페이스 구현 클래스 작성 (기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용)
	 *						  - 두 매개변수 객체를 비교하는 것
	 */
	private void test6() {
		List<Integer> list = new ArrayList<>();
		list.add(80);
		list.add(75);
		list.add(53);
		list.add(95);
		
		// 정수 정렬 (오름차순 : 기본값)
//		list.sort(null); // 비교 기준 객체 전달 안함 -> 기본정렬
		list.sort(Collections.reverseOrder()); // 기본정렬 반대순서 -> 내림차순 정렬
		
//		Collections.sort(list); // 기본정렬
		Collections.sort(list, Collections.reverseOrder());
		
		System.out.println(list);
		
		List<Student> students = new ArrayList<>();
		students.add(new Student(30, "홍길동"));
		students.add(new Student(17, "신사임당"));
		students.add(new Student(55, "김삿갓"));
		
		System.out.println(students); //  그냥 출력했을 경우 저장한 순서대로 출력함
		
		// students.sort(null); // 기본정렬 
		// 그냥 사용시에 java.lang.ClassCastException 발생
		// students 객체의 비교기준이 정해져있지 않기 때문에 기본 정렬을 사용하려면
		// comparable 구현하여 사용해야 함
		
//		students.sort(new StudentNameAsc()); // 이름기준 오름차순 기타정렬
		students.sort(Collections.reverseOrder(new StudentNameAsc())); // 이름기준 내림차순 기타정렬
		
		/*		1회성 compare 만드는 방법
		students.sort(new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		*/
		
		System.out.println(students);
		
	}
	

	/**
	 * List 열람
	 * - 일반 for문 : 인덱스까지 필요할 경우
	 * - forEach 문
	 * - Iterator 객체를 통한 반복처리
	 */
	private void test5() {
		List<Double> list = new ArrayList<>();
									// <Double> 생략
		list.add(1.1);
		list.add(2.2);
		list.add(3.3);
		
		// forEach 문
		for(Double d : list) {
			System.out.println(d);
		}
		
		// Iterator 객체 : 다음 요소를 가리키는 포인터를 가지고 있음. next 호출 시마다 다음 요소로 이동
		Iterator<Double> iter = list.iterator();
		while(iter.hasNext()) { // 다음 접근할 요소가 존재하면 true, 더 이상 요소가 존재하지 않으면 false
			Double d = iter.next();
			System.out.println(d);
		}
		
	}


	/**
	 * List API
	 */
	private void test4() {
		List<Integer> list = new ArrayList<>();
		
		// 요소추가
		list.add(10);
		list.add(20);
		
		// 요소삽입
		list.add(1, 15); // index, 값
		list.add(2, 16);
		list.add(3, 17);
		list.add(4, 18);
		list.add(5, 19);
		
		// 요소삭제
		list.remove(1); // 1번지 요소 삭제와 동시에 뒤 번지수의 요소를 하나씩 앞당기는 작업 포함
		
		// 요소검색
		System.out.println(list.indexOf(17)); // 해당 요소의 번지 수(인덱스)를 반환
		System.out.println(list.indexOf(1700)); // 해당 요소가 존재하지 않으면 -1 반환
		
//		System.out.println(list.size()); // 요소의 개수
		
		System.out.println(list.contains(17)); // 해당 요소 포함 여부를 boolean으로 반환
		System.out.println(list.contains(1700)); // 요소가 포함되어 있지 않다면 false
		
		// 요소 대체
		list.set(2, 1700); // 인덱스, 새로운 값
		
		// 요소 전체 제거
//		list.clear();

		// 요소가 0개인지 검사
		System.out.println("isEmpty ? : " + list.isEmpty()); // list가 비어있으면 true 반환
		
		// 반복처리
		for(int i=0; i<list.size(); i++) {
							// size() : 배열의 요소의 길이 (배열의 길이X)
			Integer num = list.get(i); // get() 이용하여 값을 하나씩 가져옴
			System.out.println("index " + i + " : " + num);
		}
	}
	

	/**
	 * Generics 타입제한
	 * - 요소를 추가할 때 해당 타입만 추가할 수 있다
	 * - 요소를 꺼내면 Object가 아닌 해당타입의 값이 바로 나온다
	 * - 기본형은 요소로 사용할 수 없으므로, 기본형에 상응하는 Wrapper Class를 사용해야 함
	 * - int - Integer / double - Double / char - Character / boolean - Boolean ...
	 */
	private void test3() {
//		List<String> list = new ArrayList<String>();
		
		List<String> list = new ArrayList<>();
		// String 요소만 들어올 수 있는 list 생성
		// <> 안에 타입은 좌/우항이 항상 같아야 하며, 우항은 생략 가능
		
		list.add("hello world");
//		list.add(123);
//		list.add(291.3); // The method add(String) in the type List<String> is not applicable for the arguments (double)
		list.add("안녕~~");
		list.add("반가버");
		
		String str = list.get(0);
		// list에 String 타입만 들어올 수 있도록 제한을 걸어놨기 때문에
		// String 타입의 변수 str에 바로 저장할 수 있음
		
		System.out.println(list);
		
	}

	/**
	 * Collection 인터페이스
	 *   - List 인터페이스
	 *     - ArrayList 구현클래스
	 *     
	 * - 크기 제한이 없음
	 * - 중간에 요소 추가/삭제가 쉬움
	 * - 저장된 순서를 기억
	 */
	private void test2() {
		// 하위호환 지원해서 이렇게 써도 에러는 안나는데
		// 안쓰는게 좋다고 경고함
		ArrayList list1 = new ArrayList();
		List list2 = new ArrayList();
		Collection list3 = new ArrayList();
		
		list2.add(100);
		list2.add(200);
		list2.add(123.456);
		list2.add(true);
		list2.add("안녕");
		list2.add(new Date()); // java.util.Date
		list2.add(LocalDate.now()); // java.time.LocalDateTime
		list2.add(2,"ㅋㅋㅋㅋ"); // 2번지에 요소 삽입
		
		System.out.println(list2); // toString 오버라이딩 되어 있음
		Object obj = list2.get(2); // 반환 타입이 Object임
		System.out.println(obj);
	}


	/**
	 * 배열의 단점
	 * - 학번 작성된 배열의 크기는 변경할 수 없다.
	 * - 요소를 중간에 삽입/삭제가 불편
	 */
	private void test1() {
		Student[] students = new Student[3];
		students[0] = new Student(1, "홍길동");
		students[1] = new Student(2, "심사임당");
		students[2] = new Student(3, "이순신");
		
		// 2명의 학생을 추가
		Student[] students2 = new Student[50];
		System.arraycopy(students, 0, students2, 0, students.length);
		students2[3] = new Student(4, "유관순");
		students2[4] = new Student(5, "윤봉길");
		
		
		
		// 학생 1명을 중간에 삭제
		// 이순신을 삭제하고 싶다면,
		// students2[2] = null; 하면 안됨!!
		
		// 배열은 연속된 구조이기 때문에 중간 값이 비어있을 수 없어서 뒤에 있는 배열을
		// 앞으로 당겨와야 함
		students2[2] = students2[3];
		students2[3] = students2[4];
		students[4] = null;
		
		// 학생 1명을 중간에 삽입
		// 뒤에 있는 요소들을 한 칸씩 뒤로 이동해준 후에
		// 값을 삽입해야 함
		students2[4] = students[3];
		students2[3] = students[2];
		students2[2] = new Student(100,"도요토미");
		
	}
}
