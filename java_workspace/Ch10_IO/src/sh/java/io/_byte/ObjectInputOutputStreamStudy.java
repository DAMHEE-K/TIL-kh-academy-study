package sh.java.io._byte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

// 변수명 한번에 수정 단축키 : alt + shift + R

/**
 * 객체입출력스트림 - 입출력을 객체 단위로 수행하는 스트림 클래스 ObjectInputStream#readObject
 * ObjectOutputStream#writeObject - 입출력에 사용할 클래스는 java.io.Serializable 인터페이스를
 * 구현해야 한다.
 */
public class ObjectInputOutputStreamStudy {

	public static void main(String[] args) {
		ObjectInputOutputStreamStudy study = new ObjectInputOutputStreamStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();

	}

	/**
	 * @실습문제 : 사용자 아아디를 입력받고 해당 사용자의 정보를 출력 - 해당 정보가 존재하지 않으면, 회원 가입을 먼저 하셔야 합니다 출력
	 */
	private void test4() {

		Scanner sc = new Scanner(System.in);
		System.out.print("조회할 username을 입력하세요 : ");
		String username = sc.nextLine();

		// users 폴더 안에 username.dat 파일을 가르키는 자바 객체 f를 선언
		File f = new File("users/" + username + ".dat");

		// 객체가 가르키는 파일이 존재하면
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))) {
			User user = (User) ois.readObject(); // object -> User로 다운캐스팅

			System.out.println("----------------------------");
			System.out.println("         <<조회결과>>        ");
			System.out.println("----------------------------");
			System.out.println(user);
			System.out.println("아이디 : " + user.getUsername());
			System.out.println("비밀번호 : " + user.getPassword());
			System.out.println("결혼여부 : " + user.isMarried());

		} catch (FileNotFoundException e) {
			System.out.println("회원 가입을 먼저 하셔야 합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @실습문제 : 회원정보를 입력해서 각 회원파일에 저장하세요 - 아이디 패스워드 결혼 여부 -> User 객체 -> 아이디.dat 파일에
	 *       저장(객체 단위로) - users/sinsa.dat
	 */
	private void test3() {
		Scanner sc = new Scanner(System.in);

		System.out.println("**** 회원 정보 입력 ****");
		System.out.print("이름 : ");
		String username = sc.nextLine();

		System.out.print("비밀번호 : ");
		String password = sc.nextLine();

		// 사용자 편의성을 위해서 y/n으로 입력받음
		System.out.print("결혼여부(y/n) : "); // y 또는 n으로만 입력 받음
		boolean married = sc.next().charAt(0) == 'y';
						// 입력받은.0번째 문자를 가져온게 == 'y'면   =>   y를 입력했으면, true
		// 입력받은 값을들 토대로 User 객체 생성
		User user = new User(username, password, married);

		// users 폴더를 가르키는 File 객체를 생성함
		File usersDir = new File("users");
		if (!usersDir.exists()) // 객체가 가르키는 파일이 존재하지 않으면
			usersDir.mkdir(); // mkdir()을 통해서 새로운 디렉토리(폴더) 생성

		File dest = new File(usersDir, username + ".dat"); // == "users/"+username+".dat"

		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dest)))) {
			oos.writeObject(user); // oos가 가르키는 객체("users/"+username+".dat")에 user의 정보를 출력(output) 함
			System.out.println(username + "님의 정보를 입력하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ObjectOutputStream : 객체를 직렬화하는 메소드를 제공하는 스트림
		// - 직렬화 : 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로
		// 데이터를 변환하는 기술
		// BufferedOutputStream : 보조스트림, 버퍼를 이용해 속도를 증가시킴
		// FileOutputStream : 주어진 File 객체가 가리키는 파일을 쓰기 위한 객체 생성

	}

	/**
	 * 객체 단위로 읽기
	 */
	private void test2() {

		File f = new File("user.ser");

		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)))) {
			User user = (User) ois.readObject();
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 객체 단위로 쓰기 보조스트림2 변수 = new 보조스트림2(new 보조스트림1(new 주스트림(대상)));
	 */
	private void test1() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("user.ser")))) {
			User honggd = new User("honggd", "1234", true);
			oos.writeObject(honggd);
			System.out.println("User객체를 쓰기 완료했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
