package com.ruby.java.ch02;

public class Exer2_08 {
public static void main(String[] args) {
	int x = 1;
	int y = 2;
	int z = 3;
	
	int tmp = x; // tmp에 x 대입
	x = y; // x에는 y 대입
	y = z; // y에는 z 대입
	z = tmp; // z에는 tmp(x) 대입
	
//	System.out.println은 하나의 인수만 받아들여야 하기에 오류 발생함
//	System.out.println("x=" + x, "y=" + y, "z=" + z);
	
	System.out.println("x = " + x);
	System.out.println("y = " + y);
	System.out.println("z = " + z);
}
}
