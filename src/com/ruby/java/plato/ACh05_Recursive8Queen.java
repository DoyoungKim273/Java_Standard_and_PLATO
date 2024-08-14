package com.ruby.java.plato;

//해가 256개 확인 필요 23.12.12
import java.util.ArrayList;
import java.util.List;

/*
 *https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
 *N Queen problem / backtracking
 *모든 해가 나오는 버젼 만들기 
* 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
* Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
* pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
* Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
* 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
*/

//	8퀸 문제 해결을 위한 백트래킹(backtracking) 알고리즘 구현
//	백트래킹 : 잘목된 경로로 진행했을 때 이전 상태로 돌아가서 다른 경로를 담색하는 방법
//	8x8 체스판 위에 8개의 퀸을 배치하되 서로 공격할 수 없는 위치에 놓는 문제
//	퀸은 가로, 세로, 대각선으로 움직일 수 있음

//	체스판 위의 퀸 위치 나타내는 point 클래스
//	백트래킹 위한 스택을 구현한 stack4 클래스
//	eightQueen 문제 해결을 위한 eightQueen 클래스
//	main 클래스 ---> 이렇게 총 4개의 흐름으로 진행됨

// 	체스판 위의 퀸의 위치를 나타내는 클래스
class Point {
//	퀸의 행 위치
	private int ix;
//	퀸의 열 위치
	private int iy;

//	point 생성자, 퀸의 위치를 초기화 함
	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

//	퀸의 위치를 문자열로 표현하는 메서드 
//	코드 내에서는 직접적으로 호출되거나 사용되고 있지 않음
//	주로 디버깅을 위해 사용되는 메서드
	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

//	getter와 setter는 point 클래스에서 내부 데이터에 접근, 수정하게 해주는 역할
//	퀸의 행 위치 반환
	public int getX() {
		return ix;
	}

//	퀸의 열 위치 반환
	public int getY() {
		return iy;
	}

//	퀸의 행 위치 설정
	public void setX(int x) {
		ix = x;
	}

//	퀸의 열 위치 설정
	public void setY(int y) {
		iy = y;
	}

//	두 퀸의 위치가 같은지 비교하는 메서드
//	퀸을 이동시키거나 새로운 퀸을 배치할 때 이미 다른 퀸이 있는 위치에 배치하는 것을 예방
	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point) p).ix) && (this.iy == ((Point) p).iy)) {
			return true;
			
		} else {
			return false;
		}
	}
}

//	스택 구현 클래스 -> 백트래킹 위해 퀸의 위치를 저장하는데 사용
class Stack4 {
//	스택이 비었을때 발생하는 예외 클래스		
//	generic class는 Throwable을 상속받을 수 없음(지원하지 않음)
//	제너릭 클래스 : 클래스, 메서드 정의 시 데이터 타입 특정X, 나중에 사용할떼 구체적 타입 지정하도록 하는 것
//	Throwable 클래스 : 자바에서 예외와 에러의 최상위 클래스, 모든 예외 클래스는 throwable을 상속 받아야 함
//	예외 처리 메커니즘에서 제너릭 타입의 예외를 던지는 것은 복잡하고 혼란을 일으킴
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

//	스택이 가득 찼을 때 발생하는 예외 클래스 
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

//	스택 데이터를 저장하는 리스트
//	point 객체들의 리스트를 저장하는 data라는 변수를 선언
	private List<Point> data; 
//	스택의 최대 크기
	private int capacity; 
//	스택의 현재 위치를 나타내는 포인터
	private int top; 

//	스택의 생성자 -> 최대 크기를 설정하고 초기화
	public Stack4(int capacity) {
		this.capacity = capacity;
		this.top = 0;
//		스택 데이터 초기화
		this.data = new ArrayList<>(capacity);
	}

//	스택에 데이터를 푸시(추가)하는 메서드
	public boolean push(Point x) throws OverflowGenericStackException {
		if (top >= capacity) {
			throw new OverflowGenericStackException("스택이 가득 찼습니다.");
		}
		data.add(x);
		top++;
		return true;
	}

//	스택에서 데이터를 팝(제거하고 반환)하는 메서드
	public Point pop() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("스택이 비었습니다.");
		}
		Point p = data.remove(--top);
		return p;
	}

//	스택에서 데이터를 피크(정상에서 확인)하는 메서드
	public Point peek() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("스택이 비었습니다.");
		}
		return data.get(top - 1);
	}

//	스택을 비우는 메서드
	public void clear() {
		top = 0;
		data.clear();
	}

//	스택에서 특정 데이터를 찾고 인덱스를 반환(없으면 -1을 반환)하는 메서드
	public int indexOf(Point x) {
//		꼭대기부터 선형 검색 진행
		for (int i = top - 1; i >= 0; i--) 
			if (data.get(i).equals(x))
//				검색 성공
				return i; 
//		검색 실패
		return -1; 
	}

//	스택의 크기(용량)을 반환하는 메서드
	public int getCapacity() {
		return capacity;
	}

//	스택에 쌓여있는 데이터의 개수를 반환하는 메서드
	public int size() {
		return top;
	}

//	스택이 비어있는지 확인하는 메서드
	public boolean isEmpty() {
		return top <= 0;
	}

//	스택이 가득 찼는지 확인하는 메서드
	public boolean isFull() {
		return top >= capacity;
	}

//	스택 안의 모든 데이터를 바닥부터 꼭대기까지 순서로 출력하는 메서드
	public void dump() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

//	8퀸 문제를 해결하기 위한 백트래킹 알고리즘을 구현한 클래스
public class ACh05_Recursive8Queen {
//	8퀸 문제를 해결하는 메서드
	public static void EightQueen(int[][] d) {
//		현재 배치된 퀸의 개수
		int count = 0;
//		찾은 해의 총 수
		int numberSolutions = 0;
//		현재 위치를 나타내는 행과 열
		int ix = 0, iy = 0;
//		백트래킹을 위한 스택 생성(100개를 저장할 수 있는 스택 생성)
		Stack4 st = new Stack4(100);
//		첫번째 퀸을 (0, 0)에 배치
		Point p = new Point(ix, iy);
//		첫번째 퀸 배치 하고 현 위치에 퀸 넣었다는 표시를 함
		d[ix][iy] = 1;
//		퀸의 수 증가
		count++;
//		다음 열로 이동
		iy++;
		
		try {
//			첫번째 퀸의 위치를 스택에 저장
			st.push(p);
			while (true) {
//				8개의 퀸이 모두 배치된 경우
				if (count == 8) {
//					해의 수 증가
					numberSolutions++;
					System.out.println("Solution : " + numberSolutions);
//					현재 배치된 퀸의 위치 출력
					showQueens(d);
					
//					마지막으로 배치된 퀸을 제거하고 백트래킹
					Point last = st.pop();
					d[last.getX()][last.getY()] = 0;
//					퀸의 개수 감소
					count--;
//					이전 퀸의 행으로 돌아감
					ix = last.getX();
//					이전 퀸의 다음 열로 이동
					iy = last.getY() + 1;
				} else {
//					현재 위치에서 다음에 퀸을 놓을 수 있는 열을 찾음
					int nextCol = nextMove(d, ix, iy);
//					퀸을 놓을 수 있는 위치가 없는 경우
					if (nextCol == -1) {
//						스택이 비어있으면 모든 경우를 다 탐색한 것
						if (st.isEmpty()) {
//							탐색 종료
							break;
						}
						
//						이전 상태로 백트래킹
						Point last = st.pop();
						d[last.getX()][last.getY()] = 0;
//						퀸의 수 감소, 이전 행으로 돌아감, 다음 열로 이동하여 다시 시도
						count--;
						ix = last.getX();
						iy = last.getY() + 1;
					} else {
//						퀸을 놓을 수 있는 위치를 찾은 경우 -> 퀸을 배치
						d[ix][nextCol] = 1;
//						퀸의 위치를 스택에 저장
						st.push(new Point(ix, nextCol));
//						퀸의 수 증가, 다음 행으로 이동, 첫번째 열부터 다시 시작
						count++;
						ix++;
						iy = 0;
					}
				}
			}
		} catch (Stack4.EmptyGenericStackException e) {
			System.out.println("스택이 비었습니다." + e.getMessage());
		}
		System.out.println("Total solutions : " + numberSolutions);

	}

//	배열 d에서 현재 행에 퀸을 배치할 수 있는지 확인하는 메서드
	public static boolean checkRow(int[][] d, int crow) { // 배열 d에서 행 crow에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d.length; i++) {
			if (d[crow][i] == 1) {
				return false;
			}
		}
		return true;
	}

//	현재 열에 퀸을 배치할 수 있는지 확인하는 메서드
	public static boolean checkCol(int[][] d, int ccol) {// 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
		for (int i = 0; i < d.length; i++) {
			if (d[i][ccol] == 1)
				return false;
		}
		return true;
	}

//	남서 - 북동 대각선에 ~
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		for (int i = 0; i < d.length; i++) {
			int j = cy - (cx - i);
			if (j >= 0 && j < d.length && d[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

//	남동 - 북서 대각선에 ~
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		for (int i = 0; i < d.length; i++) {
			int j = cy + (cx - i);
			if (j >= 0 && j < d.length && d[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

//	특정 위치에 퀸 놓을 수 있는지 검사하는 메서드
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		return checkRow(d, x) && checkCol(d, y) && checkDiagSW(d, x, y) && checkDiagSE(d, x, y);
	}

//	현재 위치에서 퀸을 놓을 수 있는 열을 찾는 메서드
//	배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴 
	public static int nextMove(int[][] d, int row, int col) { 
//		현재 row, col에 대하여 이동할 col을 return
//		주어진 col에서 시작하여
		for (int i = col; i < d.length; i++) {
//			퀸을 놓을 수 있는지 확인
			if (checkMove(d, row, i)) {
//				가능하다면 그 열을 반환
				return i;
			}
		}
//		불가능하다면 -1을 반환
		return -1;
	}

//	체스판을 출력하는 메서드
	static void showQueens(int[][] data) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

//	프로그램 실행 진입점
	public static void main(String[] args) {
//		체스판의 크기
		int row = 8, col = 8;
//		체스판을 2차원 배열로 표현
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
//				체스판 초기화 (모든 칸을 0으로 설정)
				data[i][j] = 0;

//		8퀸 문제 해결 시작
		EightQueen(data);

	}
}
