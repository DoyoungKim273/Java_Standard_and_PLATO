package com.ruby.java.plato;

/*
 * 6장 구현과제1
 */

class Polynomial3 implements Comparable<Polynomial3> {
//	계수 : 변수 앞에 곱해진 숫자나 상수
	double coef;
//	지수 : 변수가 거듭제곱되는 정도를 나타낸 수 
	int exp;

//	생성자 : 다항식 항을 생성, 계수와 지수를 초기화
	Polynomial3(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}

//	다항식 항을 문자열로 변환
	@Override
	public String toString() {
		return coef + "x**" + exp + " ";
	}

//	지수를 기준으로 두 다항식 항을 비교
//	지수가 같으면 계수로 비교
	@Override
	public int compareTo(Polynomial3 d2) {
//		큰 지수부터 정렬하기 위해 반환
		return d2.exp - exp;
	}
}

//	다항식의 연산을 처리하는 클래스
public class ACh06_PolynomialMergeSorting {
	
//	배열 요소 a[idx1]와 a[idx2]의 값을 교환
//	두 정렬된 배열의 부분을 병합하는 함수
	static void merge(Polynomial3[] a, int lefta, int righta, int leftb, int rightb) {
//		임시 배열 -> 두 배열을 비교하면서 병합하기 위해 ㅎ사용
		Polynomial3 temp[] = new Polynomial3[30];
//		첫번째 배열의 시작점
		int i = lefta;
//		두번째 배열의 시작점
		int j = leftb;
//		임시 배열의 인덱스
		int k = 0;

//		두 배열을 비교하며 병합
//		두 배열의 모든 요소를 비교하여 temp 배열에 순서대로 복사하는 작업 수행
		while (i <= righta && j <= rightb) {
//			null 체크 추가 (빈 항이 처리되지 않도록 방지)
			if (a[i] == null) {
				i++;
				continue;
			}

			if (a[j] == null) {
				j++;
				continue;
			}

			if (a[i].compareTo(a[j]) <= 0) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}

//		남아있는 요소를 임시 배열에 추가
		while (i <= righta) {
			if (a[i] != null) {
				temp[k++] = a[i++];
			}
			i++;
		}

		while (j <= rightb) {
			if (a[j] != null) {
				temp[k++] = a[j++];
			}
			j++;
		}

//		임시 배열의 내용을 원본 배열에 복사
		for (int l = 0; l < 30; l++) {
			if (temp[l] != null) {
				a[lefta + 1] = temp[l];
			}
		}
	}

//	비재귀 버전 퀵 정렬
	static void MergeSort(Polynomial3[] a, int left, int right) {
//		더이상 분할할 수 없는 경우	
//		left와 right는 배열의 시작과 끝 인덱스를 나타냄
		if (left == right) {
			return;
		}
//		중간점 계산
		int mid = (left + right) / 2;
//		왼쪽 반을 정렬
		MergeSort(a, left, mid);
//		오른쪽 반을 정렬
		MergeSort(a, mid + 1, right);
//		정렬된 두 부분을 병합
		merge(a, left, mid, mid + 1, right);
		return;
	}

//	다항식의 각 항을 toString() 메서드를 이용하여 문자열로 반환하여 출력하는 메서드
	static void ShowPolynomial(String str, Polynomial3[] x, int count) {
		// str 변수는 다항식 이름으로 스트링이다
		// count가 -1이면 다항식 x의 length로 계산하고 -1이면 count가 다항식 항의 숫자이다
		// 정렬후 다항식 x = 2.5x**7 + 3.8x**5 + 3.1x**4 + 1.5x**3 + 3.3x**2 + 4.0x**1 +
		// 2.2x**0
		int n = 0;
		if (count < 0) {
//			count가 -1이면 x의 길이를 n으로 설정, 배열 전체를 출력
			n = x.length;
		} else {
//			그렇지 않으면 주어진 count를 n으로 설정, 주어진 수의 항만을 출력
			n = count;
		}
//		다항식 이름 출력
		System.out.println(str);
		for (int i = 0; i < n; i++) {
//			각 항을 출력
			System.out.println(x[i].toString());
			if (i < n - 1) {
//				각 항 사이에 + 기호 추가
				System.out.println(" + ");
			}
		}
		System.out.println();
	}

	static int AddPolynomial(Polynomial3[] x, Polynomial3[] y, Polynomial3[] z) {
//		z = x + y, 다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴
//		두 다항식을 더한 결과를 새로운 다항식 배열 z에 저장
//		같은 지수끼리 더하고 결과를 z 배열에 추가
//		더한 결과의 항의 개수를 반환
		int p = 0, q = 0, r = 0;
		int terms = 0;

		while (p < x.length && q < y.length) {
			if (x[p].exp == y[p].exp) {
//				지수가 같으면 계수를 합산
				double sumCoef = x[p].coef + y[q].coef;
				if (sumCoef != 0) {
					terms = addTerm(z, new Polynomial3(sumCoef, x[p].exp), terms);
				}
				p++;
				q++;
			} else if (x[p].exp > y[q].exp) {
//				x의 항을 추가
				terms = addTerm(z, x[p++], terms);
			} else {
//				y의 항을 추가
				terms = addTerm(z, y[p++], terms);
			}
		}

//		남은 x항을 추가
		while (p < x.length) {
			terms = addTerm(z, x[p++], terms);
		}

//		남은 y항을 추가
		while (q < y.length) {
			terms = addTerm(z, y[q++], terms);
		}

//		최종 항의 수를 리턴
		return terms;

	}

//	다항식에 새로운 항을 추가하는 메서드
//	계수가 0이 되면 해당 항을 제거하고 배열을 정리
	static int addTerm(Polynomial3[] z, Polynomial3 term, int terms) {
//		다항식 z에 새로운 항 term을 추가, 지수가 같은 항이 있으면 계수만 합함
//		추가된 항의 수를 count하여 terms으로 리턴

//		null인 항은 추가되지 않음
		if (term == null) {
			return terms;
		}
		for (int i = 0; i < terms; i++) {
			if (z[i].exp == term.exp) {
				z[i].coef += term.coef;
//				계수가 0이면 제거
				if (z[i].coef == 0) {
					for (int j = i; j < terms - 1; j++) {
						z[j] = z[j + 1];
					}
					terms--;
				}
				return terms;
			}
		}
		z[terms] = term;
		return ++terms;

	}

	static int MultiplyPolynomial(Polynomial3[] x, Polynomial3[] y, Polynomial3[] z) {
//		z = x * y, 다항식 z의 항의 수는 terms으로 리턴
//		terms = addTerm(z, term, terms); 사용하여 곱셈항을 추가
		int terms = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				double coef = x[i].coef * y[j].coef;
				int exp = x[i].exp + y[j].exp;
				Polynomial3 term = new Polynomial3(coef, exp);
//				모든 항끼리 곱하여 결과를 z 배열에 추가하며, addTerm 메서드를 사용하여 정리
				terms = addTerm(z, term, terms);
			}
		}
//		곱한 결과의 항의 개수를 반환
		return terms;
	}

//	주어진 값 value에 대해 다항식의 결과값을 계산하는 메서드
//	각 항의 계수와 value의 지수만큼 거듭제곱한 값을 곱하여 결과를 계산
	static double EvaluatePolynomial(Polynomial3[] z, int zTerms, int value) {
//		zTerms는 다항식 z의 항의 수, value는 f(x)를 계산하기 위한 x 값
//		다항식 계산 결과를 double로 리턴
		double result = 0.0;
		for (int i = 0; i < zTerms; i++) {
//			각 항의 값을 계산
			result += z[i].coef * Math.pow(value, z[i].exp);
		}
		return result;
	}

	public static void main(String[] args) {
//		다항식 배열 x와 y 초기화
		Polynomial3[] x = { new Polynomial3(1.5, 3), new Polynomial3(2.5, 7), new Polynomial3(3.3, 2),
				new Polynomial3(4.0, 1), new Polynomial3(2.2, 0), new Polynomial3(3.1, 4), new Polynomial3(3.8, 5), };
		Polynomial3[] y = { new Polynomial3(1.5, 1), new Polynomial3(2.5, 2), new Polynomial3(3.3, 3),
				new Polynomial3(4.0, 0), new Polynomial3(2.2, 4), new Polynomial3(3.1, 5), new Polynomial3(3.8, 6), };
		int nx = x.length;

//		다항식 출력
		ShowPolynomial("다항식 x = ", x, -1);
		ShowPolynomial("다항식 y = ", y, -1);
//		배열 x를 퀵 정렬
		MergeSort(x, 0, x.length - 1);
//		배열 y를 퀵 정렬
		MergeSort(y, 0, y.length - 1);
//		정렬된 다항식 출력
		ShowPolynomial("정렬후 다항식 x = ", x, -1);
		ShowPolynomial("정렬후 다항식 y = ", y, -1);

		Polynomial3[] z = new Polynomial3[20];

//		다항식 덧셈 z = x + y
		int zTerms = AddPolynomial(x, y, z);
		ShowPolynomial("덧셈후 다항식 z = ", z, zTerms);

//		다항식 곱셈 z = x * y
		zTerms = MultiplyPolynomial(x, y, z);
//		배열 x를 퀵 정렬
		MergeSort(z, 0, zTerms);
		ShowPolynomial("곱셈후 다항식 z = ", z, zTerms);
//		다항식 값 계산 함수 z(10) 값 계산
		double result = EvaluatePolynomial(z, zTerms, 1);
		System.out.println(" result = " + result);
	}
}
