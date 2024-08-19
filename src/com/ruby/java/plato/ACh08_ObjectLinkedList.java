package com.ruby.java.plato;

/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject5 {
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
	public SimpleObject5() {
		no = null;
		name = null;
	}

//	데이터를 읽어들이는 메서드
	void scanData(String guide, int sw) {// sw가 3이면 11 비트 연산 > NO, NAME을 모두 입력받는다
//		Scanner 객체 생성 -> 사용자로부터 입력 받기위해 사용됨
		Scanner sc = new Scanner(System.in);
//		안내 메시지 출력 -> 사용자가 데이터 입력할 수 있도록 안내함
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

//		sw의 값과 NO 상수를 비트 AND 연산을 수행하여 번호를 입력받을지 결정
//		sw와 NO의 & 연산 결과가 NO와 같다면 -> sw의 첫번째 비트가 1이라면 실행됨
		if ((sw & NO) == NO) { // & 는 bit 연산자임 sw가 3이면 &는 비트 연산이므로 결과는 1
			System.out.print("번호: ");
//			번호를 입력받고 이를 no 필드에 저장함
			no = sc.next();
		}
		
//		sw의 값과 NAME 상수를 비트 AND 연산을 수행하여 번호를 입력받을지 결정
//		sw와 NAME의 & 연산 결과가 NAME과 같다면 -> sw의 첫번째 비트가 1이라면 실행됨
		if ((sw & NAME) == NAME) {// sw가 3이고 NAME과 비트 & 연산하면 결과는 2
			System.out.print("이름: ");
			name = sc.next();
		}
	}

//	회원번호로 순서를 매기는 Comparator
//	public -> 클래스 외부에서도 접근 가능
//	static -> 클래스 로딩 시점에 한번만 초기화 됨, 인스턴스화 없이 사용 가능
//	final -> 이 필드이 다른 값을 할당할 수 없음
	public static final Comparator<SimpleObject5> NO_ORDER = new NoOrderComparator();

//	private -> SimpleObject5 클래스 내부에서만 사용 가능
//	static -> 외부 클래스인 SimpleObject5의 인스턴스와 독립적으로 사용됨
	private static class NoOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
//			회원번호 비교
//			d1의 회원번호가 d2의 회원번호보다 크다면 1을 반환, 아니면 -1, 같다면 0 반환
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

//	이름으로 순서를 매기는 Comparator
	public static final Comparator<SimpleObject5> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject5> {
		public int compare(SimpleObject5 d1, SimpleObject5 d2) {
//			이름 비교 (사전적 순서 비교)
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node2 {
//	SimpleObject5 데이터를 저장
	SimpleObject5 data;
//	다음 노드를 가르키는 링크
	Node2 link;

//	생성자
	public Node2(SimpleObject5 element) {
		data = element;
		link = null;
	}
}

class LinkedList2 {
//	리스트의 첫번째 노드 가르킴
	Node2 first;

//	생성자 -> 첫번째 노드를 null로 초기화
	public LinkedList2() {
		first = null;
	}

//	전달된 element를 찾을 때 Comparator 객체를 사용함
	public int Delete(SimpleObject5 element, Comparator<SimpleObject5> cc)	{
//		p -> 리스트를 순회하기 위해 현재 노드를 가리키는 포인터
//		q -> p의 이전 노드를 가리키는 포인터, 첫번째 노드를 삭제할 경우 q는 null로 유지됨
		Node2 p = first, q = null;
//		리스트의 끝까지 노드를 순회
		while(p != null) {
//			비교자가 동일한지 확인 (현재 노드의 데이터와 삭제하고자 하는 element를 비교)
			if(cc.compare(p.data, element) == 0) {
//				첫번째 노드를 삭제하는 경우 (p가 첫번째 노드라는 의미)
				if (q == null) {
					first = p.link;
				} else {
//					중간이나 마지막 노드를 삭제하는 경우
					q.link = p.link;
				}
//				삭제 성공 시 1 반환
				return 1;
			}
//			이전 노드를 현재 노드로 업데이트 
//			-> 다음 반복에서 q가 p의 이전 노드를 가리키도록 하기 위함
			q = p;
//			다음 노드로 이동
			p = p.link;
		}
//		삭제할 대상이 없는 경우
		return -1;
	}

//	전체 리스트를 순서대로 출력하는 메서드
	public void Show() { 
		Node2 p = first;
//		리스트의 끝까지 반복
		while (p != null) {
//			각 노드의 데이터를 출력
			System.out.println(p.data.toString());
//			연결 리스트의 다음 노드로 이동
			p = p.link;
		}
	}

//	임의의 값을 삽입할 때 리스트가 오름차순으로 정렬되도록 하는 메서드
//	새로운 SimpleObject5 객체를 연결 리스트에 오름차순으로 삽입하는 기능
	public void Add(SimpleObject5 element, Comparator<SimpleObject5> cc) {
		Node2 newNode = new Node2(element);
//		빈 리스트에 insert 하는 경우
//		리스트가 비어있는 경우 '또는'
//		새로운 element가 현재 첫번째 노드 데이터 보다 작은 경우(양수가 반환됨)
		if (first == null || cc.compare(first.data, element) > 0) {
//			새 노드의 link를 첫번째 노드 (first)로 설정
			newNode.link = first;
//			첫번째 위치에 삽입 (새 노드가 리스트의 첫번째 노드가 됨)
			first = newNode;
//			메서드 종료
			return;
		} 

//		삽입 위치를 찾기 위해 리스트를 순회하는데 사용됨
		Node2 p = first;
		while (p.link != null && cc.compare(p.link.data, element) < 0) {
//			삽입할 위치를 찾음
			p = p.link;
		}
	
//		노드를 삽입
		newNode.link = p.link;
		p.link = newNode;
	}

//	현재 리스트에서 element를 검색하는 메서드
//	연결 리스트의 첫번째 노드부터 시작하여 주어진 element와 동일한 데이터를 가진 노드를 찾음
//	검색을 위해 cc 사용
	public boolean Search(SimpleObject5 element, Comparator<SimpleObject5> cc) {
		Node2 p = first;
		while (p != null) {
			if (cc.compare(p.data, element) == 0) {
//				검색 성공
				return true;
			}
//			다음 노드로 이동
			p = p.link;
		}
//		검색 실패 (리스트의 끝까지 순회하면서 해당 노드 찾지 못하면 검색 실패로 간주)
		return false;
	}

//	두 연결 리스트를 합병하는 메서드 -> 현재 병합 후 출력이 제대로 이루어지지 않음
	void Merge(LinkedList2 b) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 in-place 방식으로 합병/이것은 새로운 노드를 만들지
		 * 않고 합병하는 알고리즘 구현 난이도 등급: 최상급 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 a =
		 * (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
		Node2 pa = this.first;
		Node2 pb = b.first;
//		합병된 리스트의 꼬리 노드를 가리킬 변수
		Node2 tail = null;
		
//		첫번째 리스트가 비어있으면 두번째 리스트가 합병 결과가 됨
		if (pa == null) {
			this.first = pb;
//		두번째 리스트가 비어있으면 첫번째 리스트가 합병 결과가 됨
		} else if (pb == null){
			return;
		}
		
//		pa와 pb가 가리키는 노드의 데이터 비교, 어느 노드가 더 작은지 확인
//		NO_ORDER 비교기를 사용하여 회원번호 순으로 비교
		if (SimpleObject5.NO_ORDER.compare(pa.data, pb.data) <= 0) {
//			더 작은 값을 가지는 노드를 합병된 리스트의 첫번째 노드로 설정
			this.first = pa;
//			선택된 노드의 다음 노드 가리키도록 포인터 이동
			pa = pa.link;
		} else {
			this.first = pb;
			pb = pb.link;
		}
//		합병된 리스트의 첫번째 노드를 tail로 설정
//		이후 tail은 계속해서 리스트의 마지막 노드를 가리키게 됨
		tail = this.first;
		
//		pa와 pb중 하나가 null이 될 때 까지 반복
//		두 리스트 중 하나의 끝에 도달할때까지 실행됨
		while (pa != null && pb != null) {
//			pa와 pb의 데이터 비교하여 더 작은 값을 가지는 노드를 tail.link에 연결함
			if (SimpleObject5.NO_ORDER.compare(pa.data, pb.data) <= 0) {
				tail.link = pa;
				pa = pa.link;
			} else {
				tail.link = pb;
				pb = pb.link;
			}
			tail = tail.link;
		}
		
//		첫번째 리스트가 남아있으면 추가
		if (pa != null) {
			tail.link = pa;
//		두번째 리스트가 남아있으면 추가
		} else {
			tail.link = pb;
		}
		
//		마지막 노드의 link를 null로 설정 -> 리스트의 끝을 표시함ㄴ
		if (tail != null) {
			tail.link = null;
		}
	}
}

public class ACh08_ObjectLinkedList {
//	열거형 enum -> 여러 메뉴 옵션 정의
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("합병"), Exit("종료");
		
//		표시할 문자열 -> 각 메뉴 항목과 관련된 메시지를 저장하는 필드
		private final String message; 

//		순서가 idx번째인 열거를 반환
		static Menu MenuAt(int idx) { 
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

//		생성자
		Menu(String string) { 
			message = string;
		}

//		표시할 문자열을 반환
		String getMessage() { 
			return message;
		}
	}

//	메뉴 선택 받는 메서드
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		
//		올바른 메뉴 항목이 선택될 때까지 반복
		do {
//			모든 메뉴 항목을 반복, 메뉴 목록을출력
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
//			사용자로부터 메뉴 선택을 정수로 입력 받음
			key = sc.nextInt();
			
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
//		사용자가 선택한 메뉴 항목을 반환
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu;
//		두 개의 연결 리스트 객체 l, l2를 생성
		LinkedList2 l = new LinkedList2();
		LinkedList2 l2 = new LinkedList2();
		Scanner sc = new Scanner(System.in);
		int count = 3; // 3개의 객체 입력 개수
		SimpleObject5 data;
		
//		메뉴를 선택하고 각 메뉴 항목에 따라 적절한 작업을 수행
		do {
			switch (menu = SelectMenu()) {
			
//			데이터를 입력 받아 l 리스트에 추가
			case Add:
				data = new SimpleObject5();
				data.scanData("입력", 3);
//				회원번호 순서대로 정렬 입력
				l.Add(data, SimpleObject5.NO_ORDER);
				break;
			
//			삭제할 데이터를 입력받고 l 리스트에서 해당 데이터를 삭제
			case Delete:
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
//				회원번호 조건 비교하여 삭제
				int num = l.Delete(data, SimpleObject5.NO_ORDER);
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
				
//			l 리스트의 모든 데이터를 출력
			case Show:
				l.Show();
				break;
				
//			검색할 데이터를 입력받고, l 리스트에서 해당 데이터를 검색, 검색 결과를 출력
			case Search: 
				data = new SimpleObject5();
				data.scanData("탐색", SimpleObject5.NO);
				boolean result = l.Search(data, SimpleObject5.NO_ORDER);
				if (result)
					System.out.println("검색 성공 = " + result);
				else
					System.out.println("검색 실패 = " + result);
				break;
				
			case Merge:// 난이도 상
//				3개의 객체를 연속으로 입력받아 l2 객체를 만듦 -> 이를 l 리스트와 병합
				for (int i = 0; i < count; i++) {
					data = new SimpleObject5();
					data.scanData("병합", 3);
					l2.Add(data, SimpleObject5.NO_ORDER);
				}
				l.Merge(l2);
				break;
				
//			꼬리 노드 삭제, 프로그램을 종료
			case Exit: 
				break;
			}
			
		} while (menu != Menu.Exit);
	}
}
