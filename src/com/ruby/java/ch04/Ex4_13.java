package com.ruby.java.ch04;

public class Ex4_13 {
	public static void main(String[] args) {
		int sum = 0;
		int i = 0;
		
		while (sum <= 100) {
			System.out.println(i + " -> " + sum);
			sum += ++i; // i가 1씩 커져야 무한 반복이 이뤄지지 않음
		}
	}
}
