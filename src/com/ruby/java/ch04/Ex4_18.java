package com.ruby.java.ch04;

import java.util.Scanner;

public class Ex4_18 {
	public static void main(String[] args) {
		int menu = 0;
//		int num = 0; --> 굳이 선언해 줄 필요가 없는데?
		
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("(1) square");
			System.out.println("(2) square root");
			System.out.println("(3) log");
			System.out.print("원하는 메뉴(1 ~ 3)를 선택하세요. (종료 : 0) >>");
			
			String tmp = scanner.nextLine();
//			사용자가 enter 키를 누를때까지 대기, 입력된 한줄의 문자를 반환
//			이후 tmp 변수에 해당 내용저장
			menu = Integer.parseInt(tmp);
			
			if(menu == 0) {
				System.out.println("프로그램을 종료합니다");
				break;
			} else if (!(1 <= menu && menu <= 3)){
				System.out.println("메뉴를 잘못 선택하셨습니다. (종료 : 0) >>");
				continue;
			}
			System.out.println("선택하신 메뉴는 " + menu + "번 입니다.");
		}
	}
}
