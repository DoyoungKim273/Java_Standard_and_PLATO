package com.ruby.java.plato;

import java.util.Scanner;

//	minimal spanning tree:: Kruskal’s source code
// 	MST : 그래프에서 모든 노드를 포함하면서 가중치의 합이 최소가 되는 트리
//	-> 사이클을 포함하지 않으며, 연결된 노드 집합에서 가능한 최소 비용으로 모든 노드를 연결함
//	Kruskal’s Algorithm : 그래프의 모든 간선을 비용에 따라 정렬
//	-> 최소 비용의 간선부터 순차적으로 선택하면서 트리를 확장하는 방식으로 작동
//	min heap, set 사용하여 MST 구현

interface MaxHeap {
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
class Heap implements MaxHeap {
	final int heapSize = 100;
	private Edge[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) {
		MaxSize = sz;
		n = 0;
		heap = new Edge[MaxSize + 1]; // heap[0]은 사용하지 않음
	}

	public void display() {
		int i;
		for (i = 1; i <= n; i++)
			System.out.print("  (" + i + ", " + heap[i] + ") ");
		System.out.println();
	}

//	힙에 새로운 간선을 삽입
	@Override
	public void Insert(Edge x) {
		if (n == MaxSize) {
			HeapFull();
			return;
		}

		int i = ++n;
		while (i != 1 && x.compareTo(heap[i / 2]) < 0) {
			heap[i] = heap[i / 2];
			i /= 2;
		}
		heap[i] = x;
	}

//	힙에서 가중치가 가장 작은 간선을 제거하고 반환
	@Override
	public Edge DeleteMin() {
		if (n == 0) {
			HeapEmpty();
			return null;
		}

		Edge min = heap[1];
		Edge last = heap[n--];
		int i = 1;
		int child;

		while (i * 2 <= n) {
			child = i * 2;
			if (child != n && heap[child + 1].compareTo(heap[child]) < 0) {
				child++;
			}
			if (last.compareTo(heap[child]) <= 0) {
				break;
			}
			heap[i] = heap[child];
			i = child;
		}
		heap[i] = last;
		return min;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

class Graph {
	int vertices;
	int[][] adjacencyMatrix;
	
	public Graph(int vertices) {
		this.vertices = vertices;
		adjacencyMatrix = new int[vertices][vertices];
	}
	
	public void InsertVertex(int start, int end, int weight) {
		adjacencyMatrix[start][end] = weight;
		adjacencyMatrix[end][start] = weight;
	}
	
	public void displayadjacencyLists() {
		for (int i = 0; i < vertices; i++) {
			System.out.println(i + " : ");
			for (int j = 0; j < vertices; j++) {
				if (adjacencyMatrix[i][j] != 0) {
					System.out.println(j + " ( " + adjacencyMatrix[i][j] + " ) ");
				}
			}
			System.out.println();
		}
	}
}

class Sets {
	private int[] parent;
	private int[] rank;
	
	public Sets(int size) {
		parent = new int[size];
		rank = new int[size];
		
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int element) {
		if (parent[element] != element) {
			parent[element] = find(parent[element]);
		}
		return parent[element];
	}
	
	public void union(int set1, int set2) {
		int root1 = find(set1);
		int root2 = find(set2);
		
		if (root1 != root2) {
			parent[root2] = root1;
		} else if (rank[root1] < rank[root2]){
			parent[root1] = root2;
		} else {
			parent[root2] = root1;
			rank[root1]++;
		}
	}
}

public class ACh11_MinimalSpanningTree {
	// The main function to construct MST using Kruskal's algorithm
//	주어진 그래프에서 엣지를 추가하는 함수
	static int addEdgeSet(Edge[] edgeSet, int e, int from, int to, int w) {
		edgeSet[e].src = from;
		edgeSet[e].dest = to;
		edgeSet[e].weight = w;

		return e + 1;
	}

//	그래프의 인접 리스트를 읽어 edge set를 만듦
	static int makeEdgeSet(Graph grp, Edge[] edgeSet, int e) {
		// graph의 adjacency list를 읽어 edge set를 만든다
		for (int i = 0; i < grp.adjacencyMatrix.length; i++) {
			for (int j = i + 1; j < grp.adjacencyMatrix[i].length; j++) {
				if (grp.adjacencyMatrix[i][j] != 0) {
					e = addEdgeSet(edgeSet, e, i, j, grp.adjacencyMatrix[i][j]);
				}
			}
		}
		return e;
	}

	static void KruskalMST(Graph graph, int n) {
		Edge[] edgeSet = new Edge[30];
		Edge result[] = new Edge[30]; // MST
		for (int p = 0; p < 30; p++) {
			edgeSet[p] = new Edge();
			result[p] = new Edge();
		}

		int t = 0; // MST result[]
		int edgeNum = 0; // edgeSet
		edgeNum = makeEdgeSet(graph, edgeSet, edgeNum);

		Heap hp = new Heap(100);
		for (int j = 0; j < edgeNum; j++) {
			hp.Insert(edgeSet[j]);
		}

		Sets m = new Sets(20);

		while (t < n - 1)// t contains less than n-1 edges
		{
			// choose an edge (v,w) from E of lowest cost

			// Else discard the next_edge
		}
		if (t < n - 1) {
			System.out.println("no spanning tree");
			return;
		}
		// MST 출력

		for (int k = 0; k < t; k++)
			System.out.println(result[k] + " ");
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
