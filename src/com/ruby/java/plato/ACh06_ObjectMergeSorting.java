package com.ruby.java.plato;

//	Comparable<PhyscData> 인터페이스를 구현하여 객체간 비교를 가능하게 함
class PhyscData implements Comparable<PhyscData> {
	String name; // 이름
	int height; // 키
	double vision; // 시력

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
//	Comparable 인터페이스의 compareTo 메서드를 오버라이드
	public int compareTo(PhyscData d) {
//		키를 기준으로 정렬하도록 설정 (오름차순)
		if (this.height > d.height) {
			return 1;
		} else if (this.height < d.height) {
			return -1;
		} else {
			return 0;
		}
	}

}

public class ACh06_ObjectMergeSorting {
//	두 부분 배열을 병합하는 메서드 (배열 요소 a[idx1]와 a[idx2]의 값을 교환)
//	병합 정렬할 전체 배열, 첫번째 부분의 시작 / 끝 인덱스, 두번째 부분의 시작 / 끝 인덱스
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb) {
		int size = rightb - lefta + 1;
//		임시 배열
		PhyscData[] temp = new PhyscData[size];
//		첫번째 배열의 시작점
		int i = lefta;
//		두번째 배열의 시작점
		int j = leftb;
//		임시 배열의 인덱스
		int k = 0;

//		비교 및 복사 -> 남은 요소 복사 -> 결과 복사
//		두 배열을 비교하며 병합
//		두 부분 배열의 모든 요소를 비교하여 temp 배열에 순서대로 복사하는 작업 수행
		while (i <= righta && j <= rightb) {
			if (a[i].compareTo(a[j]) <= 0) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}

//		남아있는 요소를 임시 배열에 추가
//		남아있는 요소들을 temp 배열에 그대로 복사
//		k는 계속해서 temp 배열의 다음 위치를 가리키며, i 또는 j를 증가시켜 남은 요소를 temp에 복사
		while (i <= righta) {
			temp[k++] = a[i++];
		}

		while (j <= rightb) {
			temp[k++] = a[j++];
		}

//		임시 배열의 내용을 원본 배열에 복사
//		원래의 두 부분 배열이 있었던 위치에 덮어쓰는 방식으로 진행
//		temp 배열에는 두 부분의 배열의 모든 요소가 정렬된 상태에 들어 있음
//		temp 배열의 내용을 원본 배열 a에 복사하여 병합된 결과를 원래 위치에 저장
//		a 배열의 lefta에서 rightb까지의 범위가 정렬된 상태로 갱신됨
		for (int l = 0; l < size; l++) {
			a[lefta + l] = temp[l];
		}
	}

//	Merge Sort 구현(퀵 정렬 - 비재귀 버전)
	static void MergeSort(PhyscData[] a, int left, int right) {
//		더이상 분할할 수 없는 경우
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

	public static void main(String[] args) {
		PhyscData[] x = { new PhyscData("강민하", 162, 0.3), new PhyscData("김찬우", 173, 0.7),
				new PhyscData("박준서", 171, 2.0), new PhyscData("유서범", 171, 1.5), new PhyscData("이수연", 168, 0.4),
				new PhyscData("장경오", 171, 1.2), new PhyscData("황지안", 169, 0.8), };
		int nx = x.length;

//		배열 x를 Merge Sort로 정렬
		MergeSort(x, 0, nx - 1);
		System.out.println("오름차순으로 정렬했습니다.");
		System.out.println("■ 신체검사 리스트 ■");
		System.out.println(" 이름     키  시력");
		System.out.println("------------------");
		for (int i = 0; i < x.length; i++)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
