package com.ruby.java.ch06;

public class Ex6_01 {
	public static void main(String[] args) {
		
		Tv t; // Tv 인스턴스를 참조하기 위한 변수 t 선언
		t = new Tv(); // Tv 인스턴스 생성
		t.channel = 7; // Tv 인스턴스의 멤버변수 channel의 값을 7로 함
		t.channelDown(); // Tv 인스턴스의 메서드 channelDown()을 호출
		System.out.println("현재 채널은 " + t.channel + " 입니다.");
	}
}

class Tv {
	// Tv의 속성(멤버변수)
	String color;
	boolean power;
	int channel;
	
	
	void power() {
		power = !power;
	}
	
	void channerUp() {
		++channel;
	}
	
	void channelDown() {
		--channel;
	}
}
