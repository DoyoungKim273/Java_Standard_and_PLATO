package com.ruby.java.ch05;

import java.util.Arrays;

public class Ex5_01 {
	public static void main(String[] args) {
		int[] iArr1 = new int[10];
		int[] iArr2 = new int[10];
//		int[] iArr3 = new int[]{100, 95, 80, 70, 60};
		int[] iArr3 = {100, 95, 80, 70, 60};
		char[] chArr = {'a', 'b', 'c', 'd'};
		
		for (int i = 0; i < iArr1.length; i++) {
			iArr1[i] = i + 1;
		} // 1 ~ 10까지의 숫자를 순서대로 배열에 넣음
		
		for (int i = 0; i < iArr2.length; i++) {
			iArr2[i] = (int)(Math.random() * 10) + 1;
		} // 랜덤한 값 10개를 배열에 저장

		for (int i = 0; i < iArr1.length; i++) {
			System.out.print(iArr1[i] + " ");
		} // 배열에 저장된 값들을 출력
		System.out.println();
		
		System.out.println(Arrays.toString(iArr2));
		System.out.println(Arrays.toString(iArr3));
		System.out.println(Arrays.toString(chArr));
		System.out.println(iArr3);
		System.out.println(chArr);
	}
}
