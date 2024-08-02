package com.ruby.java.ch02;

// import 문은 pakage 아래 class 위에 위치
import java.util.Scanner;

public class Ex2_10 {
public static void main(String[] args) {
//	Scanner 클래스의 객체 생성
	Scanner scanner = new Scanner(System.in);
	
	System.out.print("두자리 정수를 하나 입력해주세요.");
//	입력 받은 내용을 input에 저장
	String input = scanner.nextLine();
//	입력 받은 내용을 int 타입 값으로 변환
	int num = Integer.parseInt(input);
	
	System.out.println("입력 내용:" + input);
	System.out.printf("num=%d%n", num);
}
}
