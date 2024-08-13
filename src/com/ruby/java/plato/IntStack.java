package com.ruby.java.plato;

public class IntStack {
//	스택용 배열 : 푸시된 데이터를 저장, 인덱스 0인 요소가 스택의 바닥
	private int[] stk;
//	스택 용량 : 스택에 쌓을 수 있는 최대 데이터 수
	private int capacity;
//	스택 포인터 : 스택에 쌓여 있는 데이터 수를 나타내는 필드
	private int ptr;

//	실행 시 예외 : 스택이 비어있음
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}

//	실행 시 예외 : 스택이 가득 참
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

//	생성자 : 스택용 배열 본체를 생성하는 등 준비 작업을 함
	public IntStack(int maxlen) {
//		생성할 때 스택은 비어있으므로 스택 포인터 ptr 값은 0으로 함
		ptr = 0;
//		매개변수 maxlen을 capacity에 대입, 배열 본체 생성
		capacity = maxlen;
		try {
			stk = new int[capacity];
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//	스택에 x를 푸시
	public int push(int x) throws OverflowIntStackException {
//		스택이 가득 차 있는 경우 예외
		if (ptr >= capacity) {
			throw new OverflowIntStackException();
		}
		return stk[ptr++] = x;
	}
	
//	스택에서 데이터를 팝(꼭대기에 있는 데이터를 꺼내어 제거하고 그 값을 반환)
	public int pop() throws EmptyIntStackException {
//		스택이 비어있는 경우 예외
		if (ptr <= 0) {
			throw new EmptyIntStackException();
		}
		return stk[--ptr];
	}
	
//	스택에서 데이터를 피크(스택의 꼭대기에 있는 데이터를 들여다봄)
	public int peek() throws EmptyIntStackException {
		if (ptr <= 0) {
			throw new EmptyIntStackException();
		}
//		스택이 비어있지 않으면 꼭대기에 있는 요소를 반환
		return stk[ptr - 1];
	}
	
//	스택에 쌓여있는 모든 데이터를 한번에 삭제하는 메서드
	public void clear() {
		ptr = 0;
	}
	
//	스택에서 x를 찾아 인덱스(없으면 -1)을 반환
	public int indexOf(int x) {
//		꼭대기 쪽부터 선형 검색
		for (int i = ptr - 1; i >= 0; i--) {
			if (stk[i] == x) {
//				검색 성공
				return i;
			}
		}
//		검색 실패
		return -1;
	}
	
//	스택의 용량을 반환
	public int getCapacity() {
		return capacity;
	}
	
//	현재 스택에 쌓여있는 데이터 개수(ptr 값)을 반환
	public int size() {
		return ptr;
	}
	
//	스택이 비어있는지 검사
	public boolean isEmpty() {
		return ptr <= 0;
	}
	
//	스택이 가득 찼는지 검사
	public boolean isFull() {
		return ptr >= capacity;
	}
	
//	스택 안에 있는 모든 데이터를 출력
	public void dump() {
		if (ptr <= 0) {
			System.out.println("스택이 비어있습니다.");
		} else {
			for (int i = 0; i < ptr; i++) {
				System.out.println(stk[i] + " ");
			}
			System.out.println();
		}
	}
	
}

