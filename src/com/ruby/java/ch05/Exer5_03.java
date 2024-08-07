package com.ruby.java.ch05;

public class Exer5_03 {
	public static void main(String[] args) {
//		3)
		int[] arr1 = { 10, 20, 30, 40, 50 };
		int sum1 = 0;

		for (int i = 0; i < arr1.length; i++) {
			sum1 += arr1[i];
		}

		System.out.println("sum1 = " + sum1);

//		4)
		int[][] arr2 = { { 5, 5, 5, 5, 5 }, { 10, 10, 10, 10, 10 }, { 20, 20, 20, 20, 20 }, { 30, 30, 30, 30, 30 } };
		
		int total = 0;
		float avg = 0;
		
		for(int j = 0; j < arr2.length; j++) {
			for(int k = 0; k < arr2[j].length; k++) {
				total += arr2[j][k];
				avg = total / (float)(arr2.length * arr2[j].length);
			}
		}
		System.out.println("total = " + total);
		System.out.println("avg = " + avg);
	}
}
