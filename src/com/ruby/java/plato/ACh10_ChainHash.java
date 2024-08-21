package com.ruby.java.plato;

import java.util.Comparator;
import java.util.Scanner;

//	hash node가 student 객체일 때를 구현하는 과제
//	체인법에 의한 해시를 구현하는 코드
// 	체인법 : 동일한 해시 값을 가진 여러 데이터를 연결 리스트로 관리하는 방식
//	-> 해시 충돌을 해결하는 방법중 하나
class SimpleObject {
	static final int NO = 1;
	static final int NAME = 2;
	String no; // 회원번호
	String name; // 이름

	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + " 할 데이터를 입력하세요. " + sw);
		
		if ((sw & NO) == NO) {
			System.out.println("번호 : ");
			no = sc.next();
		}
		
		if((sw & NAME) == NAME) {
			System.out.println("이름 : ");
			name = sc.next();
		}
	}
	
//	회원번호로 순서를 매기는 Comparator
//	public -> 클래스 외부에서도 접근 가능
//	static -> 클래스 로딩 시점에 한번만 초기화 됨, 인스턴스화 없이 사용 가능
//	final -> 이 필드이 다른 값을 할당할 수 없음
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

//	private -> SimpleObject5 클래스 내부에서만 사용 가능
//	static -> 외부 클래스인 SimpleObject5의 인스턴스와 독립적으로 사용됨
	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
//			회원번호 비교
//			d1의 회원번호가 d2의 회원번호보다 크다면 1을 반환, 아니면 -1, 같다면 0 반환
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

//	이름으로 순서를 매기는 Comparator
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
//			이름 비교 (사전적 순서 비교)
			return d1.name.compareTo(d2.name);
		}
	}
}

//	체인법을 사용하여 해시 테이블을 구현
//	데이터 추가, 검색, 삭제, 덤프하는 메서드 제공
class ChainHash5 {
//	해시 테이블의 각 요소를 연결 리스트로 관리
//	연결 리스트의 각 노드 정의
//	각 노드는 data 가지고 다음 노드 가리키는 포인터 next 가짐
	class Node2 {
		private SimpleObject data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
	
		// --- 생성자(constructor) ---//
		public Node2(SimpleObject data) {
			this.data = data;
			this.next = next;
		}
	}
	
	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		this.size = capacity;
		table = new Node2[capacity];

	}

//	해시 값을 구하는 메서드
	public int hashValue(SimpleObject key) {
//		해시 코드를 계산하여 해시 테이블 인덱스를 반환
//		hashCode() 메서드는 문자열에 대한 고유의 정수값 생성
//		해시 코드 값을 해시 테이블의 크기로 나눈 나머지 값 구함
//		-> 해시 테이블의 유효한 인덱스 범위 내에 위치하게 됨 (0 ~ size - 1)
		return key.no.hashCode() % size;
	}
//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject st, Comparator<? super SimpleObject> c) {
//		검색할 요소의 해시값을 계산
		int hash = hashValue(st);
//		해당 해시 값을 가진 리스트의 첫 노드를 가져옴
		Node2 p = table[hash];
		
//		해시 테이블의 해당 인덱스에 저장된 연결 리스트 순회
//		p가 null이 되면 리스트의 끝에 도달한 것 
		while (p != null) {
			if (c.compare(p.data, st) == 0) {
//				검색 성공
				return 1;
			}
//			다음 노드로 이동
			p = p.next;
		}
//		검색 실패
		return 0;
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject st, Comparator<? super SimpleObject> c) {
		int hash = hashValue(st);
		Node2 p = table[hash];
		
		while (p != null) {
			if (c.compare(p.data, st) == 0) {
				return 1;
			}
			p = p.next;
		}
		
//		새로운 노드를 생성
		Node2 temp = new Node2(st);
//		새 노드를 리스트의 첫 부분에 추가
		temp.next = table[hash];
//		해시 테이블의 해당 인덱스에 새 노드를 성정
		table[hash] = temp;
//		추가 성공
		return 0;
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject st, Comparator<? super SimpleObject> c) {
		int hash = hashValue(st);
		Node2 p = table[hash];
//		이전 노드를 추적하기 위한 변수
		Node2 pp = null;
		
		while (p != null) {
			if (c.compare(p.data, st) == 0) {
				if (pp == null) {
//					첫 노드를 삭제하는 경우
					table[hash] = p.next;
				} else {
//					중간 또는 마지막 노드를 삭제하는 경우
					pp.next = p.next;
				}
//				삭제 성공
				return 1;
			}
			pp = p;
//			다음 노드로 이동
			p = p.next;
		}
//		삭제 실패
		return 0;
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			Node2 p = table[i];
			System.out.println("[" + i + "]");
			
			while (p != null) {
				System.out.println(" -> " + p.data);
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class ACh10_ChainHash {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject();
				data.scanData("삽입", SimpleObject.NO | SimpleObject.NAME);
				result = hash.add(data, SimpleObject.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject();
				data.scanData("삭제", SimpleObject.NO);
				result = hash.delete(data, SimpleObject.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject();
				data.scanData("검색", SimpleObject.NO);
				result = hash.search(data, SimpleObject.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}

}
