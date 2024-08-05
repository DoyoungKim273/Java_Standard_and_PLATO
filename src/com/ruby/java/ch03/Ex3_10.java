package com.ruby.java.ch03;

public class Ex3_10 {
	public static void main(String[] args) {
//		int 타입과 int 타입의 연산 결과는 int 타입
//		long으로 자동 형변환 되어도 값은 변하지 않음
		long a = 1_000_000 * 1_000_000;
//		올바른 결과를 얻기 위해서는 숫자 둘 중 하나를 long 타입으로 변환해야함
		long b = 1_000_000 * 1_000_000L;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
	
	}
}
