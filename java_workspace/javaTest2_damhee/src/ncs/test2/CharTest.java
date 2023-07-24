package ncs.test2;

import java.util.Arrays;

public class CharTest {

	public static void main(String[] args) {
		//arg가 String[]로 들어오니까 String으로 이어준 후
		//char[]로 만들어서 사용함.
		
//		String str = "";
//		for(int i=0; i<args.length; i++) {
//			str += args[i];
//		}
		char[] arr = args[0].toCharArray();
		String answer = "";
		for(int i=arr.length-1; i>=0; i--) {
			if('a'<= arr[i] && arr[i] <= 'z') {
				answer += (char)(arr[i]-32);
			} else {
				answer += arr[i];
			}
		}
		System.out.println(answer);
	}
}
