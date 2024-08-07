package com.ruby.java.ch05;

import java.util.Scanner;

public class Exer5_06 {
	public static void main(String[] args) {
		String[] words = {"television", "computer", "mouse", "phone"};
		
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < words.length; i++) {
			char[] question = words[i].toCharArray(); // String을 char[]로 변환
			
			int j = (int)(Math.random() * words[i].length());
			char tmp = ' ';
			
			tmp = question[i];
			question[i] = question[j];
			question[j] = tmp;
			
			System.out.printf("Q%d. %s의 정답을 입력하세요. >> ",
					i + 1, new String(question));
			
			String answer = scanner.nextLine();
			
			if (words[i].equals(answer.trim())) { // trim으로 좌우 공백 제거 후 equals()로 비교
				System.out.println("정답입니다.");
			} else {
				System.out.println("오답입니다. 정답은 " + words[i] + " 입니다.");
			}
		}
	}
}
