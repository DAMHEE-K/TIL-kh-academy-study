import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		new Test().output2();

	}
	
	public void output2() {
		try (FileWriter fw = new FileWriter("test.txt", true)) {
			fw.write(97);
			fw.write(65);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void output() {
		FileWriter fw = null;
		try {
			fw = new FileWriter("test.txt", true);
			fw.write(93);
			fw.write(65);
			System.out.println("이어쓰기 완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void test2() {
		try(
			BufferedReader br = new BufferedReader(new FileReader("helloworld.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("hiworld2.txt"));
				// ("hiworld2.txt", true) : 기존 파일 내용에 이어쓰기 가능
		) {
			String line = null;
			//  BufferedReader#readLine은 개행문자까지 읽어서 개행문자를 제외하고 반환
			while((line = br.readLine()) != null) {
				System.out.println(line);
				bw.write(line);
				// 개행문제를 제외하고 read했으니 다시 쓰면 개행없이 한 줄에 출력되서 나오기 때문에
				// 따로 개행문자를 작성해줘야 함
				bw.write("\r\n"); // 윈도우의 개행문자 \r\n, 리눅스/맥의 개행문자 \n
								  // 공통되는 \n을 쓰는 것이 좋다
			}
		} catch (Exception e) {
			
		}
	}
	
	
	public void test() {
		
		File dir = new File("foo");
		if (!dir.exists()) {
			dir.mkdir();
			System.out.println("foo 디렉토리를 생성했습니다.");
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("foo/bar.txt"))) {
			bw.write("helloworld");
			System.out.println("정상적으로 파일에 쓰기완료했습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}