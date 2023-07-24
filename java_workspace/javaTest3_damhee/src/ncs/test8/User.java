package ncs.test8;

import java.util.Objects;

public class User implements Cloneable {
	private String id;
	private String password;
	private String name;
	private int age;
	private char gender;
	private String phone;
	
	public User() {}
	
	public User(String id, String password, String name, int age, char gender, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
	}

	/**
	 * 복사 생성자
	 * (clone 오버라이딩 할 때 코드를 좀 더 깔끔하게 할 수 있음)
	 */
	public User(User user) {
		super();
		this.id = user.id;
		this.password = user.password;
		this.name = user.name;
		this.age = user.age;
		this.gender = user.gender;
		this.phone = user.phone;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	/**
	 * User 필드값들이 일치하는지 확인
	 */
	public boolean equals(Object obj) {
		if(this == obj) // 이거 대체 왜 한거지??
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		User other = (User) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password) &&
				Objects.equals(name, other.name) && age == other.age && gender == other.gender &&
				Objects.equals(phone, other.phone);
	}
	

	/**
	 * 현재 객체의 필드값을 가지고 새로운 User객체 생성
	 * 
	 * 원래는 Override 부모메소드의 시그니쳐
	 * (접근제한자, 리턴타입, 메소드명, 매개변수 선언부, 예외선언부)와 똑같이 해야함
	 * 
	 * Override 예외 (호출부에서 더욱 쉽게 사용하기 위해)
	 * 1. 접근제한자 더 개방할 수 있다. 접근제한자 protected --> public
	 * 2. 부모메소드에 선언된 예외선언을 제거할 수 있다. throws CloneNotSupportedException --> 제거가능
	 * 3. 반환타입을 Object의 자식 타입으로 선언가능 (공변 변환타입)
	 */
	@Override
	public User clone() {
//		return new User(this.id, this.password, this.name, this.age, this.gender, this.phone);
		return new User(this); // 복사생성자
	}
	
	/*
	 *  protected Object clone() throws CloneNotSupportedException {
		return new User(this.id, this.password, this.name, this.age, this.gender, this.phone);
		}
	 */

	@Override
	public String toString() {
		return  id + "  " + password + "  " + name + "  " + age + "  " + gender
				+ "  " + phone;
	}

	// getter / setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
