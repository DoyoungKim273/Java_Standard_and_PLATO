package com.ruby.java.ch03;

import java.util.Scanner;

public class Ex3_15 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char ch = ' ';
		
		System.out.println("문자를 하나 입력하세요. >> ");
		
		String input = scanner.nextLine();
		ch = input.charAt(0);
		
//		유니코드에서 문자 '0' 부터 '9' 까지가 연속적으로 배치되어 있기에 가능한 식
		if('0' <= ch && ch <= '9') {
			System.out.println("입력하신 문자는 숫자입니다.");
		}

//		마찬가지로 연속적으로 배치되어 있기에 가능한 식
		if(('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
			System.out.println("입력하신 문자는 영문자입니다.");
		}
	}
}
