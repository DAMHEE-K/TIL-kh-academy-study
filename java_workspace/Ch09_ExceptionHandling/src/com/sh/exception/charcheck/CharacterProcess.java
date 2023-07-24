package com.sh.exception.charcheck;

public class CharacterProcess {
	public CharacterProcess() {}
	
	/**
	 * 전달된 문자열에서 알파벳(소문자/대문자)의 개수를 세어서 반환
	 * 단, 공백문자가 있으면, CharCheckException 발생
	 */
	public int countAlpha(String str) {
		int count = 0;
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			
			// if(str.indexOf(" ") > -1)
				// 검색하려는 문자열이 없으면 -1 반환
				// -1이 되는 순간 " "이 나왔다는 뜻이니까 true가 되면서 예외가 발생함
			if(ch == ' ') {
				throw new CharCheckException("체크할 문자열 안에 공백을 포함할 수 없습니다.");
			}
			
			// 이 조건식을 메소드로 만드는 것 == refactoring
//			if('A'<=ch && ch<='Z' || 'a'<=ch && ch<='z') 
//			if(isUpperCase(ch) || isLowerCase (ch)) 
			
			if(isAlphabetic(ch))
				count++;
		}
		return count;
	}
	
	
	
	private boolean isAlphabetic(char ch) {
		return isUpperCase(ch)||isLowerCase(ch);
	}

	public boolean isUpperCase(char ch) {
		return ('A'<=ch && ch<='Z');
	}
	
	public boolean isLowerCase(char ch) {
		return ('a'<=ch && ch<='z');
	}
	
	
}
