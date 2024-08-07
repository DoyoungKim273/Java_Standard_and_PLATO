package com.ruby.java.ch05;

public class Ex5_05 {
	public static void main(String[] args) {
		int[] ball = new int[45]; // 45개의 정수값 저장 위한 배열 생성
		
//		배열의 각 요소에 1 ~ 45의 값 저장
		for(int i = 0; i < ball.length; i++) {
			ball[i] = i + 1; // ball[0]에 1 저장
		}
		
		int tmp = 0; // 두 값을 바꾸는데 사용하는 임시 변수
		int j = 0; // 임의의 값 얻어서 저장할 변수
		
//		배열의 i번째 요소와 임의의 요소에 저장된 값을 서로 바꿔서 값을 섞음
//		0번째부터 5번째 요소까지 모두 6개만 바꿈
		for (int i = 0; i < ball.length; i++) {
			j = (int)(Math.random() * 45); // 0 ~ 44 범위의 임의의 값을 얻음
			tmp = ball[i];
			ball[i] = ball[j];
			ball[j] = tmp;
		}
		
//		배열 ball의 앞에서부터 6개의 값을 출력
		for (int i = 0; i < 6; i++) {
			System.out.printf("ball[%d] = %d%n", i, ball[i]);
		}
	}
}
