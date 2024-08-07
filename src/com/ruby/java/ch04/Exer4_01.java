package com.ruby.java.ch04;

public class Exer4_01 {
	public static void main(String[] args) {
//		1)
		int x = 0;
		if (10 < x && x < 20) {
			System.out.println("true1");
		} else {
			System.out.println("false1");
		}
		
//		2)
		char ch1 = ' ';
		if (ch1 != ' ' && ch1 != '\t' ) {
			System.out.println("true2");
		} else {
			System.out.println("false2");
		}
		
//		3)
		char ch2 = 'x';
		if (ch2 == 'x' || ch2 == 'X') {
			System.out.println("true3");
		} else {
			System.out.println("false3");
		}
		
//		4) 
		char ch3 = '9';
		if ('0' <= ch3 && ch3 <= '9') {
			System.out.println("true4");
		} else {
			System.out.println("false4");
		}
		
//		5) 
		char ch4 = 'A';
		if (('a' <= ch4 && 'z' <= ch4) || ('A' <= ch4 && 'Z' <= ch4)) {
			System.out.println("true5");
		} else {
			System.out.println("false5");
		}
		
//		6) 
		int year = 1500;
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))){
			System.out.println("true6");
		} else {
			System.out.println("false6");
		}
		
//		7)
		boolean powerOn = false;
		if (powerOn == false) {
			System.out.println("true7");
		} else {
			System.out.println("false7");
		}
		
//		8)
		String str = "no";
//		if (str == "yes") {
		if (str.equals("yes")) {
			System.out.println("true8");
		} else {
			System.out.println("false8");
		}
	}
}
