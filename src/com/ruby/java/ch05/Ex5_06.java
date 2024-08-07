package com.ruby.java.ch05;

public class Ex5_06 {
	public static void main(String[] args) {
		String[] names = {"kim", "park", "lee"};
		
		for (int i = 0; i < names.length; i++) {
			System.out.println("names[" + i + "] : " + names[i]);
		}
		
		String tmp = names[2];
		System.out.println("tmp : " + tmp);
		names[0] = "song";
		
		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
	}
}
