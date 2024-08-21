package com.ruby.java.plato;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class SimpleObject4 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject4() {
		no = null;
		name = null;
	}

	public SimpleObject4(String no, String name) {
		this.no = no;
		this.name = name;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

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
	public static final Comparator<SimpleObject4> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject4> {
		@Override
		public int compare(SimpleObject4 d1, SimpleObject4 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject4> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject4> {
		@Override
		public int compare(SimpleObject4 d1, SimpleObject4 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class ObjectStack4 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException() {
			super();
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

	private List<TreeNode4> data; // list를 사용: 배열은 크기를 2배로 늘리는 작업 필요
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public ObjectStack4(int capacity) {
		top = 0;
		this.capacity = capacity;
		// this.data = new T[capacity]; // 스택 본체용 배열을 생성
		try {
			data = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(TreeNode4 x) throws OverflowGenericStackException {
		System.out.println("top = " + top + "capacity = " + capacity);
		if (top >= capacity)
			throw new OverflowGenericStackException();
		top++;
		return data.add(x);

	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public TreeNode4 pop() throws EmptyGenericStackException {
		if (top < 0)
			throw new EmptyGenericStackException();
		return data.remove(--top);
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public TreeNode4 peek() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(TreeNode4 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

//정수를 저정하는 이진트리 만들기 실습
class ObjectQueue4 {
	private TreeNode4[] que;// 큐는 배열로 구현
	// private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

	// --- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

	// --- 생성자(constructor) ---//
	public ObjectQueue4(int maxlen) {
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = new TreeNode4[maxlen];
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 큐에 데이터를 인큐 ---//
	public int enque(TreeNode4 x) throws OverflowQueueException {
		if (num >= capacity)
			throw new OverflowQueueException("queue full"); // 큐가 가득 찼음
		que[rear++] = x;
		num++;

		return 1;
	}

	// --- 큐에서 데이터를 디큐 ---//
	public TreeNode4 deque() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		TreeNode4 x = que[front++];
		num--;

		return x;
	}

	// --- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public TreeNode4 peek() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		return que[front];
	}

	// --- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

	// --- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(TreeNode4 x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx].equals(x)) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return num;
	}

	// --- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

	// --- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

	// --- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[((i + front) % capacity)] + " ");
			System.out.println();
		}
	}
}

//	정수를 저정하는 이진트리 만들기 실습
// 	트리의 각 노드를 표현하는 클래스
//	각 노드는 simpleObject4 데이터를 포함, 좌측 자식과 우측 자식을 가리킴
//	노드 생성자를 통해 데이터를 설정, 자식 노드에 대한 참조를 초기화 함
class TreeNode4 {
//	부모 노드보다 작은 값을 가짐
	TreeNode4 LeftChild;
	SimpleObject4 data;
//	부모 노드 보다 큰 값을 가짐
	TreeNode4 RightChild;

//	기본 생성자
	public TreeNode4() {
		LeftChild = RightChild = null;
	}

//	매개변수가 있는 생성자
	TreeNode4(SimpleObject4 so) {
		data = so;
		LeftChild = RightChild = null;
	}
}

//	이진 검색 트리를 관리하는 클래스
//	트리의 루트 노드 보유, 트리의 다양한 작업 수행하는 메서드들을 제공
class Tree4 {
	TreeNode4 root;

	Tree4() {
		root = null;
	}

//	중위 순회 : 왼쪽 -> 노드 -> 오른쪽
//	주어진 노드에 대한 inorder traversal 방문시에 다음에 방문할 노드(successor)를 찾는 알고리즘
	TreeNode4 inorderSucc(TreeNode4 current) { 
//		temp는 현재 노드의 오른쪽 자식을 가리킴
		TreeNode4 temp = current.RightChild;
//		현재 노드의 오른쪽 자식이 존재하는 경우에만 후속 작업을 수행
		if (current.RightChild != null)
//			오른쪽 서브트리에서 가장 왼쪽에 있는 노드를 찾는 반복문
			while (temp.LeftChild != null) {
//				inOrder 순회에서 현재 노드의 후계자는 오른쪽 서브트리의 가장 왼쪽 노드
				temp = temp.LeftChild;
			}
//		temp는 후계자를 가리킴 (가장 왼쪽 노드 또는 오른쪽 자식 자체)
//		찾은 후계자를 반환
		return temp;
	}

//	주어진 노드의 parent node를 찾는 알고리즘
	TreeNode4 findParent(TreeNode4 current, Comparator<? super SimpleObject4> c) {
//		p -> 현재 탐색중인 노드, temp -> 부모 노드
		TreeNode4 p = root, temp = null;
		while (p != null) {
//			현재 노드(p)의 데이터와 주어진 노드(current)의 데이터가 같다면
//			p == q 이므로 부모노드(temp)를 반환
			if (c.compare(p.data, current.data) == 0) {
				return temp;
//			p < current 라면, p를 오른쪽 자식으로 이동
			} else if (c.compare(p.data, current.data) < 0) {
				temp = p;
				p = p.RightChild;
//			p > current 라면, p를 왼쪽 자식으로 이동
			} else {
				temp = p;
				p = p.LeftChild;
			}
		}
		return null;
	}

//	주어진 노드가 leaf node인지 검사 -> 트리의 끝에 위치, 왼쪽 / 오른쪽 자식도 없는 노드
	boolean isLeafNode(TreeNode4 current) {
		if (current.LeftChild == null && current.RightChild == null) {
			return true;
		} else {
			return false;
		}
	}

//	진입점 메서드
//	트리의 루트 노드를 인자로 받아 재귀적으로 트리를 순회하는 메서드를 호출
//	순회를 시작할 때 외부에서 간단하게 호출할 수 있는 진입점 제공
//	트리의 루트 노드를 직접 지정할 필요 없이, 트리의 전체를 순회하고 싶을때 이 메서드를 호출
	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

//	제귀 호출을 수행하면서 트리를 순회하는 메서드
//	트리의 특정 노드(CurrentNode)를 인자로 받음
//	-> 이 노드를 기준으로 왼쪽 자식, 오른쪽 자식 노드를 따라가며 재귀적으로 순회를 수행
	void inorder(TreeNode4 CurrentNode) {
//		중위 순회 : 왼쪽 -> 노드 -> 오른쪽
		if (CurrentNode != null) {
//			왼쪽 자식 노드를 재귀적으로 방문
			inorder(CurrentNode.LeftChild);
//			현재 노드의 데이터 출력
			System.out.print(" " + CurrentNode.data);
//			오른쪽 자식 노드를 재귀적으로 방문
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode4 CurrentNode) {
//		전위 순회 : 노드 -> 왼쪽 -> 오른쪽
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode4 CurrentNode) {
//		후위 순회 : 왼쪽 -> 오른쪽 -> 노드
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

//	이진 검색 트리의 규칙에 따라 노드를 추가하는 메서드
	public boolean add(SimpleObject4 obj, Comparator<? super SimpleObject4> c) {
		// inorder로 출력시에 정렬이 되도록 입력: binary search tree를 구현
		// left subtree < x < right subtree
		
//		탐색에 사용할 현재 노드를 가리키는 포인터
		TreeNode4 p = root;
//		현재 노드(p)의 부모 노드를 가리키는 포인터
		TreeNode4 q = null;
		
//		트리가 비어있을 경우
		if (p == null) {
//			루트 노드를 새로 생성하고 obj를 데이터로 설정
			root = new TreeNode4(obj);
//			노드 추가 성공
			return true;
		}
		
//		적절한 위치를 찾아 노드를 삽입
		while (p != null) {
//			현재 노드를 부모 노드로 저장
			q = p;
			if (c.compare(obj, p.data) < 0) {
				p = p.LeftChild;
			} else if (c.compare(obj, p.data) > 0){
				p = p.RightChild;
			} else {
//				이미 존재하는 값 -> 삽입하지 않음
				return false;
			}
		}
		
//		새 노드를 부모 노드의 왼쪽 또는 오른쪽에 삽입
		if (c.compare(obj, q.data) < 0) {
			q.LeftChild = new TreeNode4(obj);
		} else {
			q.RightChild = new TreeNode4(obj);
		}
		
		return true;

	}

//	주어진 데이터를 트리에서 삭제(노드를 찾고, 자식 노드의 유무에 따라 적절히 삭제)
	public boolean delete(SimpleObject4 obj, Comparator<? super SimpleObject4> c) {
		// 주어진 객체 obj를 포함한 노드를 찾아 삭제하는 알고리즘
		// 난이도: 최상급 중에서 최상급
//		p -> 삭제할 노드, q -> 부모 노드
		TreeNode4 p = root, q = null;
		
		while (p != null) {
			int comp = c.compare(obj, p.data);
			
			if (comp == 0) {
//				삭제할 노드를 찾음
				break;
			}
			q = p;
			
			if (comp < 0) {
				p = p.LeftChild;
			} else {
				p = p.RightChild;
			}
		}

		if (p == null) {
//			삭제할 노드가 없음
			return false;
		}
		
//		자식이 하나 또는 없는 경우
		if (p.LeftChild == null || p.RightChild == null) {
			TreeNode4 child = (p.LeftChild != null) ? p.LeftChild :p.RightChild;
			
			if (p == root) {
				root = child;
			} else if (p == q.LeftChild){
				q.LeftChild = child;
			} else {
				q.RightChild = child;
			}
			
//		자식이 둘 있는 경우
//		-> 트리의 구조를 유지하기 위해 후계자 노드 또는 전임 노드 중 하나로 대체하는 방법 사용
//		후계자 노드 -> 삭제하려는 노드의 오른쪽 서브트리에서 가장 왼쪽에 있는 노드
//		이 노드가 삭제하려는 노드의 자리에 와도 트리의 이진 검색 트리 속성을 유지할 수 있음
//		(왼쪽 서브트리의 모든 값은 노드보다 작고, 오른쪽 서브트리의 모든 값은 노드보다 크다 만족)	
		} else {
//			후계자 노드를 찾음
			TreeNode4 successor = inorderSucc(p);
//			후계자 노드의 데이터를 저장
			SimpleObject4 succData = successor.data;
//			후계자 노드를 트리에서 삭제
			delete(successor.data, c);		
//			후계자의 데이터를 삭제할 노드에 복사
//			-> 삭제할 노드의 데이터를 후계자의 데이터로 대체
			p.data = succData;
		}
		
		return true;
	}
	

//	주어진 데이터를 트리에서 검색하여 존재 여부를 반환
	boolean search(SimpleObject4 obj, Comparator<? super SimpleObject4> c) {
		// 주어진 객체 obj를 갖는 노드를 찾는 문제
		TreeNode4 p = root;

		while (p != null) {
			int comp = c.compare(obj, p.data);
			if (comp == 0) {
//				노드를 찾음
				return true;
			} else if (comp < 0){
				p = p.LeftChild;
			} else {
				p = p.RightChild;
			}
		}
//		노드를 찾지 못함
		return false;
	}

//	레벨 순서 순회 (너비 우선 순회) -> 각 레벨 별로 왼쪽에서 오른쪽으로 노드 방문
//	트리의 깊이를 고려하여 레벨별로 노드를 탐색
	void levelOrder()
	// root 부터 level별로 출력 : root는 level 1, level 2는 다음줄에 => 같은 level이면 같은 줄에 출력하게 한다
	{
//		큐 생성 (너비 우선 탐색 위해 사용)
		ObjectQueue4 q = new ObjectQueue4(20);
//		탐색을 시작할 현재 노드를 루트로 초기화
		TreeNode4 CurrentNode = root;
		
//		루트 노드가 null이 아니면
		if (CurrentNode != null) {
//			큐에 루트 노드를 추가
			q.enque(CurrentNode);
//			큐가 비어있지 않은 동안 반복
			while (!q.isEmpty()) {
				CurrentNode = q.deque();
				System.out.println(CurrentNode.data + " ");
				
				if (CurrentNode.LeftChild != null) {
					q.enque(CurrentNode.LeftChild);
				}
				
				if(CurrentNode.RightChild != null) {
					q.enque(CurrentNode.RightChild);
				}
			}
			System.out.println();
		}
	}

//	비재귀적 중위 순회 -> 재귀를 사용하지 않고, 스택을 이용하여 중위 순회 구현
	void NonrecInorder()// void Tree::inorder(TreeNode4 *CurrentNode)와 비교
	// stack을 이용하여 inorder traversal하는 알고리즘 구현
	{
		ObjectStack4 s = new ObjectStack4(20);
		TreeNode4 CurrentNode = root;
//		중단 조건 없이 반복
		while (true) {
//			현재 노드가 null이 아닐때까지 반복
			while (CurrentNode != null) {
//				현재 노드를 스택에 푸시
				s.push(CurrentNode);
//				왼쪽 자식으로 이동
				CurrentNode = CurrentNode.LeftChild;
			}
//			스택이 비어있지 않다면
			if (!s.isEmpty()) {
				try {
//					스택에서 노드를 꺼내 현재 노드로 설정
					CurrentNode = s.pop();
				} catch (ObjectStack4.EmptyGenericStackException e) {
					e.printStackTrace();
				}
//				현재 노드의 데이터 출력 후 오른쪽 자식으로 이동
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			} else
				break;
		}
	}
}

public class ACh09_ObjectTree {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), LevelorderPrint("레벨별출력"),
		StackInorderPrint("스택정렬출력"), PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

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
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Scanner sc2 = new Scanner(System.in);
		Tree4 t = new Tree4();
		Menu menu; // 메뉴
		String sno1, sname1;
		SimpleObject4 so;
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: //
				SimpleObject4[] sox = { new SimpleObject4("33", "ee"), new SimpleObject4("55", "tt"),
						new SimpleObject4("22", "ww"), new SimpleObject4("66", "yy"), new SimpleObject4("21", "wq") };
				for (SimpleObject4 soz : sox)
					t.add(soz, SimpleObject4.NO_ORDER);
				break;

			case Delete: // 임의 정수 삭제
				so = new SimpleObject4();
				so.scanData("삭제", SimpleObject4.NO);
				t.delete(so, SimpleObject4.NO_ORDER);

				break;

			case Search: // 노드 검색
				so = new SimpleObject4();
				so.scanData("검색", SimpleObject4.NO);
				result = t.search(so, SimpleObject4.NO_ORDER);
				if (!result)
					System.out.println("검색 값 = " + so + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + so + "데이터가 존재합니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case LevelorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.levelOrder();
				System.out.println();
				// t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint:// prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint:// postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
