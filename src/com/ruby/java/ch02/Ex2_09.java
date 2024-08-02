package com.ruby.java.ch02;

public class Ex2_09 {
	
	public static void main(String[] args) {
		String url = "www.codechobo.com";
		float f1 = .10f;
		float f2 = 1e1f;
		float f3 = 3.14e3f;
		double d = 1.23456789;
		
//		printf를 쓰면 같은 값이라도 다른 형식으로 출력됨
//		개행문자를 별도로 사용해주어야 줄바꿈 이루어짐
//		%f는 소수점 아래 6자리까지, %e는 지수 형태 출력, %g는 값을 간략하게 표현할 때 사용
		System.out.printf("f1=%f, %e, %g%n" , f1, f1, f1);
		System.out.printf("f2=%f, %e, %g%n" , f2, f2, f2);
		System.out.printf("f3=%f, %e, %g%n" , f3, f3, f3);
		
//		%전체자리.소수점아래자리f
//		전체 14자리 중 소수점 아래 10자리
		System.out.printf("d=%f%n", d);
		System.out.printf("d=%14.10f%n", d);

		System.out.printf("[12345678901234567890]%n");
//		문자열의 길이만큼 출력 공간 확보
		System.out.printf("[%s]%n", url);
//		최소 20글자만큼 출력 공간 확보(우측 정렬)
		System.out.printf("[%20s]%n", url);
//		좌측 정렬
		System.out.printf("[%-20s]%n", url);
//		왼쪽에서 8글자만 출력(문자열의 일부만 출력)
		System.out.printf("[%.8s]%n", url);
	}	
}
