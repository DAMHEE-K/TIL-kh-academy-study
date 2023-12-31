package sh.java.collections.student;

import java.util.Objects;

public class Student implements Comparable<Student> {
	private int no;
	private String name;
	
	// 기본 생성자
	public Student() {
		super();
	}
	// 매개변수 생성자
	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + "]";
	}
	
	/**
	 * Comparable 인터페이스를 구현하려면
	 * 기본 정렬 기준을 제시해야 함
	 * 
	 * - no 오름차순으로 정렬하는 메소드 compareTo 오버라이딩
	 * - 객체간 필드값 비교결과 정수로 반환
	 */
	@Override
	public int compareTo(Student other) {
		// 현재객체 - 다른객체 : 오름차순
		return this.no-other.no;
	}
	
	
	// alt + shift + s => hashCode, equals 오버라이딩 가능
	
	/**
	 * 필드값 기준으로 hashCode를 재생성
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name, no);
	}
	
	
	/**
	 * 필드값 기준으로 모든 필드가 동일하면 같은 객체라고 처리
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		// if(!(obj instanceof Student)) // obj가 Student의 클래스에 속하면
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && no == other.no;
	}

}
