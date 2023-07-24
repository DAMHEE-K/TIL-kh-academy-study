package ncs.test3;

import java.util.*;

public class Test03 {
	public static void main(String[] args) {
		int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
		// 방법1 reverseOrder() 사용하기
		Integer[] integerArray = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		Arrays.sort(integerArray, Comparator.reverseOrder());
		for(int i : integerArray) {
			System.out.print(i + " ");
		}
		
		// 방법2 for() 사용하기
		for(int i=0; i<arr.length; i++) {
			int tmp = 0;
			for(int j=0; j<arr.length; j++) { // arr이 오름차순 정렬이 되어있다면 앞뒤로만 비교하면 되지만
											  // 랜덤일수도 있으니 배열의 전체 수를 비교하는 for문
				if (arr[j]<arr[i]) {
					tmp = arr[j];
					arr[j] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		for(int i : arr) {
			System.out.print(i + " ");
		}
		
	}
}
