package com.ruby.java.ch04;

import java.util.Scanner;

public class Ex4_20 {
	public static void main(String[] args) {
		int menu = 0;
		int num = 0;

		Scanner scanner = new Scanner(System.in);

		outer : // while문에 outer라는 이름을 붙임
		while (true) {
			System.out.println("(1) square");
			System.out.println("(2) square root");
			System.out.println("(3) log");
			System.out.print("원하는 메뉴(1 ~ 3)를 선택하세요. (종료 : 0) >>");

			String tmp = scanner.nextLine();
//			사용자가 enter 키를 누를때까지 대기, 입력된 한줄의 문자를 반환
//			이후 tmp 변수에 해당 내용저장
			menu = Integer.parseInt(tmp);

			if (menu == 0) {
				System.out.println("프로그램을 종료합니다");
				break;
			} else if (!(1 <= menu && menu <= 3)) {
				System.out.println("메뉴를 잘못 선택하셨습니다. (종료 : 0) >>");
				continue;
			}
			
			for(;;) { // 조건 없이 반복되는 무한 루프 생성 -> ex) 사용자의 입력을 기다리는 경우
				System.out.print("계산할 값을 입력하세요 (계산 종료 : 0, 전체 종료 : 99 >>)");
				tmp = scanner.nextLine();
				num = Integer.parseInt(tmp);
				
				if (num == 0) {
					break;
				} // 계산 종료, for문을 벗어남
				
				if (num == 99) {
					break outer;
				} // 전체 종료, for문과 while문을 모두 벗어남
				
				switch (menu) {
					case 1 :
						System.out.println("result = " + num * num); // 제곱 출력
						break;
					case 2 :
						System.out.println("result = " + Math.sqrt(num)); // 제곱근 출력
						break;
					case 3 :
						System.out.println("result = " + Math.log(num)); // 로그 출력
						break;
				} // switch문의 끝
			} // for(;;)의 끝
		} // while의 끝
	}
}
