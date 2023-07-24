package ncs.test4;

import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;

public class TestSort {
	public static void main(String[] args) {
		int[] arr = new int[10];
		
		// 랜덤값 생성하여 저장하는 for문
		System.out.print("before: ");
		for(int i=0; i<arr.length; i++) {
			arr[i] = (int)(Math.random()*50+51);
			
			System.out.print(arr[i] + " ");
		}
		
		// 선택정렬
		for(int i=0; i<arr.length; i++) {
			for(int j=i+1; j<arr.length; j++) {
				if(arr[i] < arr[j]) { // i가 j보다 작으면
					int tmp = arr[i]; //
					arr[i] = arr[j];
					arr[j] = tmp; // 둘의 자리를 바꿔준다
				}
			}
		}
		System.out.println();
		System.out.print("after: ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
