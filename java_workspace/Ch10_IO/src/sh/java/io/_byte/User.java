package sh.java.io._byte;

import java.io.Serializable;

/**
 * Serializable 인터페이스는 몸통 코드가 없는 마커 인터페이스이다. (규격만 제공)
 *
 */
public class User implements Serializable {
	
	// 필드
	private String username;
	private String password;
	private boolean married;
	
	// 기본 생성자
	public User() {}
	
	// 매개변수 생성자
	public User(String username, String password, boolean married) {
		super();
		this.username = username;
		this.password = password;
		this.married = married;
	}

	// getter / setter 

	
	// boolean 형의 getter는 is로 시작한다
	public boolean isMarried() {
		return this.married;
	}
	
	public void setMarried(boolean married) {
		this.married = married;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", married=" + married + "]";
	}
	
}
