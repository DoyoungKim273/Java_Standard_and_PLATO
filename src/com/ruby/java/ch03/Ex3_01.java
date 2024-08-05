package com.ruby.java.ch03;

public class Ex3_01 {
	public static void main(String[] args) {
		int x, y;
		
//		y에 3이 저장된 이후에, x에 3이 저장됨
//		연산자의 결합규칙이 오른쪽에서 왼쪽인 경우
		x = y = 3;
		
		System.out.println("x = " + x);
		System.out.println("y = " + y);
	}
}
