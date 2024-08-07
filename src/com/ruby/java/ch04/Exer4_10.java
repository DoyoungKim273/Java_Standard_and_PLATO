package com.ruby.java.ch04;

import java.util.Random;

public class Exer4_10 {
	public static void main(String[] args) {
		
		int answer = (int)(Math.random() * 100) + 1;
//		구하고자 하는 수를 곱한 것을 괄호로 감싸서 int로 타입캐스팅 후 1 더해주기
		int input = 0;
		int count = 0;
		
		java.util.Scanner s = new java.util.Scanner(System.in);
		
		do {
			count ++;
			System.out.print("1과 100 사이의 값을 입력하세요. >>");
			input = s.nextInt(); // 입력 받은 값을 변수 input에 저장
			
			if(input == answer) {
				System.out.println("정답입니다.");
			} else if (input < answer){
				System.out.println("좀 더 큰 수를 입력하세요.");
			} else if (input > answer){
				System.out.println("좀 더 작은 수를 입력하세요");
			}
		} while (true);
	}
}
