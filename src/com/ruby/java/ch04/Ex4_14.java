package com.ruby.java.ch04;

import java.util.Scanner;

public class Ex4_14 {
	public static void main(String[] args) {
		int num = 0, sum = 0;
		System.out.println("숫자를 입력하세요. (예 : 12345) >>");
		
		Scanner scanner = new Scanner(System.in);
		String tmp = scanner.nextLine(); // 화면을 통해 입력받은 숫자 저장
		num = Integer.parseInt(tmp); // 입력받은 문자열(tmp)를 숫자로 변환
		
		while(num != 0) {
			sum += num % 10; // num을 10으로 나눈 나머지를 sum에 더함 (1의 자리수 더함)
			System.out.println("sum = " + sum + " , " + "num = " + num);
			
			num /= 10; // num을 10으로 나눈 값을 다시 num에 저장 -> 계속해서 10으로 나누어 가도록
		}
		System.out.println("각 자리수의 합:" + sum);
	}
}
