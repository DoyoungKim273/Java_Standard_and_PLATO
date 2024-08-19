package com.ruby.java.plato;

import java.util.Random;
import java.util.Scanner;

//	난수로 생성된 데이터를 삽입하고 가장 큰 값을 삭제하면서 정렬된 배열을 얻음
//	힙 정렬은 제자리에서(in-place) 정렬을 수행하며 추가적인 메모리 공간 필요로 하지 않는 특성 있음
//	Insert 와 DeleteMax 메서드를 정의하여 MaxHeap의 핵심 동작을 설명
interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();
}

//	Heap 인터페이스를 구현, 실제로 MaxHeap을 관리
class Heap implements MaxHeap {
//	최대 힙의 크기
	final int heapSize = 100;
//	힙 배열
	private int[] heap;
//	현재 힙에 있는 요소의 개수 (MaxHeap의 현재 입력된 element 개수)
	private int n; 
//	허용 가능한 최대 힙 크기 (Maximum allowable size of MaxHeap)
	private int MaxSize; 

	public Heap(int sz) {
		MaxSize = sz;
//		초기에는 힙에 요소가 없음
		n = 0;
//		0번 인덱스는 사용하지 않음 (힙 배열은 0번 인덱스 사용하지 않음)
		heap = new int[MaxSize + 1];

	}

//	heap 배열을 출력, 배열 인덱스와 heap[]의 값을 출력
	public void display() {
		System.out.println("Heap 배열 상태 : ");
		for (int i = 1; i <= n; i++) {
			System.out.println("Index " + i +" : " + heap[i]);
		}
	}

	@Override
//	max heap이 되도록 insert 
//	삽입 후 complete binary tree가 유지 되어야 함
//	삽입 후 힙 속성이 유지되도록 부모와 비교하여 위치 결정함
	public void Insert(int x) { 
		int i;
		if (n == MaxSize) {
			HeapFull();
			return;
		}
		n++;
		
//		부모 노드와 비교하여 큰 값을 위로 올림
		for (i = n; i > 1 && x > heap[i / 2]; i /= 2) {
			heap[i] = heap[i / 2];
		}
//		최종 위치에 삽입
		heap[i] = x;
	}

	@Override
//	heap에서 가장 큰 값을 삭제하여 리턴, 나머지 요소들을 정렬
//	루트에서 최대값을 삭제하고 마지막 요소를 루트로 이동시킨 후 재정렬
	public int DeleteMax() { 
		int x, i, j;
		if (n == 0) {
			HeapEmpty();
			int elm = 0;
			return elm;
		}
//		root에서 가장 큰 값을 가져옴
		x = heap[1];
//		마지막 요소를 가져와서 힙 크기를 줄임
		int last = heap[n--];
		for (i = 1, j = 2; j <= n; i = j, j *= 2) {
//			더 큰 자식을 선택
			if (j < n && heap[j] < heap[j + 1]) {
				j++;
			}
//			올바른 위치를 찾았으면 루프 종료
			if (last >= heap[j]) {
				break;
			}
//			자식을 부모 위치로 올림
			heap[i] = heap[j];
		}
//		마지막 요소를 최종 위치에 삽입
		heap[i] = last;
//		삭제된 최대값 반환
		return x;
	}

//	힙이 비어있는 경우
	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

//	힙이 가득 찬 경우
	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

//	사용자가 MaxHeap을 사용하여 데이터 정렬할 수 있도록 함
//	난수를 생성하고 힙에 데이터를 삽입, 정렬된 결과를 출력하는 기능 제공
public class ACh06_HeapSorting {
//	배열 데이터를 출력하는 함수
	static void showData(int[] d) {
		System.out.println("현재 데이터 : ");
		for (int i = 1; i < d.length; i++) {
			System.out.println(d[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
//		난수 생성 개수 
		final int count = 10;
//		x[0]은 사용하지 않으므로 11개 정수 배열을 생성
		int[] x = new int[count + 1];
//		heap을 사용하여 deleted 정수를 배열 sorted[]에 보관 후 출력
		int[] sorted = new int[count];

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			
//			난수를 생성하여 배열 x에 넣음 ->  heap에 insert
			case 1: 
				for (int i = 1; i <= count; i++) {
					x[i] = rnd.nextInt(100);
					heap.Insert(x[i]);
				}
				showData(x);
				break;
				
//				heap 트리구조를 배열 인덱스를 사용하여 출력
			case 2: 
				heap.display();
				break;
				
//			heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣음
//			배열 sorted[]를 출력하면 정렬 결과를 얻음
			case 3: 
				for (int i = 0; i < count; i++) {
//					최대값을 삭제하여 sorted 배열에 저장
					sorted[i] = heap.DeleteMax();
				}
				System.out.println("힙 정렬 결과 : ");
				for (int i = 0; i < count; i++) {
//					정렬된 배열을 출력
					System.out.println(sorted[i] + " ");
				}
				System.out.println();
				break;
				
			case 4:
				return;
			}
			
		} while (select < 5);

		return;
	}
}
