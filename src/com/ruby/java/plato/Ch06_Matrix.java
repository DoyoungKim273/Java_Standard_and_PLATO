package com.ruby.java.plato;

import java.util.Random;

/*
 * 클래스 메소드 구현 실습 목적
 */
class Matrix {
	int rows;
	int cols;
	int[] data;
	public Matrix(int rows, int cols) {
		this.rows = rows; 
		this.cols = cols;
//		행렬의 크기만큼 1차원 배열을 생성
		data = new int[rows * cols];
	}
	
//	난수로 data 입력 받기
	void getData() {
		Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
//				0부터 9까지의 값을 행렬에 저장
				data[i * cols + j] = random.nextInt(10);
			}
		}
	}
	
//	행렬을 더하는 메서드
	Matrix addMatrix(Matrix b) {
//		결과 행렬 생성
		Matrix m = new Matrix(this.rows, this.cols);
		for (int i = 0; i < data.length; i++) {
//			두 행렬의 같은 위치 요소 더해서 결과 행렬에 저장
			m.data[i] = this.data[i] + b.data[i];
		}
//		결과 행렬 반환
		return m;
	}
	
//	행렬을 곱하는 메서드
	Matrix multiplyMatrix(Matrix b) {
		Matrix m = new Matrix(this.rows, b.cols);
//		결과 행렬의 행 인덱스
		for (int i = 0; i < this.rows; i++) {
//			결과 행렬의 열 인덱스
			for (int j = 0; j < b.cols; j++) {
				int sum = 0;
//				곱셈과 덧셈을 수행할 인덱스
				for (int k = 0; k < this.cols; k++) {
//					곱셈 결과 누적
					sum += this.data[i * this.cols + k] * b.data[k * b.cols + j];
				}
//				결과 행렬에 저장
				m.data[i * b.cols + j] = sum;
			}
		}
		return m;	
	}
	
//	행렬을 전치하는 메서드
	Matrix transposeMatrix() {
		Matrix m = new Matrix(this.cols, this.rows);
//		원래 행렬의 행 인덱스
		for (int i = 0; i < this.rows; i++) {
//			원래 행렬의 열 인덱스
			for (int j= 0; j < this.cols; j++) {
//				전치 행렬에 저장
				m.data[j * this.rows + i] = this.data[i * this.cols + j];
			}
		}
		return m;
	}
	
//	행렬을 2차원 배열 모양으로 출력하는 메서드
	void showMatrix(String str) {
		System.out.println(str);
//		2차원 배열 모양으로 출력하는 코드 작성
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
//				요소 출력
				System.out.print(this.data[i * this.cols + j] + " " ); 
			}
			System.out.println();
		}
	}
}
public class Ch06_Matrix {
	public static void main(String[] args) {
		
		/*
		 * 난수 생성으로 초기화
		 * A[3][4] = B[3][4] + C[3][4]; D[3][5] = B[3][4] * E[4][5];
		 * F[4][3] = B[3][4]의 전치 행렬
		 */
		
//		3 x 4 행렬 B와 C 생성
		Matrix B = new Matrix(3,4);
		Matrix C = new Matrix(3,4);
		
//		결과 행렬 A, D, F 선언
		Matrix A, D, F;
		
//		4 x 5 행렬 E 생성
		Matrix E = new Matrix(4,5);

//		행렬 더하기 
		System.out.println("행렬 더하기: A[3][4] = B[3][4] + C[3][4]");
		
//		B, C, D 행렬에 각각 난수 데이터 입력
		B.getData();
		C.getData();
		E.getData();
		
//		A = B + C 연산
		A = B.addMatrix(C);

//		B, C, A 행렬 출력
		B.showMatrix("B[3][4]");
		C.showMatrix("C[3][4]");
		A.showMatrix("A[3][4]");
		
//		행렬 곱하기
		System.out.println("행렬 곱하기: D[3][5] = B[3][4] * E[4][5]");
		
//		D = B * E 연산
		D = B.multiplyMatrix(E);
		
//		B, E, D 행렬 출력
		B.showMatrix("B[3][4]");
		E.showMatrix("E[4][5]");
		D.showMatrix("D[3][5]");
		
//		행렬 전치
		System.out.println("행렬 전치: F[4][3] = B[3][4]의 전치 행렬");
		
//		F = B의 전치 행렬
		F = B.transposeMatrix();
		
//		B, F 행렬 출력
		B.showMatrix("B[3][4]"); 
		F.showMatrix("F[4][3]");
	}
}
