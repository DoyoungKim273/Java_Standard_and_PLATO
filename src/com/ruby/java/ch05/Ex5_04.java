package com.ruby.java.ch05;

import java.util.Arrays;

public class Ex5_04 {
	public static void main(String[] args) {
		int[] numArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println(Arrays.toString(numArr));
		
		for (int i = 0; i < 100; i++) {
			int n = (int)(Math.random() * 10); // 0 ~ 9 중 임의의 값 얻음
			int tmp = numArr[0];
			numArr[0] = numArr[n];
			numArr[n] = tmp; 
//			배열의 임의의 위치에 있는 값과 배열의 첫번째 요소의 값을 교환하는 일 100번 반복
		}
		System.out.println(Arrays.toString(numArr));
	}
}
