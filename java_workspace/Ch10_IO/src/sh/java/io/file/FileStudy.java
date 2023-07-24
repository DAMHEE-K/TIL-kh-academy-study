package sh.java.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * java.io.File
 * - 입출력에 필요한 파일/디렉토리에 대한 정보를 가진 자바객체
 * - 파일에 대해 파일명, 경로, 존재여부, 크기등을 조회
 * - 디렉토리에 대해 존재여부, 파일열람 등을 제공
 * 
 */
public class FileStudy {
	public static void main(String[] args) {
		FileStudy study = new FileStudy();
//		study.test1();
//		study.test2();
//		study.test3();
		study.test4();
	}
	
	
	/**
	 * @실습문제 - 회원 이름을 입력받고, members 디렉토리 하위에 회원명/회원명.txt 파일을 생성하세요.
	 * members/강아지/강아지.txt
	 */
	private void test4() {
		
		// 이름을 입력받아 변수에 대입
		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요 : ");
		String name = sc.nextLine(); // 이름을 저장할 변수
		
		
		/* 내가 쓴 코드
					// members 하위에 name 폴더를 가리키고 있는 File 객체 생성
		File memberDir = new File("members/" + name);
					// members 하위 name 폴더 하위 name.txt를 가리키고 있는 File 객체 생성
		File memberFile = new File("members/"+name+"/"+name+".txt");
		
		// 만약에 File과 Dir이 존재하면,
		if(memberFile.exists() && memberDir.exists()) {
			System.out.println(name+"이 존재합니다.");
			
		} else { // File과 Dir이 존재하지 않으면 아래 코드 시행
			if(!memberFile.exists()) // 존재하지 않으면 새로운 파일 생성
				try {
					memberDir.mkdirs(); // Dir 새로 생성함 (Dir이 2개니까 mkdirs로 생성함)
					memberFile.createNewFile(); // File 생성함
					System.out.println("생성 완료");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		*/
		
		/* 강사님 코드 */
		File membersDir = new File("members");
		if(!membersDir.exists())
			membersDir.mkdir();
		
		File nameDir = new File("members/" + name);
		if(!nameDir.exists())
			nameDir.mkdir();
		
		// 회원 .txt 파일 생성
		// File f = new File("members/" + name + "/" + name + ".txt");
						// 부모 경로, 파일명
		File f = new File(nameDir, name+".txt");
		if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	/**
	 * File#mkdir() : 디렉토리 하나를 만들 때 사용
	 * File#mkdirs() 
	 */
	private void test3() {
		// mkdir
		File a = new File("a");
		
		if(a.exists()) {
			System.out.println("a가 존재합니다.");
		} else {
			System.out.println("a가 존재하지 않습니다.");
			a.mkdir();
			// a 디렉토리 생성함
		}
		
		// mkdirs
		File y = new File("x/y");
						// x 폴더의 하위 폴더인 y를 가리킴
		if(y.exists()) {
			System.out.println("x/y가 존재합니다.");
		} else {
			System.out.println("x/y가 존재하지 않습니다.");
			y.mkdirs(); // y의 부모 x가 존재하지 않으면 x를 만들고 y를 생성
		}
	}

	/**
	 * 디렉토리 제어
	 */
	private void test2() {
		File dir = new File("F:\\Workspaces\\java_workspace\\Ch10_IO");
		System.out.println(dir.isDirectory());
		System.out.println(dir.exists());
		
		// 디렉토리는 안에 파일이나 또 다른 디렉토리를 포함할 수 있음
		// listFiles() : 디렉토리의 파일목록(디렉토리 포함)을 File배열로 반환한다.
		File[] files = dir.listFiles();
		for(int i=0; i<files.length; i++) {
			File f = files[i];
			if(f.isDirectory())
				System.out.println("[디렉토리] " + f.getName());
			else
				System.out.println(f.getName());
		}
	}
	
	/**
	 * 파일 제어
	 */
	private void test1() {
//		f는 "helloworld" 파일을 가리키고 있는 자바 객체임
		
//		* <상대경로>
//		* 현재 디렉토리 또는 기준 디렉토리에서 파일을 찾음
//		File f = new File("helloworld.txt");
		
//		* <절대경로>
//		* 루트 디렉토리부터 시작
		File f = new File("F:\\Workspaces\\java_workspace\\Ch10_IO\\helloworld.txt");
		
		System.out.println(f.getName()); // 파일 이름
		System.out.println(f.getParent()); // 부모 경로
		System.out.println(f.getAbsolutePath()); // 절대경로
		System.out.println(f.exists()); // 존재하면 true, 존재하지 않으면 false
		System.out.println(f.isFile()); // 파일이면 true, 파일 아니면 false
		System.out.println(f.isDirectory()); // 폴더면 true, 폴더 아니면 false
		
		if(!f.exists()) // 존재하지 않으면 새로운 파일 생성
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
//		File dest = new File("안녕세계.txt");
//		f.renameTo(dest);
	}
}
