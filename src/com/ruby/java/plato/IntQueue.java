package com.ruby.java.plato;

// 	데이터를 일시적으로 쌓아두는 기본 자료구조
//	가장 먼저 넣은 데이터를 가장 먼저 꺼내는 선입선출(FIFO)
public class IntQueue {
//	큐용 배열
	private int[] que;
//	큐의 용량
	private int capacity;
//	맨 앞의 요소 커서
	private int front;
//	맨 뒤의 요소 커서
	private int rear;
//	현재 데이터 개수
	private int num;
	
//	실행 시 예외 :  큐가 비어있음
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException() {}
	}
	
//	실행 시 예외 : 큐가 가득 참
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException() {}
 	}
	
//	생성자
	public IntQueue(int maxlen) {
//		생성 시 큐는 비어 있기 때문에 모두 0의 값
		num = front = rear = 0;
		capacity = maxlen;
		try {
			que = new int[capacity];
		} catch (OutOfMemoryError e){
			capacity = 0;
		}
	}
	
// 	큐에 데이터를 인큐하고 인큐한 값을 그대로 반환하는 메서드
	public int enque(int x) throws OverflowIntQueueException {
//		큐가 가득 차서 인큐가 불가하면 예외 처리
		if (num >= capacity) {
			throw new OverflowIntQueueException();
		}
		
		que[rear++] = x;
		num++;
		
//		rear값이 capacity와 같아지는 것을 방지 
//		rear값을 1 증가시켰을때 큐의 최대 용량값인 capacity와 같아질 경우 rear을 배열의 첫 인덱스인 0으로 변경
//		다음에 인큐할 데이터가 que[0] 위치에 제대로 저장됨
		if (rear == capacity) {
			rear = 0;
		}
		
		return x;
	}
	
//	큐에서 데이터를 디큐하고 그 값을 반환하는 메서드
//	큐가 비어있어 디큐가 불가하면 예외 처리
	public int deque() throws EmptyIntQueueException {
		if (num <= 0) {
			throw new EmptyIntQueueException();
		}
		
//		큐에 들어 있는 값을 꺼내고 front를 1 증가시킨 다음 num값을 1 감소시킴
		int x = que[front++];
		num--;
		
//		증가시킨 front 값이 큐의 용량인 capacity와 같아지면 front 값을 배열의 맨 앞 인덱스인 0으로 변겅
		if (front == capacity) {
			front = 0;
		}
		
		return x;
	}
	
//	큐에서 데이터를 피크(프런트 데이터 -- 맨 뒤의 데이터를 들여다봄)
	public int peek() throws EmptyIntQueueException {
		if (num <= 0) {
			throw new EmptyIntQueueException();
		}
		return que[front];
	}
	
//	큐를 비움
	public void clear() {
		num = front = rear = 0;
	}
	
//	큐에서 x를 검색하여 인덱스 반환(찾지 못하면 -1을 반환)
	public int indexOf(int x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx] == x) {
				return idx;
			}
		}
		return -1;
	}
	
	public int getCapcity() {
		return capacity;
	}
	
	public int size() {
		return num;
	}
	
	public boolean isEmpty() {
		return num <= 0;
	}
	
	public boolean isFull() {
		return num >= capacity;
	}
	
	public void dump() {
		if (num <= 0) {
			System.out.println("큐가 비어있습니다.");
		} else {
			for (int i = 0; i < num; i++) {
				System.out.print(que[(i + front) % capacity] + " ");
			}
			System.out.println();
		}
	}
	
	
}
