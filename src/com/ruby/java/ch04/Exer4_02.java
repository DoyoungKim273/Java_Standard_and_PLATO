package com.ruby.java.ch04;

public class Exer4_02 {
	public static void main(String[] args) {

//		2) 
		int x = 0;
		int sum = 0;

		for (x = 0; x <= 20; x++) {
			if (x % 2 != 0 && x % 3 != 0) {
				sum += x;
			}
		}
		System.out.println(sum);

//		3)
		int y = 0;
		int z = 0;
		int tri = 0;
		long tempTri = 0;

		for (y = 1; y <= 10; y++) {
			tempTri = 0; // 삼각수 구하는 for문 밖에서 매번 초기화 해주어야 이전 계산값이 누적되지 않음
			for (z = 0; z <= y; z++) {
				tempTri += z;
//				System.out.println(tempTri);
			}
			tri += tempTri;
		}
		System.out.println("3) " + tri);

//		3_re)
		int r = 0;
		int tri2 = 0;
		int tempTri2 = 0;

		for (r = 1; r <= 10; r++) {
			tempTri2 += r; // ex ) 1 + 2 + 3
			tri2 += tempTri2; // ex ) (1 + 2 + 3) ~~~ 의 합
		}
		System.out.println("3_re) " + tri2);

//		4)
		int k = 0;
		int sum2 = 0;
		int cnt = 0;
		
//		while (sum2 == 100) { -> 100이 되는 즉시 종료되는 루프가 아니라 처음부터 루프에 들어가지 않는 조건
		while (sum2 != 100) { // 100이 '아닌 동안' 루프 돌아야함
			if (k % 2 == 0) { // k가 짝수인 경우를 if문으로 쓴다면
				sum2 -= k; // k = -k 를 하고 sum += -k 를 하는것 보다 훨씬 간단해짐
			} else { // 당연히 else문으로 반대의 경우도 고려해야함
				sum2 += k;
			}
			k++;
			cnt++;
		}
		System.out.println(cnt);
	}
}
