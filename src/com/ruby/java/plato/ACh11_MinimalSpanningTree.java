package com.ruby.java.plato;

import java.util.Scanner;

//	minimal spanning tree:: Kruskal’s source code
// 	MST : 그래프에서 모든 노드를 포함하면서 가중치의 합이 최소가 되는 트리
//	-> 사이클을 포함하지 않으며, 연결된 노드 집합에서 가능한 최소 비용으로 모든 노드를 연결함
//	Kruskal’s Algorithm : 그래프의 모든 간선을 비용에 따라 정렬
//	-> 최소 비용의 간선부터 순차적으로 선택하면서 트리를 확장하는 방식으로 작동
//	min heap, set 사용하여 MST 구현

//	인터페이스 사용하여 추상화된 기능 정의한 것
//	최대 힙 자료구조에서 필요한 2개의 메서드 정의
//	인터페이스 자체는 구체적인 구현 제공 X
//	이 인터페이스를 구현하는 클래스가 실제로 이 메서드들이 어떻게 작동할지 결정함
interface MaxHeap21 {
	public void Insert(Edge x);
	public Edge DeleteMin();
}

//	그래프의 간선을 표현
class Edge implements Comparable<Edge> {
//	두 개의 노드
	int src;
	int dest;
//	간선의 가중치
	int weight;

//	간선을 가중치 기준으로 비교하는 메서드
	@Override
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}

}

//	최대 힙을 구현한 것
//	간선을 가중치에 따라 정렬하여 저장
class Heap21 implements MaxHeap21 {
//	힙의 기본 크기를 나타내는 상수 -> 이 코드에서는 사용되지X
	final int heapSize = 100;
//	힙 배열, 간선을 저장하는 배열
	private Edge[] heap;
//	현재 힙에 저장된 요소의 수 (힙 크기, current size of MaxHeap)
	private int n; 
//	힙의 최대 크기, 배열의 크기 (Maximum allowable size of MaxHeap)
	private int MaxSize; 

//	Heap 클래스의 생성자
	public Heap21(int sz) {
//		힙의 최대 크기
		MaxSize = sz;
//		현재 요소수를 초기화
		n = 0;
//		힙 배열 초기화 (힙의 인덱스는 1부터 시작, heap[0]은 사용하지 않음)
		heap = new Edge[MaxSize + 1]; 
	}

	public void display() {
		int i;
//		힙의 요소 차례대로 출력
		for (i = 1; i <= n; i++) {
			System.out.print("  (" + i + ", " + heap[i] + ") ");
			}
		System.out.println();
	}

//	힙에 새로운 간선을 삽입
	@Override
	public void Insert(Edge x) {
//		힙이 가득 찼는지 확인
		if (n == MaxSize) {
			HeapFull();
			return;
		}

//		요소 개수를 1 증가시키고 i에 저장
		int i = ++n;
//		새로운 간선을 적절한 위치에 삽입하기 위해 상위 노드와 비교하며 이
		while (i != 1 && x.compareTo(heap[i / 2]) < 0) {
//			부모 노드를 자식 노드로 이동
			heap[i] = heap[i / 2];
//			부모 노드의 인덱스로 이동
			i /= 2;
		}
//		적절한 위치에 새로운 간선을 삽입
		heap[i] = x;
	}

//	힙에서 가중치가 가장 작은 간선을 제거하고 반환
	@Override
	public Edge DeleteMin() {
//		힙이 비어있는지 확인
		if (n == 0) {
			HeapEmpty();
			return null;
		}

//		최솟값(최소 가중치 간선)은 힙의 루트 노드
		Edge min = heap[1];
//		마지막 요소 가져오고 힙의 크기를 1 감소
		Edge last = heap[n--];
//		루트 노드부터 시작
		int i = 1;
		int child;

//		힙 재졍럴 하여 최소 힙 속성을 유지
//		자식 노드 있는 동안 반복
		while (i * 2 <= n) {
//			왼쪽 자식 노드의 인덱스
			child = i * 2;
//			오른쪽 자식 노드가 존재하고, 오른쪽 자식이 더 작으면 child를 오른쪽 자식으로 변경
			if (child != n && heap[child + 1].compareTo(heap[child]) < 0) {
				child++;
			}
//			마지막 요소가 자식보다 작거나 같으면 힙 속성이 만족됨
			if (last.compareTo(heap[child]) <= 0) {
				break;
			}
//			자식을 부모로 올림
			heap[i] = heap[child];
//			지식의 인덱스로 이동
			i = child;
		}
//		마지막 요소를 적절한 위치에 삽입
		heap[i] = last;
//		최소값 반환
		return min;
	}

//	힙이 비어있을때 
	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

//	힙이 가득 찼을때
	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

//	그래프의 인접 행렬로 표현한 클래스
class Graph {
//	그래프의 노드(정점) 수
	int vertices;
//	인접 행렬을 사용하여 그래프를 표현
	int[][] adjacencyMatrix;
	
//	생성자
	public Graph(int vertices) {
		this.vertices = vertices;
		adjacencyMatrix = new int[vertices][vertices];
	}
	
//	간선을 그래프에 추가하는 메서드
	public void InsertVertex(int start, int end, int weight) {
//		start에서 end로 가는간선의 가중치 설정
		adjacencyMatrix[start][end] = weight;
//		end에서 start로 가는 간선의 가중치 설정 (무방향 그래프이므로 대칭)
		adjacencyMatrix[end][start] = weight;
	}

//	그래프의 인접 행렬을 출력하는 메서드
	public void displayadjacencyLists() {
//		각 노드에 대해 반복
		for (int i = 0; i < vertices; i++) {
//			현재 노드 i를 출력
			System.out.println(i + " : ");
//			해당 노드 i와 연결된 다른 정점들을 확인
			for (int j = 0; j < vertices; j++) {
//				i와 j 사이에 간선이 존재하는 경우
				if (adjacencyMatrix[i][j] != 0) {
//					연결된 정점과 그 간선의 가중치를 출력
					System.out.println(j + " ( " + adjacencyMatrix[i][j] + " ) ");
				}
			}
			System.out.println();
		}
	}
}

//	Union-Find 자료구조 구현
//	서로소 집합 관리 위해 사용
//	여러 개의 집합을 관리, 각 원소가 어떤 집합에 속하는지 빠르게 찾고 두 집합을 하나로 합치는 작업 수행
class Sets {
//	각 원소의 부모를 나타내는 배열
	private int[] parent;
//	트리의 깊이를 추적하는 배열
	private int[] rank;
	
//	생성자, 집합의 크기를 인자로 받아 초기화
	public Sets(int size) {
//		parent 배열과 rank 배열을 size 크기로 초기화
		parent = new int[size];
		rank = new int[size];
		
//		각 원소를 독립된 집합으로 초기화
		for (int i = 0; i < size; i++) {
//			각 원소는 처음에 자기 자신을 부모로 가짐
			parent[i] = i;
//			모든 원소의 초기 rank는 0
			rank[i] = 0;
		}
	}
	
//	주어진 원소가 속한 집합의 루트를 찾음
	public int find(int element) {
//		만약 원소의 부모자 자기 자신이 아니면
		if (parent[element] != element) {
//			재귀적으로 부모를 찾아 루트를 설정
			parent[element] = find(parent[element]);
		}
//		최종적으로 루트를 반환
		return parent[element];
	}
	
//	두 집합을 하나로 합치는 메서드
	public void union(int set1, int set2) {
//		첫번째, 두번째 집합의 루트를 찾음
		int root1 = find(set1);
		int root2 = find(set2);
		
//		두 집합의 루트가 다르면 두 집합을 합침
		if (root1 != root2) {
//			rank를 사용하여 작은 트리를 큰 트리에 붙임
			if (rank[root1] < rank[root2]) {
//			root1이 작으면 root2 아래에 붙임
				parent[root1] = root2;
		} else if (rank[root1] > rank[root2]){
			parent[root2] = root1;
		} else {
//			두 트리의 rank가 같으면
//			root2를 root1 아래에 붙이고 root1의 rank를 증가시킴
			parent[root2] = root1;
			rank[root1]++;
		}
		}
	}
}

public class ACh11_MinimalSpanningTree {	// The main function to construct MST using Kruskal's algorithm
//	주어진 그래프에서 간선을 edgeSet에 추가하는 함수
	static int addEdgeSet(Edge[] edgeSet, int e, int from, int to, int w) {
//		간선의 시작 노드
		edgeSet[e].src = from;
//		간선의 끝 노드
		edgeSet[e].dest = to;
//		간선의 가중치 설정
		edgeSet[e].weight = w;

//		간선의 개수를 1 증가시켜 반환
		return e + 1;
	}

//	그래프의 인접 리스트를 읽어 edge set를 만듦
	static int makeEdgeSet(Graph grp, Edge[] edgeSet, int e) {
		// graph의 adjacency list를 읽어 edge set를 만든다
		for (int i = 0; i < grp.adjacencyMatrix.length; i++) {
			for (int j = i + 1; j < grp.adjacencyMatrix[i].length; j++) {
//				간선이 존재하는 경우
				if (grp.adjacencyMatrix[i][j] != 0) {
					e = addEdgeSet(edgeSet, e, i, j, grp.adjacencyMatrix[i][j]);
				}
			}
		}
//		생성된 edgeSet의 크기를 반환
		return e;
	}


//	Kruskal 알고리즘을 사용해 MST를 생성하는 함수
	static void KruskalMST(Graph graph, int n) {
//		최대 30개의 간선을 저장할 배열
		Edge[] edgeSet = new Edge[30];
//		MST를 저장할 배열
		Edge result[] = new Edge[30]; 
		for (int p = 0; p < 30; p++) {
//			각 배열의 각 요소 초기화
			edgeSet[p] = new Edge();
			result[p] = new Edge();
		}

		int t = 0; // MST result[]
		int edgeNum = 0; // edgeSet
//		그래프에서 edgeSet을 생성
		edgeNum = makeEdgeSet(graph, edgeSet, edgeNum);

//		최대 크기가 100인 힙 생성
		Heap21 hp = new Heap21(100);
		for (int j = 0; j < edgeNum; j++) {
//			생성된 edgeSet의 간선을 힙에 삽입
			hp.Insert(edgeSet[j]);
		}

//		최대 20개의 노드를 가진 Sets 객체 생성
		Sets m = new Sets(20);

//		MST에 포함된 간선의 수가 n-1 보다 적은 동안 반복
		while (t < n - 1)
		{
//			가중치가 가장 작은 간선을 힙에서 제거
			Edge nextEdge = hp.DeleteMin();
//			더이상 간선이 없으면 종료
			if (nextEdge == null) {
				break;
			}
			
//			간선의 시작 / 끝 노드가 속한 집합 찾기
			int x = m.find(nextEdge.src);
			int y = m.find(nextEdge.dest);
			
//			두 노드가 서로 다른 집합에 속해 있으면 사이클이 발생하지 않음
			if (x != y) {
//				간선을 MST에 추가
				result[t++] = nextEdge;
//				두 집합을 합침
				m.union(x, y);
			}
		}
		
//		MST에 필요한 간선이 충분치 않으면 spanning tree 없음
		if (t < n - 1) {
			System.out.println("no spanning tree");
			return;
		}
		

//		MST 출력
		for (int k = 0; k < t; k++) {
			System.out.println(result[k] + " ");
		}
		return;
	}

	// Driver program to test above functions
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 0, n, weight;
		int startBFSNode = 100;// the start node to BFS
		int startEdge, endEdge;
		System.out.println("Input the total node number: ");
		n = sc.nextInt();

		/* Let us create following weighted graph */

		Graph graph = new Graph(n);
		while (select != '0') {
			System.out.println("\n명령선택:: 1.edges/Weight 입력, 2. Adjacency Lists 출력, 3. spanningTree, 4: Quit => ");
			select = sc.nextInt();
			switch (select) {

			case 1:
				System.out.println("edge 추가: from vertext: ");
				startEdge = sc.nextInt();
				System.out.println("to vertex: ");
				endEdge = sc.nextInt();
				System.out.println("가중치: ");
				weight = sc.nextInt();
				if (startEdge < 0 || startEdge >= n || endEdge < 0 || endEdge >= n) {
					System.out.println("the input node is out of bound.");
					break;
				}
				// get smallest start node.
				if (startEdge < startBFSNode)
					startBFSNode = startEdge;
				if (endEdge < startBFSNode)
					startBFSNode = endEdge;
				graph.InsertVertex(startEdge, endEdge, weight);
				break;
			case 2:
				// display
				graph.displayadjacencyLists();
				break;
			case 3:
				System.out.println("\n Minimal SpanningTree 작업 시작");
				KruskalMST(graph, n);

				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  ");
				System.out.println("Re-Enter");
				break;
			}
		}
		return;
	}
}
