package com.ruby.java.ch04;

public class Ex4_07 {
	public static void main(String[] args) {
		int num = 0;
		
		for (int i = 1; i <= 5; i++) {
//			0.0 <= Math.random() < 1.0
//			0.0 은 범위에 포함되고 1.0은 포함되지 않음
			num = (int)(Math.random() * 6) + 1; // 1과 6사이의 임의의 수 얻는 경우
//			각 변에 6을 곱하고 -> 각 변을 int 형으로 변환 -> 각 변에 1을 더함
			System.out.println(num);
		}
	}
}
