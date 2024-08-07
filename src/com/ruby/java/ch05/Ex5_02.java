package com.ruby.java.ch05;

public class Ex5_02 {
	public static void main(String[] args) {
		int sum = 0;
		float average = 0f;

		int[] score = { 100, 88, 100, 100, 90, 79, 88, 91, 33, 55, 95 };

		int max = score[0];
		int min = score[0];
//		배열의 첫번째 값으로 초기화
		
		for (int i = 0; i < score.length; i++) {
			sum += score[i];
			
			if (score[i] > max) {
				max = score[i];
			} else if (score[i] < min){
				min = score[i];
			}
		}
		average = sum / (float)score.length; 
//		계산 결과를 float 타입으로 얻기 위해 형변환
		
		System.out.println("총합 : " + sum);
		System.out.println("평균 : " + average);
		
		System.out.println("최대값 : " + max);
		System.out.println("최소값 : " + min);
	}
}
