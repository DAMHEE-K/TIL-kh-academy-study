package edu.kh.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine()); // 파일 갯수
		String[] str = br.readLine().split(" "); // 파일 용량
		long size = Long.parseLong(br.readLine()); // 클러스터 용량
		long[] file = new long[num];
		
		int index = 0;
		for(String s : str) 
			file[index++] = Long.parseLong(s);
		
		long count = 0;
		for(int i=0; i<file.length; i++) {
			if(file[i] % size == 0) {
				count += file[i]/size;
			} else {
				count += (file[i]/size)+1;
			}
		}
		long sum = count * size;
		System.out.println(sum);
	}
}
