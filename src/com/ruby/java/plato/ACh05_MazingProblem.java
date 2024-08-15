package com.ruby.java.plato;

import java.util.ArrayList;
import java.util.List;

//	방항을 나타내는 열거형(enum) 정의 : 북, 북동, 동, 남동, 서, 북서
enum Directions2 {
	N, NE, E, SE, S, SW, W, NW
}

//	미로에서의 위치와 이동 방향을 저장하는 클래스
class Items3 {
//	현재 위치의 x, y (행, 열) 좌표
	int x;
	int y;
//	이동 방향을 나타내는 변수 (0 ~ 7 사이의 값, Directions2와 일치)
	int dir;

//	생성자 : 위치와 방향을 초기화
	public Items3(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.dir = d;
	}

//	객체 정보를 문자열로 반환 (디버깅용)
	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + ", dir = " + dir;
	}
}

//	각 방향에 따른 이동 오프셋(위치 변위)를 저장하는 클래스
class Offsets3 {
//	x, y 방향으로의 변위
	int a;
	int b;

//	생성자 : 변위를 초기화
	public Offsets3(int a, int b) {
		this.a = a;
		this.b = b;
	}
}

//	스택 자료구조를 구현하는 클래스
class StackList {
//	스택으로 사용할 리스트
	private List<Items3> data;
//	스택의 최대 크기
	private int capacity;
//	스택의 포인터 (스택의 현재 크기)
	private int top;

//	스택이 비어있을때 발생하는 예외 클래스
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}

//	스택이 가득 찼을때 ~ 
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

//	생성자 : 스택의 크기를 설정하고 초기화
	public StackList(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
//			스택 본체용 배열을 생성
//			수정) 초기화 시 용량을 0에서 capacity로 변경
			data = new ArrayList<>(capacity);
//			배열 생성 실패 시
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//	스택에 새로운 요소를 추가 (푸시)
	public void push(Items3 p) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException();
		data.add(p);
		top++;
		return;
	}

//	스택의 가장 위 요소 팝
	public Items3 pop() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.remove(--top);
	}

//	스택의 가장 위 요소 피크
	public Items3 peek() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.get(top - 1);
	}

//	스택을 비움
	public void clear() {
		top = 0;
	}

//	스택에서 특정 요소 찾아서 인덱스 반환
	public int indexOf(Items3 x) {
//		정상 쪽에서 선형검색
		for (int i = top - 1; i >= 0; i--)
			if (data.get(i).equals(x))
//				검색 성공, 인덱스 반환
				return i;
//		검색 실패
		return -1;
	}

//	스택의 최대 크기 반환
	public int getCapacity() {
		return capacity;
	}

//	스택에 쌓여있는 데이터 개수 반환
	public int size() {
		return top;
	}

//	스택이 비었는지?
	public boolean isEmpty() {
		return top <= 0;
	}

//	스택이 가득 찼는지?
	public boolean isFull() {
		return top >= capacity;
	}

//	스택안의 모든 데이터 바닥 -> 정상 순서로 덤프
	public void dump() {
		if (top <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
//				모든 요소 출력 (덤프)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

//	미로찾기 문제를 해결하는 클래스
public class ACh05_MazingProblem {
//	8방향의 이동 오프셋을 저장하는 배열
//	static을 선언하는 이유 : 모든 인스턴스에서 공유할 수 있게 하기 위함
	static Offsets3[] moves = new Offsets3[8];

//	미로에서 경로를 찾는 메서드
//	ix와 iy는 출구의 위치 의미
	public static void path(int[][] maze, int[][] mark, int ix, int iy) {

//		출발점 표시
		mark[1][1] = 1;
//		경로를 저장할 스택 생성
//		스택의 크기를 늘려서 오버플로우 문제 해결 -> 이게 해결책이 아님
		StackList st = new StackList(50);
//		초기 아이템 생성 (사용되지 않음)
		Items3 temp = new Items3(0, 0, 0);
		temp.x = 1;
		temp.y = 1;
//		처음에 동쪽(E)으로 시작
//		수정) 모든 방향 탐색 가능하도록 바꿈 -> 해결책이 아님
		temp.dir = 2;
//		미로찾기 궤적은 2로 표시
		mark[temp.x][temp.y] = 2;
//		초기 위치를 스택에 추가
		st.push(temp);

//		스택이 비어있지 않을 때까지 반복
		while (!st.isEmpty()) {
//			스택에서 하나의 위치를 꺼냄 (unstack)
			Items3 tmp = st.pop();
			
			int i = tmp.x;
			int j = tmp.y;
			int d = tmp.dir;
			
//			backtracking 궤적은 1로 표시
//			수정) 이미 방문한 경로는 2로 표시
//			mark[i][j] = 1;
			
//			디버깅용 코드
			System.out.println("현재 위치 : " + i + "," + j);
			
//			8가지 방향으로 이동 시도
			while (d < 8) {
				int g = i + moves[d].a;
				int h = j + moves[d].b;

//				디버깅용 코드
//			    System.out.println("다음 이동 가능 여부: " + (g >= 1 && g <= 12 && h >= 1 && h <= 15) + ", 위치: (" + g + "," + h + ")");

				if (g >= 1 && g <= 12 && h >= 1 && h <= 15) {
//				출구에 도달한 경우
					System.out.println("204라인 디버깅 코드 " + g + " , " + h);
				if ((g == ix) && (h == iy)) {
					System.out.println("경로를 찾았습니다.");
//					수정) 출구 위치도 경로로 표시
					mark[g][h] = 1;
					return;
				}

//				새로운 위치가 유효하고 방문하지 않았을 경우
				if ((maze[g][h] == 0) && (mark[g][h] == 0)) { 
					System.out.println("215라인 디버깅 코드 " + g + " , " + h);
					st.push(new Items3(i, j, d + 1));
					i = g;
					j = h;
//					다시 북쪽부터 시도 (방향 초기화)
					d = 0;
//					이동한 라인을 방문으로 표시
					mark[i][j] = 1;
					System.out.println("222라인 디버깅 코드 " + i + " , " + j);
					break;
				} else {
//					다음 방향으로 이동 시도
					d++;
					System.out.println("226라인 디버깅 코드 " + d);
				}
			}
			}
		}
//		경로가 없음을 출력
		System.out.println("경로가 없습니다.");
	}

//	미로 행렬을 출력하는 메소드
	static void showMatrix(int[][] d, int row, int col) {
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
	}

//	메인 메서드
	public static void main(String[] args) {
//		미로 배열 초기화
		int[][] maze = new int[14][17];
//		방문 표시 배열 초기화
		int[][] mark = new int[14][17];

//		미로 데이터 입력 (12 x 15) 크기
		int input[][] = { { 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
				{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 }, { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
				{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 }, { 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
				{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 }, { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
				{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
				{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 } };

		for (int ia = 0; ia < 8; ia++) {
//			8방향 오프셋 초기화
//			배열에 offsets 객체를 치환
			moves[ia] = new Offsets3(0, 0);
		}
//			북쪽으로 이동
			moves[0].a = -1;
			moves[0].b = 0;
//			북동쪽으로 이동
			moves[1].a = -1;
			moves[1].b = 1;
//			동쪽으로 이동
			moves[2].a = 0;
			moves[2].b = 1;
//			남동쪽으로 이동
			moves[3].a = 1;
			moves[3].b = 1;
//			남쪽으로 이동
			moves[4].a = 1;
			moves[4].b = 0;
//			남서쪽으로 이동
			moves[5].a = 1;
			moves[5].b = -1;
//			서쪽으로 이동
			moves[6].a = 0;
			moves[6].b = -1;
//			북서쪽으로 이동
			moves[7].a = -1;
			moves[7].b = -1;
		
		
		// Directions d;
		// d = Directions.N;
		// d = d + 1;//java는 지원안됨

//		input[][]을 maze[][]로 변환
		for (int i = 0; i < 14; i++) {
			for (int j = 0; j < 17; j++) {
//				미로의 테두리 부분 의미
				if (i == 0 || i == 13 || j == 0 || j == 16) {
//					미로의 테두리를 모두 1로 설정
					maze[i][j] = 1;
				} else {
//					input[][] 배열의 값을 maze[][] 배열로 복사
//					미로의 내부를 input[][] 배열로 채움
//					-1 하는 이유는 maze[][] 베열의 경계 부분을 제외한 내부 부분에 정확하게 복사하기 위함
					maze[i][j] = input[i - 1][j - 1];
				}
			}
		}

		System.out.println("maze[12,15]::");
//		미로 배열을 출력
		showMatrix(maze, 13, 16);

		System.out.println("mark::");
//		방문 표시 배열을 출력
		showMatrix(mark, 13, 16);
		System.out.println("방문 표시 배열 출력 확인");

//		경로 찾기 시작
		path(maze, mark, 12, 15);
		System.out.println("mark::");
		System.out.println("경로 찾기 결과 출력 확인");
//		경로 찾기 결과를 출력
		showMatrix(mark, 12, 15);
	}
}
