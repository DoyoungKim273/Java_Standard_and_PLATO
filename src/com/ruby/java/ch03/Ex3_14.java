package com.ruby.java.ch03;

public class Ex3_14 {
	public static void main(String[] args) {
		
		String str1 = new String("abc"); // String 클래스의 객체를 생성
		String str2 = "abc"; // 위의 문장을 간단하게 표현 (특별히 String만 new 사용하지 않고 간단히 표현 허용됨)
		String str3 = "ABC";
		
		System.out.println(str1 == str2);
//		두 문자열의 내용이 같다 해도 서로 다른 객체이기에 false 반환됨
		System.out.println(str1.equals(str2));
//		equals()는 객체가 달라도 내용이 같으면 true 반환함
		System.out.println(str1.equalsIgnoreCase(str3));
//		대소문자 구별하지 않고 비교
	}
}
