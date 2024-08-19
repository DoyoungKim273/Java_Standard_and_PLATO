package com.ruby.java.plato;

/*
 * 정수 리스트 > 객체 리스트 >
 * * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
import java.util.Comparator;
import java.util.Scanner;

//	객체 이중 리스트 : 각 노드가 2개의 포인터(링크)를 가지는 리스트
//	하나는 다음 노드를 가리키고 다른 하나는 이전 노드를 가리킴
//	이중 연결 리스트는 단일 연결 리스트에 비해 양방향 탐색 가능, 삽입과 삭제 유연하다는 장점
class SimpleObject2 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	
	String no; // 회원번호
	String name; // 이름
	String expire;// 유효기간 필드를 추가

//	객체 생성에서의 유연성 제공 위해 생성자 2개 정의
//	매개변수를 받는 생성자
	public SimpleObject2(String sno, String sname) {
		this.no = sno;
		this.name = sname;
	}

//	기본 생성자 -> 객체를 생성한 후에 나중에 no와 name을 설정할 수 있는 상황에서 유용
//	사용자 입력을 받아서 객체의 값을 나중에 설정하고 싶을 때 사용
	public SimpleObject2() {
		this.no = null;
		this.name = null;
	}

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

//		sw 변수는 scanData 메서드가 어떤 데이터를 읽어들일지 결정하는 정수 변수
//		정수 변수가 여러 옵션을 동시에 나타낼 수 있도록 비트 연산자 사용
//		-> 플래그 비트 마스크 (야러개의 옵션을 하나의 정수 변수로 표현 가능하게 함)
		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject2> {
		@Override
		public int compare(SimpleObject2 d1, SimpleObject2 d2) {
			return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
		}
	}
}

//	이중 연결 리스트의 각 노드 나타냄
class Node4 {
	SimpleObject2 data; // 데이터
	Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
	Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

	// --- 생성자(constructor) ---//
	Node4(SimpleObject2 so) {
		this.data = so;
		llink = rlink = this;
	}

	Node4() { // head node로 사용
		this.data = null;
//		더미 노드로 설정
		llink = rlink = this;
	}

	Node4(String sno, String sname) {
		data = new SimpleObject2(sno, sname);
		llink = rlink = this;
	}

//	두 노드를 비교하는 메서드
	public int compareNode(Node4 n2) {
		SimpleObject2 so1 = this.data;
		if (SimpleObject2.NO_ORDER.compare(so1, n2.data) < 0)
			return -1;
		else if (SimpleObject2.NO_ORDER.compare(so1, n2.data) > 0)
			return 1;
		else
			return 0;
	}
}

//	이중 연결 리스트 전체를 관리함
class DoubledLinkedList2 {
//	더미노드 -> 리스트의 끝과 처음을 연결하는 특별한 노드, 편리한 리스트 조작을 위해 사용
	private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

	// --- 생성자(constructor) ---//
	public DoubledLinkedList2() {
		first = new Node4(); // dummy(first) 노드를 생성

	}

	// --- 리스트가 비어있는가? ---//
	public boolean isEmpty() {
		return first.rlink == first;
	}

	// --- 노드를 검색 ---//
	public boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 ptr = first.rlink; // 현재 스캔 중인 노드
		while (ptr != first) {
			if (c.compare(ptr.data, obj) == 0) {
//				검색 성공
				return true;
			}
//			다음 노드로 이동
			ptr = ptr.rlink;
		}
//		검색 실패
		return false;
	}

	// --- 전체 노드 표시 ---//
	public void show() {
		Node4 ptr = first.rlink; // 더미 노드의 뒤쪽 노드
		while (ptr != first) {
			System.out.println(ptr.data.toString());
			ptr = ptr.rlink;
		}

	}

	// --- 올림차순으로 정렬이 되도록 insert ---//
	public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 temp = new Node4(obj);
//		리스트의 첫번째 실질적인 노드를 가리킴
		Node4 ptr = first;

//		삽입할 위치를 찾음
		while (ptr != first && c.compare(ptr.data, obj) < 0) {
			ptr = ptr.rlink;
		}

//		새 노드를 삽입할 위치를 찾았으므로, 노드를 삽입
		temp.llink = ptr.llink;
//		새 노드의 오른쪽 링크를 현재 노드로 설정
		temp.rlink = ptr;
//		현재 노드의 왼쪽 노드의 오른쪽 링크를 새 노드로 설정
		ptr.llink.rlink = temp;
//		현재 노드의 왼쪽 링크를 새 노드로 설정
		ptr.llink = temp;
//		-> 데이터 삽입 후에는 llink와 rlink 포인터를 업데이트, 이전 및 다음 노드와 연결함

	}

	// --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
	public void delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
		Node4 ptr = first.rlink;

		while (ptr != first) {
			if (c.compare(ptr.data, obj) == 0) {
//				현재 노드의 왼쪽 노드가 현재 노드의 오른쪽 노드를 가리키게 함
				ptr.llink.rlink = ptr.rlink;
//				현재 노드의 오른쪽 노드가 현재 노드의 왼쪽 노드를 가리키게 함
				ptr.rlink.llink = ptr.llink;
//				-> 삭제할 노드의 이전 노드와 다음 노드가 서로를 가리키도록 포인터를 업데이트 함
//				삭제 후에 메서드 종료
				return;
			}
			ptr = ptr.rlink;
		}
	}

	public DoubledLinkedList2 merge(DoubledLinkedList2 lst2) {
		// l3 = l1.merge(l2); 실행하도록 리턴 값이 리스트임
		// l.add(객체)를 사용하여 구현
		// 기존 리스트의 노드를 변경하지 않고 새로운 리스트의 노드들을 생성하여 구현
		DoubledLinkedList2 lst3 = new DoubledLinkedList2();
		Node4 ai = this.first.rlink, bi = lst2.first.rlink;

		while (ai != this.first && bi != lst2.first) {
			if (SimpleObject2.NO_ORDER.compare(ai.data, bi.data) <= 0) {
				lst3.add(ai.data, SimpleObject2.NO_ORDER);
				ai = ai.rlink;
			} else {
				lst3.add(bi.data, SimpleObject2.NO_ORDER);
				bi = bi.rlink;
			}
		}

		while (ai != this.first) {
			lst3.add(ai.data, SimpleObject2.NO_ORDER);
			ai = ai.rlink;
		}

		while (bi != lst2.first) {
			lst3.add(bi.data, SimpleObject2.NO_ORDER);
			bi = bi.rlink;
		}
		return lst3;
	}
}

public class ACh08_DoubleLinkedList {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("병합"), Exit("종료");

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
		Scanner sc1 = new Scanner(System.in);
		int key;
		
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc1.nextInt();
			
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		Scanner sc2 = new Scanner(System.in);
		DoubledLinkedList2 lst1 = new DoubledLinkedList2(), lst2 = new DoubledLinkedList2(),
				lst3 = new DoubledLinkedList2();
		String sno1 = null, sname1 = null;
		SimpleObject2 so;
		boolean result = false;
		int count = 3;
		
		do {
			switch (menu = SelectMenu()) {
			
			case Add: // 객체들의 올림차순으로 정렬되도록 구현
				so = new SimpleObject2();
				so.scanData("입력", 3);
				lst1.add(so, SimpleObject2.NO_ORDER);
				break;
				
			case Delete: // 임의 객체를 삭제
				so = new SimpleObject2();
				so.scanData("삭제", SimpleObject2.NO);
				lst1.delete(so, SimpleObject2.NO_ORDER);
				break;
				
			case Show: // 리스트 전체를 출력
				lst1.show();
				break;
				
			case Search: // 회원 번호 검색
				so = new SimpleObject2();
				so.scanData("탐색", SimpleObject2.NO);
				result = lst1.search(so, SimpleObject2.NO_ORDER);
				
				if (!result) {
					System.out.println("검색 값 = " + so + "데이터가 없습니다.");
				} else {
					System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
				}
				break;
				
			case Merge:// 기존 2개의 리스트를 합병하여 새로운 리스트를 생성(새로운 노드를 생성하여 추가)
				for (int i = 0; i < count; i++) {
					so = new SimpleObject2();
					so.scanData("입력", 3);
					lst2.add(so, SimpleObject2.NO_ORDER);
				}
				
				lst3 = lst1.merge(lst2);
				System.out.println("list1: ");
				lst1.show();
				System.out.println("list2: ");
				lst2.show();
				System.out.println("list3: ");
				lst3.show();
				break;
				
			case Exit: //
				break;
			}
			
		} while (menu != Menu.Exit);
	}

}
