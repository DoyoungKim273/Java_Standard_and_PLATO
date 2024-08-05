package com.ruby.java.ch03;

public class Ex3_03 {
	public static void main(String[] args) {
		int i = 5, j = 5;
		
//		참조하여 대입 후에 증가
		System.out.println(i++); // i의 값을 출력 후 1 증가
//		증가 후에 참조하여 대입
		System.out.println(++j); // j의 값을 1 증가 후 출력
		System.out.println("i = " + i + ", j = " + j);
	}
}
