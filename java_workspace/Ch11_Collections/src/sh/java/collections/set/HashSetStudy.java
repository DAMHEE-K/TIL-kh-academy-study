package sh.java.collections.set;

import java.util.*;

import sh.java.collections.student.Student;

public class HashSetStudy {

	public static void main(String[] args) {
		HashSetStudy study = new HashSetStudy();
//		study.test1();
//		study.test2();
//		study.test3();
//		study.test4();
//		study.test5();
//		study.test6();
		study.test7();
		study.test8();

	}
	
	/**
	 * @실습문제 : Lotto 생성
	 * - 중복없는 1~45 사이의 숫자 6개를 생성하고, 오름차순 정렬해서 출력
	 */
	private void test8() {
		Set<Integer> set = new TreeSet<>();
		// 정렬 기능을 제공하는 TreeSet 사용
		
		// 일반 for문을 사용했을 때, 중복이 발생하면 난수 5개만 저장하므로
		// while문을 통해서 난수 6개가 저장될 수 있도록 코드 구현함
		while(set.size() < 6) {
			set.add((int)(Math.random()*45)+1);
		}
		
		System.out.println(set);
	}
	
	
	/**
	 * @실습문제 : 다음 문자열을 List를 생성한 후, 대문자로 변환, 중복을 제거하세요
	 * - abc, ABC, xyz, Java, code, happy, dinner, good, java, XYZ
	 */
	private void test7() {
		List<String> list = new ArrayList<>();
		list.add("abc");
		list.add("ABC");
		list.add("xyz");
		list.add("Java");
		list.add("code");
		list.add("happy");
		list.add("dinner");
		list.add("good");
		list.add("java");
		list.add("XYZ");
		
		Set<String> set = new HashSet<>(list);
//		for(int i=0; i<list.size(); i++) {
//			String str = list.get(i);
//			set.add(str.toUpperCase());
//		}
//		
		list.replaceAll(String::toUpperCase);
		// String#toUpperCase : 대문자로 변환
		// replaceAll : list 모든 요소를 대체한다
		
		// list를 HashSet으로 변환
		System.out.println(list);
		System.out.println(set);
		
	}

	/**
	 * LinkedHashSet 저장된 순서 유지
	 * TreeSet 기본정렬 지원
	 */
	private void test6() {
//		Set<String> set = new LinkedHashSet<>();
		Set<String> set = new TreeSet<>();
		// 문자열은 가나다 순서대로 저장함
		
		set.add("홍길동");
		set.add("신사임당");
		set.add("유관순");
		
		System.out.println(set);
	}
	
	
	/**
	 * 커스텀 클래스의 중복관리
	 * - 객체의 중복 저장을 막기 위해서는 equals/hashCode를 모두 오버라이드 해야 함
	 * - 자바 객체 관리 룰 : equals의 결과가 true라면, hashCode도 동일해야 함
	 */
	private void test5() {
		
		// no, name 필드가 값이 같다면 equals 결과는 true
		System.out.println(new Student(2, "신사임당").equals(new Student(2, "신사임당")));
		
		// no, name 필드 기준으로 hashCode 재생성(no, name 필드가 같다면 동일한 hashCode를 갖는다)
		System.out.println(new Student(2, "신사임당").hashCode());
		System.out.println(new Student(2, "신사임당").hashCode());
		
		Set<Student> studentSet = new HashSet<>();
		studentSet.add(new Student(1, "홍길동"));
		studentSet.add(new Student(2, "신사임당"));
		studentSet.add(new Student(3, "이순신"));
		studentSet.add(new Student(2, "신사임당"));
		// 각기 다른 주소를 갖고 있는 다른 객체이기 때문에,
		// Set이 모두 저장함 = > equals/hashCode를 모두 오버라이드하여 중복 저장 방지
		
		System.out.println(studentSet);
		
		
	}

	/**
	 * List <---> Set
	 * - List의 중복된 요소 제거하기 위해 Set으로 변환
	 * - Set에 순서나 정렬기능 필요한 경우 List로 변환
	 */
	private void test4() {
		List<String> nameList = new ArrayList<>();
		nameList.add("홍길동");
		nameList.add("신사임당");
		nameList.add("홍길동");
		nameList.add("이순신");
		System.out.println(nameList);
		
		Set<String> nameSet = new HashSet<>(nameList);
		// HashSet 생성자 중에서 Collection을 받는 생성자 이용함
		System.out.println(nameSet);
		
		List<String> nameList2 = new ArrayList<>(nameSet);
		nameList2.sort(null); // 기본정렬
		System.out.println(nameList2);
		
	}
	
	
	
	/**
	 * 요소 순회
	 * - forEach
	 * - Iterator
	 */
	private void test3() {
		Set<Double> set = new HashSet<>();
		set.add(1.2);
		set.add(3.5);
		set.add(7.3);
		set.add(9.9);
		
		// forEach문
		for(Double d : set) {
			System.out.println(d);
		}
		
		// iterator객체
		Iterator<Double> iter = set.iterator();
		while(iter.hasNext()) {
			Double elem = iter.next();
			System.out.println(elem);
		}
		
		
	}

	/**
	 * Set API
	 */
	private void test2() {
		Set<String> set = new HashSet<>();
		
		// 요소추가
		set.add("홍길동");
		set.add("신사임당");
		set.add("신사임당");
		set.add("신사임당"); 
		set.add("이순신");
		
		// 요소제거
		set.remove("이순신"); // 제거할 요소를 전달
		
		// 요소포함여부
		System.out.println(set.contains("이순신")); // false
		System.out.println(set.contains("신사임당")); // true
		
		// 전체 제거
		set.clear();
		
		// 비어있는지 여부
		System.out.println(set.isEmpty());
		
		System.out.println(set);
	}
	

	/**
	 * Set 중복을 허용하지 않는 컬렉션
	 * 
	 * 구현 클래스
	 * - HashSet
	 * - LinkedHashSet : 저장한 순서 유지
	 * - TreeSet : 기본정렬 지원 (Comparable 인터페이스 구현 필수)
	 */
	private void test1() {
		HashSet<String> set1 = new HashSet();
		Set<Integer> set2 = new HashSet();
		Collection<Double> set3 = new HashSet();
		
		set2.add(3);
		set2.add(5);
		set2.add(7);
		set2.add(1);
		set2.add(3); // 중복된 요소는 저장할 수 없음
		set2.add(5); // 저장 순서도 지키지 않음

		// set2.add(2, 100); // set은 index 기반으로 관리하지 않아, 이런 식으로 저장X
		// set2.get(2); 
		
		System.out.println(set2);
	}

}
