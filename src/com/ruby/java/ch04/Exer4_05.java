package com.ruby.java.ch04;

public class Exer4_05 {
	public static void main(String[] args) {
//		5)
		int i = 0;
		int j = 0;
		
		while (i <= 10) {
			i++;
			while (j <= i) {
				System.out.print("*");
				System.out.println();
				j++;
			}
		}
		
//		6)
		int a = 0;
		int b = 0;
		int dice = 0;
		
		for (a = 1; a <= 6; a++) {
			for (b = 1; b <= 6; b++) {
				if(a + b == 6) {
					dice = (a + b);
					System.out.println(a + " + " + b + " = " + dice);
				}
			}
		}
	}
}
