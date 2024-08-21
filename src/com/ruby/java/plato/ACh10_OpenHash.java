package com.ruby.java.plato;

import java.util.Comparator;
import java.util.Scanner;

//	오픈 해시 (개방 주소법) -> 해시 테이블에서 충돌을 처리하는 방법 중 하나
//	해시 테이블에서 충돌이 발생했을 때, 데이터를 다른 위치에 저장하는 방식
//	충돌 발생 시 정해진 규칙에 따라 새로운 해시값(재해시값)을 계산
//	-> 그 위치에 데이터를 저장하거나 검색을 시도
//	오픈 해시는 해시 테이블 내에서 빈 공간을 찾기에 데이터 저장 시마다 새로운 노드를 생성하지는X
//	체인 해시는 충돌 발생 시 별도의 연결 리스트를 사용한다는 차이점 있음

class SimpleObject21 {
	static final int NO = 1;
	static final int NAME = 2;
	String sno; // 회원번호
	String sname; // 이름

	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + " 할 데이터를 입력하세요. " + sw);

		if ((sw & NO) == NO) {
			System.out.println("번호 : ");
			sno = sc.next();
		}

		if ((sw & NAME) == NAME) {
			System.out.println("이름 : ");
			sname = sc.next();
		}
	}

//	회원번호로 순서를 매기는 Comparator
//	public -> 클래스 외부에서도 접근 가능
//	static -> 클래스 로딩 시점에 한번만 초기화 됨, 인스턴스화 없이 사용 가능
//	final -> 이 필드이 다른 값을 할당할 수 없음
	public static final Comparator<SimpleObject21> NO_ORDER = new NoOrderComparator();

//	private -> SimpleObject5 클래스 내부에서만 사용 가능
//	static -> 외부 클래스인 SimpleObject5의 인스턴스와 독립적으로 사용됨
	private static class NoOrderComparator implements Comparator<SimpleObject21> {
		public int compare(SimpleObject21 d1, SimpleObject21 d2) {
//			회원번호 비교
//			d1의 회원번호가 d2의 회원번호보다 크다면 1을 반환, 아니면 -1, 같다면 0 반환
			return (d1.sno.compareTo(d2.sno) > 0) ? 1 : (d1.sno.compareTo(d2.sno) < 0) ? -1 : 0;
		}
	}

//	이름으로 순서를 매기는 Comparator
	public static final Comparator<SimpleObject21> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject21> {
		public int compare(SimpleObject21 d1, SimpleObject21 d2) {
//			이름 비교 (사전적 순서 비교)
			return d1.sname.compareTo(d2.sname);
		}
	}
}

//*
class OpenHash {
	// --- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	}; // {데이터 저장, 비어있음, 삭제 완료}

	// --- 버킷 ---//
	static class Bucket {
		private SimpleObject21 data; // 데이터
		private Status stat; // 상태

//			생성자
		public Bucket() {
//				초기 상태는 비어 있음
			stat = Status.EMPTY;
		}

		void set(SimpleObject21 data, Status stat) {
			this.data = data;
			this.stat = stat;
		}
	}

	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public OpenHash(int size) {
		this.size = size;
		table = new Bucket[size];

		for (int i = 0; i < size; i++) {
			table[i] = new Bucket();
		}
	}

	// --- 해시값을 구함 ---//
	public int hashValue(SimpleObject21 key) {
		return Integer.parseInt(key.sno) % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(SimpleObject21 key, Comparator<? super SimpleObject21> c) {
//			해시 값을 계산
		int hash = hashValue(key);
//			해당 해시 값을 가진 버킷을 가져옴
		Bucket p = table[hash];

//			버킷이 점유된 상태이고, 키가 일치하지 않으면 계속 탐색
		for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
			if (p.stat == Status.OCCUPIED && c.compare(p.data, key) == 0) {
//					키가 일치하는 버킷을 찾음
				return p;
			}
//				충돌이 발생하면 재해시
			hash = rehashValue(hash);
			p = table[hash];
		}
//			일치하는 버킷을 찾지 못함
		return null;

	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public SimpleObject21 search(SimpleObject21 key, Comparator<? super SimpleObject21> c) {
		Bucket p = searchNode(key, c);
		if (p != null && p.stat == Status.OCCUPIED) {
			return p.data;
		} else {
			return null;
		}
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject21 key, Comparator<? super SimpleObject21> c) {
		if (search(key, c) != null) {
//				이미 등록된 키 값
			return 1;
		}

		int hash = hashValue(key);
		Bucket p = table[hash];

		for (int i = 0; i < size; i++) {
			if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
//					데이터 추가
				p.set(key, Status.OCCUPIED);
				return 0;
			}
//				충돌 시 재해시
			hash = rehashValue(hash);
			p = table[hash];
		}
//			해시 테이블이 가득 참
		return 2;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject21 key, Comparator<? super SimpleObject21> c) {
		Bucket p = searchNode(key, c);
		if (p == null || p.stat != Status.OCCUPIED) {
//				삭제할 데이터가 없음
			return 1;
		}
//			데이터 삭제
		p.stat = Status.DELETED;
//			삭제 성공
		return 0;
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			System.out.println("해시 테이블 인덱스 : " + i);

			if (table[i].stat == Status.OCCUPIED) {
				System.out.println(table[i].data.sno + " ( " + table[i].data.sname + " ) ");
			} else if (table[i].stat == Status.EMPTY) {
				System.out.println("해시 테이블이 비었습니다.");
			} else if (table[i].stat == Status.DELETED) {
				System.out.println("해시 테이블이 삭제되었습니다.");
			}
		}
	}
}

public class ACh10_OpenHash {

	static Scanner stdIn = new Scanner(System.in);

//--- 메뉴 열거형 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

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

//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴

		SimpleObject21 temp; // 읽어 들일 데이터
		int result;
		OpenHash hash = new OpenHash(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				temp = new SimpleObject21();
				temp.scanData("추가", SimpleObject21.NO | SimpleObject21.NAME);
				int k = hash.add(temp, SimpleObject21.NO_ORDER);
				switch (k) {
				case 1:
					System.out.println("그 키값은 이미 등록되어 있습니다.");
					break;
				case 2:
					System.out.println("해시 테이블이 가득 찼습니다.");
					break;
				case 0:
				}
				break;

			case REMOVE: // 삭제
				temp = new SimpleObject21();
				temp.scanData("삭제", SimpleObject21.NO);
				result = hash.remove(temp, SimpleObject21.NO_ORDER);
				if (result == 0)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;

			case SEARCH: // 검색
				temp = new SimpleObject21();
				temp.scanData("검색", SimpleObject21.NO);
				SimpleObject21 t = hash.search(temp, SimpleObject21.NO_ORDER);
				if (t != null)
					System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
