package com.ruby.java.plato;

// Node3 클래스 정의 (연결 리스트의 노드)
class Node3 {
//	노드의 데이터
	int data;
//	다음 노드를 가리키는 링크
	Node3 link;

//	생성자
	public Node3(int data) {
		this.data = data;
		link = null;
	}
}

// LinkedList3 클래스 정의 (연결 리스트 구현)
class LinkedList3 {
//	첫번째 노드를 가리키는 포인터
	Node3 first;

//	리스트의 끝에 데이터를 추가하는 메서드
	void append(int data) {
//		새로운 노드 생성
		Node3 newNode = new Node3(data);
		if (first == null) {
//			리스트가 비어있으면 첫번째 노드로 설정
			first = newNode;
		} else {
			Node3 p = first;
			while (p.link != null) {
//				아니면 마지막 노드까지 이동
				p = p.link;
			}
//			마지막 노드의 링크에 새 노드 연결
			p.link = newNode;
		}
	}

//	리스트를 출력하는 메서드
	void showList() {
		System.out.println();
		System.out.println();
		Node3 p = first;
//		리스트에 데이터가 있어야 출력
		while (p != null) {
//			노드의 데이터 출력
			System.out.print(p.data + " ");
//			다음 노드로 이동
			p = p.link;
		}
		System.out.println();
	}

//	리스트에 데이터를 삽입하는 메서드 (정렬된 순서 유지)
	void insert(int data) {
//		새로운 노드 생성
		Node3 newNode = new Node3(data);
		Node3 p = first, q = null;
		while (p != null & p.data < data) {
//			p는 현재 노드 -- 삽입할 위치 찾기 위해 리스트 순회하는 동안 이동
//			q는 p의 바로 이전 노드 -- p가 삽입할 위치를 찾으면 q는 p의 이전 노드로 유지됨
			q = p;
//			삽입 위치를 찾기 위해 이동 (p는 다음 노드로 이동)
			p = p.link;
		}
		if (q == null) {
			newNode.link = first;
//			데이터 없으면 맨 앞에 삽입
			first = newNode;
		} else {
//			아니면 중간에 삽입
			newNode.link = p;
			q.link = newNode;
		}
	}
}

public class Ch08_LinkedListandNode {
//	리스트를 초기화 하는 메서드
	static int getList(int[] data) {
		int count = 0;
		int mid = data.length / 2;
		for (int i = 0; i <= mid; i++) {
//			0, 5, 10, 15, 20 설정
			data[i] = i * 5;
			count++;
		}
//		요소의 개수 반환
		return count;
	}

//	배열로 표현된 리스트를 출력하는 메서드
	static void showList(int[] data) {
		System.out.println();
		for (int i = 0; i < data.length; i++) {
//			인덱스 출력
			System.out.print(" " + i + " "); 
		}
		System.out.println();
		for (int i = 0; i < data.length; i++) {
			if (data[i] < 10)
				System.out.print(" ");
//			데이터 출력
			System.out.print(data[i] + " ");
		}

	}

//	배열로 표현된 리스트에 데이터를 삽입하는 메서드
	static int insertList(int[] data, int count, int x) {
		if (count >= data.length) {
//			배열이 가득 차면 삽입을 하지 않음
			System.out.println("리스트가 가득 참");
			return count;
		}
		int i = 0;
		for(i = count - 1; i >= 0 && data[i] > x; i--) {
//			삽입 위치를 찾기 위해 이동
			data[i + 1] = data[i];
		}
//		새로운 요소 삽입
		data[i + 1] = x;
//		요소의 개수 증가
		return count + 1;

	}

	public static void main(String[] args) {
//		배열로 리스트 구현
		int[] list = new int[10];
//		요소의 개수
		int count = 0;
		System.out.println("배열로 리스트::");
//		리스트 초기화
		count = getList(list);
//		리스트 출력
		showList(list);
//		리스트에 3 삽입 후 리스트 출력
		count = insertList(list, count, 3);
		showList(list);
//		리스트에 7 삽입 후 리스트 출력
		count = insertList(list, count, 7);
		showList(list);

//		연결 리스트 구현
		LinkedList3 ll = new LinkedList3();
//		append 메서드를 호출하여 해당 숫자를 연결 리스트에 추가
		ll.append(5);
		ll.append(10);
		ll.append(15);
		ll.append(20);
		ll.append(25);
		ll.showList();
//		연결 리스트에 3 삽입 후 리스트 출력
		ll.insert(3);
		ll.showList();
//		연결 리스트에 7 삽입 후 리스트 출력
		ll.insert(7);
		ll.showList();
	}
}
