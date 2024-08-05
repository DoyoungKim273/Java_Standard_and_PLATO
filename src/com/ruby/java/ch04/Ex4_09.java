package com.ruby.java.ch04;

public class Ex4_09 {
	public static void main(String[] args) {

		int sum = 0; // sum을 반복문 밖에서 선언해야 반복문 밖에서 한번만 sum 출력 가능
		
		for(int i = 1; i <= 5; i++) {
			System.out.println(i);
			
			sum += i; // 단, sum의 합산은 반복문 안에서 이루어져야함
			
			System.out.println("**********");
			
			for(int j = 1; j <= 10; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		System.out.println("1부터 5까지의 합은 " + sum + " 입니다.");
		System.out.println();
		
		for(int k = 1; k <= 1; k++) {
			System.out.print("*");
		}
		System.out.println();
		for(int k = 1; k <= 2; k++) {
			System.out.print("*");
		}
		System.out.println();
		for(int k = 1; k <= 3; k++) {
			System.out.print("*");
		}
		System.out.println();
		for(int k = 1; k <= 4; k++) {
			System.out.print("*");
		}
		System.out.println();
		for(int k = 1; k <= 5; k++) {
			System.out.print("*");
		}
		System.out.println();
		for(int k = 1; k <= 6; k++) {
			System.out.print("*");
		}
		System.out.println();
	}
}
