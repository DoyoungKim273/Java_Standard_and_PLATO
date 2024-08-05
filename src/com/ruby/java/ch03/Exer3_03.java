package com.ruby.java.ch03;

public class Exer3_03 {
	public static void main(String[] args) {
//		03)
		int num1 = 456;
		
		System.out.println(Math.round(456 / 100) * 100);
		
//		04)
		int numOfApple = 123; 
		int sizeOfBucket = 10;
//		int numOfBucket = (123 / 10) + (123 % 10 > 0 ? 1 : 0);
		int numOfBucket = (numOfApple / sizeOfBucket) + (numOfApple % sizeOfBucket > 0 ? 1 : 0);
		
		System.out.println("필요한 바구니의 수 : " + numOfBucket);
		
//		05)
		int num2 = 10;
		
		System.out.println(num2 > 0 ? "양수" : "음수");
		
//		06)
		int fahrenheit = 100;
		float celcius = (int)((5 / 9f * (fahrenheit - 32)) * 100 + 0.5) / 100f;
//		Math.round() 사용하지 않고 소수점 셋째자리 반올림
//		float celcius2 = celcius1 - ((celcius1 * 100000) % 1000) / 100000;
		
		System.out.println("fahrenheit : " + fahrenheit);
		System.out.println("celcius1 : " + celcius);
//		System.out.println("celcius2 : " + celcius2);
	}
}
