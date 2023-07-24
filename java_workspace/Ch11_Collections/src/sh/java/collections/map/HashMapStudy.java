package sh.java.collections.map;

import java.util.*;

import sh.java.collections.student.Student;

/**
 * - HashMap
 * - LinkedHashMap : 저장된 순서를 유지
 * - TreeMap : key값 기준으로 정렬 지원
 */
public class HashMapStudy {
	public static void main(String[] args) {
		HashMapStudy study = new HashMapStudy();
//		study.test1();
//		study.test2();
		study.test3();
		
	}
	
	/**
	 * 커스텀클래스 객체를 Map으로 관리하기
	 * - key 커스텀객체의 고유한 필드값
	 * - value 커스텀객체
	 */
	private void test3() {
		Map<Integer, Student> studentMap = new HashMap<>();
		// Key를 선정하는 기준 : Student 객체의 필드 중 객체마다 고유한 값으로 선정
		
		studentMap.put(1, new Student(1, "홍길동"));
		studentMap.put(2, new Student(2, "신사임당"));
		studentMap.put(3, new Student(3, "이순신"));
		
		
		
		// studentMap 열람하기
		// 1) keySet
		// a.forEach
		Set<Integer> keySet = studentMap.keySet();
		for(Integer i : keySet) {
			Student s = studentMap.get(i);
			System.out.println("학번 : " + s.getNo() + ", 이름 : " + s.getName());
		}
		// b. iterator
		Iterator<Integer> iter = studentMap.keySet().iterator();
		while(iter.hasNext()) { // 다음요소가 있니? 해서 true면 계속 읽어옴
			Integer key = iter.next();
			// int ----> Integer (auto-boxing 기능 지원)
			// Integer ---> int (auto-unboxing 기능 지원)
			Student value = studentMap.get(key);
			System.out.println(key + " = " + value);
		}
		
		
		// 2) entrySet
		// entry : Student [no=1, name=홍길동] 형식으로 저장됨
		// a.forEach
		Set<Map.Entry<Integer, Student>> entrySet = studentMap.entrySet();
		for(Map.Entry<Integer, Student> entry : entrySet) {
			Integer key = entry.getKey();
			Student value = entry.getValue();
			System.out.println(key+ " : " + value);
		}
		// b. iterator
		Iterator<Map.Entry<Integer, Student>> entryIter = studentMap.entrySet().iterator();
		while(entryIter.hasNext()) {
			Map.Entry<Integer, Student> entry = entryIter.next();
			Integer key = entry.getKey();
			Student value = entry.getValue();
			System.out.println(key+ " : " + value);
		}
	}

	/**
	 * Map은 iterator 사용 불가능 (Collection의 자손이 아님)
	 * 
	 * 요소 열람 - Set으로 먼저 변환 후 열람이 가능
	 * 1. Map#KeySet() : Set<K> 키만 set으로 반환
	 * 2. Map#entrySet() : Set<Entry<K,V>> Entry(키, 벨류)를 set으로 반환
	 */
	private void test2() {
		Map<String, Integer> map = new HashMap<>();
		map.put("국어", 80);
		map.put("영어", 77);
		map.put("수학", 93);
		map.put("사회", 60);
		
		// 1. keySet()
		Set<String> keySet = map.keySet(); 
		// KeySet() : map에 있는 Key값만 가져와서 Set으로 반환함
		// <>는 key부분의 제네릭을 가져와서 적용함
		for(String key : keySet) { // set으로 변환한 keySet에 순차적으로 접근하기 위한 forEach문
			Integer value = map.get(key);
			// get(key):map에 있는 key와 짝을 이루는 value값을 얻어와서
			// Integer value 변수에 저장함
			System.out.println(key + " 과목의 점수는 " + value + "점 입니다.");
		}
		
		// 2.entrySet() : Set<Map.Entry<K,V>>
		// Map.Entry는 map의 요소의 부모인터페이스이다.
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		for(Map.Entry<String, Integer> entry : entrySet) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println(key + " : " + value + "점");
		}
	}
	
	

	/**
	 * Map - HashMap
	 * Collection 인터페이스를 구현하지 않음
	 * 
	 * - Map<K,V>
	 * - key와 value로 하나의 요소를 구성
	 * - key는 중복X / value 중복O
	 * - key를 통해 value를 조회
	 * - 동일한 key로 다시 저장하면 value가 덮어써진다.
	 */
	private void test1() {
		// Key - String, Value - Object를 타입제한
		Map<String, Object> map1 = new HashMap<>();
		HashMap<String, Object> map2 = new HashMap<>();
		
		// 요소추가
		map1.put("이름", "홍길동");
		map1.put("나이", 33);
		map1.put("addr", "서울시 강남구 역삼동");
		
		// 요소조회
		System.out.println(map1.get("나이")); // get(key) : key값을 통해 value를 얻어옴
		
		// 삭제
		map1.remove("이름"); // remove(key) 
		
		// 현재 저장된 요소의 개수
		System.out.println(map1.size());
		
		// 특정 Key를 가지고 있는가?
		System.out.println(map1.containsKey("나이"));
		
		// 특정 value를 가지고 있는가?
		System.out.println(map1.containsValue("홍길동"));
		
		// toString()
		System.out.println(map1);
	}

}
