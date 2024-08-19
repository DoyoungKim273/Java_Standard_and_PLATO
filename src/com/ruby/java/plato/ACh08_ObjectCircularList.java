package com.ruby.java.plato;

/*
 * 정수 리스트 > 객체 리스트> 객체 원형 리스트 
 * 헤드 노드가 있는 원형 리스트, 헤드 노드가 없는 원형 리스트 구현
 * merge 구현: in-place 구현, 새로운 노드를 생성하여 합병 구현 
 * 원형 이중 리스트로 동일하게 적용
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject3 {
//	번호를 읽어들일까요? (번호를 읽어들일떄 이용하는 상수)
	static final int NO = 1;
//	이름을 읽어들일까요? (이름을 읽어들일때 이용하는 상수)
	static final int NAME = 2;

//	회원 번호
	private String no;
//	이름
	private String name;
//	유효기간 필드를 추가 -> 현재는 사용하지 않음
	String expire;

//	문자열 표현을 반환
	public String toString() {
		return "(" + no + ") " + name;
	}

//	기본 생성자
	public SimpleObject3(String no, String name) {
		this.no = no;
		this.name = name;
	}

//	head node를 만들때 사용
	public SimpleObject3() {
		this.no = null;
		this.name = null;
	}

//	데이터를 읽어들이는 메서드
	void scanData(String guide, int sw) {
//		Scanner 객체 생성 -> 사용자로부터 입력 받기위해 사용됨
		Scanner sc = new Scanner(System.in);
//		안내 메시지 출력 -> 사용자가 데이터 입력할 수 있도록 안내함
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

//		sw의 값과 NO 상수를 비트 AND 연산을 수행하여 번호를 입력받을지 결정
		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}

//		sw의 값과 NAME 상수를 비트 AND 연산을 수행하여 번호를 입력받을지 결정
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

//	회원번호로 순서를 매기는 Comparator
	public static final Comparator<SimpleObject3> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
//			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
//			null 체크 추가
			if (d1.no == null && d2.no == null) {
				return 0;
			}

			if (d1.no == null) {
				return -1;
			}

			if (d2.no == null) {
				return 1;
			}
			return d1.no.compareTo(d2.no);
		}
	}

//	이름으로 순서를 매기는 Comparator
	public static final Comparator<SimpleObject3> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			if (d1.name == null && d2.name == null) {
				return 0;
			}

			if (d1.name == null) {
				return -1;
			}

			if (d2.name == null) {
				return 1;
			}
			return d1.name.compareTo(d2.name);
		}
	}

	class Node3 {
		SimpleObject3 data;
		Node3 link;

		public Node3(SimpleObject3 element) {
			data = element;
			link = null;
		}
	}

	class CircularList {
		Node3 first;

		public CircularList() { // head node
			SimpleObject3 data = new SimpleObject3();
			first = new Node3(data);
			first.link = first;
		}

		/*
		 * static void sortData(Fruit []arr, Comparator<Fruit> cc) { for (int i = 0; i <
		 * arr.length; i++) { for (int j = i; j < arr.length; j++) if
		 * (cc.compare(arr[i], arr[j])> 0) swap(arr, i, j); } }
		 */

		public int Delete(SimpleObject3 element, Comparator<SimpleObject3> cc) // delete the element
		{
			Node3 p = first, q = null;

			do {
				if (cc.compare(p.data, element) == 0) {
					if (q == null) {
						first = p.link;
//					원형 리스트이므로 마지막 노드가 새로운 첫번째 노드를 가리키도록
						Node3 last = first;
						while (last.link != p) {
							last = last.link;
						}
					} else {
						q.link = p.link;
					}
					return 1;
				}
				q = p;
				p = p.link;
//		원형 리스트이므로 다시 첫 노드로 돌아올 때까지 반복
			} while (p != first);

			return -1;// 삭제할 대상이 없다.
		}

		public void Show() { // 전체 리스트를 순서대로 출력한다.
			Node3 p = first.link;

			do {
				System.out.println(p.data.toString());
				p = p.link;
			} while (p != first.link);

		}

		public void Add(SimpleObject3 element, Comparator<SimpleObject3> cc) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
		{
			Node3 newNode = new Node3(element);

			if (first == null || cc.compare(first.data, element) > 0) {
				newNode.link = first;
				first = newNode;

				Node3 last = first;
				while (last.link != first) {
					last = last.link;
				}
				last.link = newNode;
				return;
			}

			Node3 p = first.link;
			while (p.link != null && cc.compare(p.link.data, element) < 0) {
				p = p.link;
			}

			newNode.link = p.link;
			p.link = newNode;

		}

		public boolean Search(SimpleObject3 element, Comparator<SimpleObject3> cc) { // 전체 리스트를 순서대로 출력한다.
			Node3 p = first;
			while (p != null) {
				if (cc.compare(p.data, element) == 0) {
					return true;
				}
				p = p.link;
			}
			return false;
		}

		void Merge(CircularList b) {
			/*
			 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
			 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상급 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a =
			 * (2,3,4,5,8,9)가 되도록 구현하는 코드
			 */

			Node3 pa = this.first.link;
			Node3 pb = b.first.link;
			Node3 tail = null;

//		첫 노드를 결정
			if (SimpleObject3.NO_ORDER.compare(pa.data, pb.data) <= 0) {
				tail = pa;
				pa = pa.link;
			} else {
				tail = pb;
				pb = pb.link;
			}
			this.first.link = tail;

//		노드 연결
			while (pa != this.first && pb != b.first) {
				if (SimpleObject3.NO_ORDER.compare(pa.data, pb.data) <= 0) {
					tail.link = pa;
					pa = pa.link;
				} else {
					tail.link = pb;
					pb = pb.link;
				}
				tail = tail.link;
			}

//		남은 노드를 연결
			if (pa != this.first) {
				tail.link = pa;
				while (tail.link != this.first) {
					tail = tail.link;
				}
			} else {
				tail.link = pb;
				while (tail.link != b.first) {
					tail = tail.link;
				}
			}
//		마지막 노드가 새로운 첫 노드를 가리키게 설정
			tail.link = this.first;
		}
	}

	public class ACh08_ObjectCircularList {

		enum Menu {
			Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");

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
			Menu menu; // 메뉴
			CircularList l = new CircularList();
			CircularList l2 = new CircularList();
			Scanner sc = new Scanner(System.in);
			SimpleObject3 data;
			int count = 3;// l2 객체의 숫자를 3개로 한다

			do {
				switch (menu = SelectMenu()) {
				case Add: //
					data = new SimpleObject3();
					data.scanData("입력", 3);
					l.Add(data, SimpleObject3.NO_ORDER);
					break;
				case Delete: //
					data = new SimpleObject3();
					data.scanData("삭제", SimpleObject3.NO);
					int num = l.Delete(data, SimpleObject3.NO_ORDER);
					System.out.println("삭제된 데이터 성공은 " + num);
					break;
				case Show:
					l.Show();
					break;
				case Search: // 회원 번호 검색
					data = new SimpleObject3();
					data.scanData("탐색", SimpleObject3.NO);
					boolean result = l.Search(data, SimpleObject3.NO_ORDER);
					if (result)
						System.out.println("검색 성공 = " + result);
					else
						System.out.println("검색 실패 = " + result);
					break;
				case Merge:
					for (int i = 0; i < count; i++) {// 3개의 객체를 연속으로 입력받아 l2 객체를 만든다
						data = new SimpleObject3();
						data.scanData("병합", 3);
						l2.Add(data, SimpleObject3.NO_ORDER);
					}
					l.Merge(l2);
					break;
				case Exit: // 꼬리 노드 삭제
					break;
				}
			} while (menu != Menu.Exit);
		}
	}
}
