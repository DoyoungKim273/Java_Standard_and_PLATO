package com.ruby.java.ch02;

public class Ex2_12 {
public static void main(String[] args) {
	String str = "3";
	
//	문자열을 문자로 변환
	System.out.println(str.charAt(0) - '0');
//	문자를 숫자로 변환 - 문자에서 0을 뺌
	System.out.println('3'- '0' + 1);
//	문자열을 숫자로 변환
	System.out.println(Integer.parseInt("3") + 1);
//	문자열 더하기 숫자 -> 문자열과는 어떤 타입을 더해도 문자열 + 문자열로 처리됨
	System.out.println("3" + 1);
//	숫자를 문자로 변환
	System.out.println((char)(3 + '0'));
}
}
