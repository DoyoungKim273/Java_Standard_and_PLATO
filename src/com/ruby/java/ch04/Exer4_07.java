package com.ruby.java.ch04;

import java.util.Random;

public class Exer4_07 {
	public static void main(String[] args) {
		
//		7)
		String str = "12345";
		char a = ' ';
		int sum1 = 0;
		int sum2 = 0;
		
		for (int i = 0; i < str.length(); i++) {
			sum1 += str.charAt(i) - '0'; 
			
			a  = str.charAt(i);
			int d = Character.getNumericValue(a);
			sum2 += d;
		}
		
		System.out.println("sum1 = " + sum1);
		System.out.println("sum2 = " + sum2);
		
//		8)
		Random random = new Random();
		int value = (int)(Math.random() * 6) + 1;
//		0부터 5(5.9999)까지의 정수를 구하게 됨 --> 1을 더하면 1부터 6까지의 정수 구할 수 있음
		System.out.println("value : " + value);
		
//		9)
		int num = 12345;
		int sum = 0;
		int tmp = 0;
		
		while (num > 0) {
			tmp = num % 10;
			sum += tmp;
			num = num / 10;	
		}
		System.out.println(sum);
	}
}
