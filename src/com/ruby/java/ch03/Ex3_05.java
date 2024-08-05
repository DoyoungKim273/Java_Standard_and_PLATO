package com.ruby.java.ch03;

public class Ex3_05 {
	public static void main(String[] args) {
//		경우 1)
		double d = 84.5;
//		타입캐스팅(형변환) --> (타입)피연산자
		int score = (int) d;
		
//		경우 2)
		float f = 1234; // (float) 생략 --> 자동 형변환
//		크기가 작은 자료형의 변수를 큰 자료형의 변수에 저장할 때는 자동으로 형변환 이루어짐
//		byte b = 1000; --> byte 값의 범위를 벗어나서 에러 발생
		byte b = (byte)1000; // 명시적 형변환
//		큰 자료형의 값을 작은 자료형의 변수에 저장하려면 명시적으로 형변환 연산자를 사용해서 변환해주어야함
		
		System.out.println("score = " + score);
//		형변환 연산자는 그저 피연산자의 값을 읽어서 지정된 타입으로 형변환 후 그 결과를 반환할 뿐
//		피연산자인 변수 d의 값은 형변환 후에도 아무런 변화 X
		System.out.println("d = " + d);
	}
}
