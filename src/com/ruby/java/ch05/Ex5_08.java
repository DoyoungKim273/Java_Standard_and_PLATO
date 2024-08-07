package com.ruby.java.ch05;

public class Ex5_08 {
	public static void main(String[] args) {
		int[][] score = { { 100, 100, 100 }, { 20, 20, 20 }, { 30, 30, 30 }, { 40, 40, 40 }, { 50, 50, 50 } };

//		8)
		int sum = 0;

		for (int i = 0; i < score.length; i++) {
			for (int j = 0; j < score[i].length; j++) {
				System.out.printf("score[%d][%d] = %d%n", i, j, score[i][j]);

				sum += score[i][j];
			}
		}

		System.out.println("sum = " + sum);

//		9)
		int korTotal = 0, engTotal = 0, mathTotal = 0;

		System.out.println("번호  국어  영어  수학  총점  평균");
		System.out.println("============================");

		for (int i = 0; i < score.length; i++) {
			int sum2 = 0;
			float avg = 0.0f;

			korTotal += score[i][0];
			engTotal += score[i][1];
			mathTotal += score[i][2];
			System.out.printf("%3d", i + 1); // 번호를 숫자의 너비가 3자리가 되도록 출력

			for (int j = 0; j < score[i].length; j++) {
				sum2 += score[i][j];
				System.out.printf("%5d", score[i][j]); // 각 배열의 숫자를 너비 5로 출력
			}

			avg = sum / (float) score[i].length;
			System.out.printf("%5d %5.1f%n", sum, avg); // 총점과 평균을 너비 5, 5.1로 출력
		}
		System.out.println("============================");
		System.out.printf("총점 : %3d %4d %4d%n", korTotal, engTotal, mathTotal);
	}
}
