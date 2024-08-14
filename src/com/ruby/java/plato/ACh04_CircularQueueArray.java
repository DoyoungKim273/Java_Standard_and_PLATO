package com.ruby.java.plato;

/*
 * 원형 큐로서 큐에 Point 객체를 저장
 * class CircularQueue의 필드는 QUEUE_SIZE, que,	front, rear, isEmptyTag 변수만 사용
 */

import java.util.Random;
import java.util.Scanner;

import com.ruby.java.plato.IntQueue.EmptyIntQueueException;
import com.ruby.java.plato.IntQueue.OverflowIntQueueException;

//	point 객체를 원형 큐에 저장하기 위한 클래스
class Point5 {
	private int ix;
	private int iy;

	public Point5(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point5) p).ix) && (this.iy == ((Point5) p).iy))
			return true;
		else
			return false;
	}
}

//	원형 큐를 구현하는 클래스
class CircularQueue {
//	큐의 최대 크기
	static int QUEUE_SIZE = 0;
//	point5 객체를 저장하는 배열
	Point5[] que;
//	큐의 앞과 뒤를 가리키는 인덱스
	int front, rear;
//	큐가 비어있는지 확인하는 플래그
	static boolean isEmptyTag;

	// --- 실행시 예외: 큐가 비어있음 ---//
	public static class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	public static class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException(String message) {
			super(message);
		}
	}

//	생성자 : 큐의 크기를 초기화하고 필요한 변수를 설정
	public CircularQueue(int count) {
//		초기 상태에서 front와 rear는 0으로 설정
		front = rear = 0;
//		큐의 크기에 맞게 배열을 생성
		que = new Point5[count];
//		큐가 비어있는 상태로 시작
		isEmptyTag = true;
//		큐의 크기를 설정
// 		다음 2개 field 가 필요한지 확인 필요
		QUEUE_SIZE = count;
	}

//	데이터를 큐에 추가하는 메서드
	void push(Point5 it) throws OverflowIntQueueException {
//		큐가 가득 찬 경우 예외를 발생시킴
		if (isFull()) {
			throw new OverflowIntQueueException("push: circular queue overflow");
		}

//		데이터를 큐에 추가하고 rear를 증가시킴
		que[rear] = it;
		rear = (rear + 1) % QUEUE_SIZE;
//		큐가 더이상 비어있지 않음을 표시
		isEmptyTag = false;
	}

//	큐에서 데이터를 꺼내는 메서드
	Point5 pop() throws EmptyIntQueueException {
		if (isEmpty()) {
			throw new EmptyIntQueueException("pop: circular queue overflow");
		}

//		데이터를 큐에서 꺼내고 front를 증가시킴
		Point5 it = que[front];
		front = (front + 1) % QUEUE_SIZE;

//		큐가 비어있더면 isEmptyTag를 True로 설정
		if (front == rear) {
			isEmptyTag = true;
		}
		return it;
	}

//	큐를 비우는 메서드
	void clear() throws EmptyIntQueueException {
		if (isEmpty()) {
			throw new EmptyIntQueueException("enque: circular queue overflow");
		}

//		front와 rear를 초기화하고 큐를 비움
		front = rear = 0;
		isEmptyTag = true;
	}

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return QUEUE_SIZE;
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
//		큐의 현재 크기를 계산하여 반환
		if (isFull()) {
			return QUEUE_SIZE;
		} else if (isEmpty()){
			return 0;
		} else if (front <= rear) {
			return rear - front;
		} else {
			return QUEUE_SIZE - front + rear;
		}
	}

	// --- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		return isEmptyTag;

	}

	// --- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		return rear == front && !isEmptyTag;

	}

//	큐의 모든 데이터를 출력하는 메서드
	public void dump() throws EmptyIntQueueException {
		if (isEmpty()) {
			throw new EmptyIntQueueException("dump: queue empty");
		} else {
//			큐의 모든 데이터를 순서대로 출력
			int i = front;
//			doWhile문을 ㅎ사용하여 최소한 한번은 루프가 돌고 i가 rear와 같아질때까지 루프 실행
			do {
				System.out.println(que[i] + " ");
				i = (i + 1) % QUEUE_SIZE;
//			큐가 가득 찬 경우를 포함해서 모든 경우에 대해 안전하게 루프 종료 가능
			} while (i != rear); {
				System.out.println();
			}
			System.out.println();
		}
	}

//	큐의 맨 앞 데이터를 확인하는 메서드
	public Point5 peek() throws EmptyIntQueueException {
		if (isEmpty()) {
			throw new EmptyIntQueueException("peek: queue empty"); // 큐가 비어있음
		}
		return que[front];
	}
}

//	원형 큐의 테스트 프로그램
public class ACh04_CircularQueueArray {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
//		최대 4개를 인큐할 수 있는 큐
		CircularQueue oq = new CircularQueue(4);
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point5 p = null;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			switch (menu) {
			case 1: // 인큐

//				0부터 19까지의 랜덤한 x, y 좌표 생성
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
//				생성된 좌표를 가진 point5 객체를 생성
				p = new Point5(rndx, rndy);
				try {
//					큐에 point5 객체를 추가
					oq.push(p);
					System.out.println("push: size() = " + oq.size());
				} catch (CircularQueue.OverflowIntQueueException e) {
//					예외 메시지 출력, 예외의 스택 트레이스 출력(디버깅 위함)
					System.out.println("queue이 full입니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("pop: size() = " + oq.size());
				} catch (CircularQueue.EmptyIntQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyIntQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
				
			case 4: // 덤프
				try {
					if (!oq.isEmpty()) {
						oq.dump();
					} else {
						System.out.println("큐가 비어있기에 덤프할 데이터가 없습니다.");
					}
				} catch (CircularQueue.EmptyIntQueueException e) {
					System.out.println("queue가 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: // clear
				try {
					oq.clear();
					System.out.println("큐를 비웠습니다");
				} catch (CircularQueue.EmptyIntQueueException e) {
					System.out.println("큐가 이미 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
