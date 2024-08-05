package com.ruby.java.ch03;

public class Ex3_11 {
	public static void main(String[] args) {
//		소수점 첫째 자리에서 반올림한 결과를 정수로 반환
		long result = Math.round(4.52);
		
		double pi = 3.141592;
		double shortPi1 = Math.round(pi * 1000) / 1000.0;
		double shortPi2 = Math.round(pi * 1000) / 1000;
//		pi의 값을 소수점 넷째 자리인 5에서 반올림, 3.142 출력
//		round 메서드는 매개변수로 받은 값을 소수점 첫째 자리에서 반올림, round의 값은 3142
//		3142를 1000.0으로 나눔, 3.142 출력
//		1000.0이 아닌 1000으로 나누면 int 나누기 int 이므로 답은 3이 됨
		
		System.out.println(result);
		System.out.println(shortPi1);
		System.out.println(shortPi2);
	}
}
