package edu.kh.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Run {
	
	public static void main(String[] args) {
		Run test = new Run();
		test.fileSave("사과");
		
	}
	
	public void fileSave(String fileName) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName + ".dat")))) {
			Food f = new Food(fileName, 20);
			oos.writeObject(f);
			System.out.println(fileName + "명의 Food객체를 쓰기 완료했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
