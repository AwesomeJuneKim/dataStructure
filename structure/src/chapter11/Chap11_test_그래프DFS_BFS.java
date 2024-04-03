package chapter11;

/*
 Graph Representation
 Adjacency Lists + BFS + DFS
*/

import java.util.Scanner;

class ListNode {
	int data;
	ListNode link;

	public ListNode(int data) {
		this.data = data;
		link = null;
	}
}

class List {
	ListNode first;
	public List() {
		first = null;
	}
	void Insert(int k) {//같은 값을 체크하지 않아 중복 입력됨
		// 구현할 부분
		ListNode temp= new ListNode(k);
		if(first==null) {
			first=temp;//리스트가 비어있으므로 새 리스트노드를 만들어서 삽입한다.
			return;
		}
		ListNode p=first, q=null;
		while(p!=null) {
			if(p.data<k) {
				q=p;
				p=p.link;
			}else {
				if(p.data==k) //등록할 수 없음
					return;
				else {
					if(q==null) {
						temp.link=p;
						first=temp;
						return;
					}else {
						q.link=temp;
						temp.link=p;
						return;
					}
				}
			}
		}
		q.link=temp;
		return;
	}
	void Delete(int k) {
		// 구현할 부분
		ListNode temp= new ListNode(k);
		ListNode p=first, q=null;
		while(p!=null) {
			if(p.data==k) {
				if(q==null)
					first=p.link;
				else
					q.link=p.link;
			}
			q=p;
			p=p.link;
		}
	}
}

class ListIterator {

	private List list;
	private ListNode current;

	public ListIterator(List l) {
		list = l;
		current = list.first;
	}

	int First() {
		if (current != null)
			return current.data;
		else
			return 0;
	}

	int Next() {
		int data = current.data;
		current = current.link;
		return data;
	}

	boolean NotNull() {
		if (current != null)
			return true;
		else
			return false;
	}

	boolean NextNotNull() {
		if (current.link != null)
			return true;
		else
			return false;
	}


}

class QueueNode {
	int data;
	QueueNode link;

	QueueNode(int data, QueueNode link) {
		this.data = data;
		this.link = link;
	}
}

class Queue {
	private QueueNode front, rear;

	void QueueEmpty() {
		front = rear = null;
	}

	public Queue() {
		QueueEmpty();
	}

	boolean IsEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	void Insert(int y) {
		// 구현할 부분
		if(front==null) {
			front=rear= new QueueNode(y,null);
		}else {
			rear.link=new QueueNode(y,null);
			rear=rear.link;
		}
	}

	int Delete() {// 잘 모르겠음
	// 구현할 부분
		if(front==null) {
			QueueEmpty();
			System.out.println("큐가 비어있습니다.");
			return 0;
		}else {
			int result= front.data;
			front=front.link;
			if(front==null) {
				rear=null;
			}
			return result;
		}
	}
}
class StackNode {
	ListNode data;
	StackNode link;

	StackNode(ListNode data, StackNode link) {
		this.data = data;
		this.link = link;
	}
}
class Stack {
	private StackNode top;

	void StackEmpty() {
		top = null;
	}

	public Stack() {
		StackEmpty();
	}

	boolean IsEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	void Insert(ListNode y) {
		// 구현할 부분
//		ListNode temp= new ListNode(y,null);
	}

	ListNode Delete()
	// delete the top node in stack and return its data
	{
		// 구현할 부분
//		ListNode p=top, q=null;
	}
}


class Graph {
	private List[] HeadNodes;//헤드노드가 배열로 나열되어 있다.
	int n;
	boolean[] visited;

	public Graph(int vertices) {
		int[][]a=new int[3][];//행을 3개 만들고
		for(int i=0; i<a.length; i++)
			a[i]=new int[4];//한개의 행에 요소가 4개가 들어갈 자리를 만들어 준다.
		n = vertices;
		HeadNodes = new List[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			HeadNodes[i] = new List();
			visited[i] = false;
		}
		
	}

	void displayAdjacencyLists() {
		for (int i = 0; i < n; i++) {
			// 구현할 부분
			System.out.print("\n"+i+"[ ]");
			ListNode p=HeadNodes[i].first;//왜 .first를 썼는지 이해하기
			while(p!=null) {
				System.out.println("->"+p.data);
			}
		}
		System.out.println();
	}

	void InsertVertex(int start, int end) {//(0,1) 전달 시
		if (start < 0 || start >= n || end < 0 || end >= n) {
			System.out.println("the start node number is out of bound.");
			return;
		}
		HeadNodes[start].Insert(end);//(0,1)을 추가하는 경우 헤드노드 0을 찾고 insert 1을 한다는 의미
		HeadNodes[end].Insert(start);

		// 구현할 부분
	}

	void BFS(int v) {
		boolean[] visited = new boolean[n]; // visited is declared as a Boolean 
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited
		// 구현할 부분
		_BFS(v);
	}
	void _BFS(int v) {
		//queue를 사용하여 구현
		visited[v]=true;
		System.out.print(v+", ");
		ListIterator li= new ListIterator(HeadNodes[v]);
		if(!li.NotNull())//헤드노드배열이 비어있는 경우
			return;
		int w=li.First();
		while(true) {
			if(!visited[w])
				_BFS(w);
			if(!li.NotNull())
				w=li.Next();
			else
				return;
		}
	}
	void ShowList(List l) {
		ListIterator li = new ListIterator(l);
		// 구현할 부분
	}

	// Driver->메인에서 부르는 메서드
	void DFS(int v) {
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited

		_DFS(v); // start search at vertex 0
//		_NonRecursiveDFS(v); //스택을 사용하지 않음 //스택을 사용한 non recursive

	}

	// Workhorse
	void _DFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		visited[v] = true;
		System.out.println(v + ", ");
		ListIterator li = new ListIterator(HeadNodes[v]);//헤드노드배열을 참조함
		if (!li.NotNull())//헤드노드배열이 비어었는 경우
			return;
		int w = li.First();//배열의 첫번째 요소를 참조 함
		while (true) {
			if (!visited[w])//첫 요소를 방문하지 않았으면
				_DFS(w);//방문하고
			if (li.NotNull())//배열에 데이터가 있는 경우
				w = li.Next();//다음요소를 탐색한다.
			else
				return;
		}
	}
	// Workhorse 2
		void _NonRecursiveDFS(int v)
		// visit all previously unvisited vertices that are reachable from vertex v
		{
			// 구현할 부분
			
		}
}
public class Chap11_test_그래프DFS_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int select = 10;//메뉴선택하는 숫자를 의미한다(enum을 사용하지 않고 나타냈음)
		int n;//노드갯수
		int startEdge = -1, endEdge = -1;
		int startBFSNode = 0;// the start node to BFS

		System.out.println("vertex 숫자 입력: ");

		n = sc.nextInt();

		Graph g = new Graph(n);

		while (select != '0') {
			System.out.println("\n명령 선택 1: edge 추가, 2: Adjacency Lists 출력, 3: BFS, 4: DFS, 5: 종료 => ");
			select = sc.nextInt();
			switch (select) {
			case 1:/*
				System.out.println("edge 추가: from vertext: ");
				startEdge = sc.nextInt();
				System.out.println("to vertex: ");
				endEdge = sc.nextInt();
				if (startEdge < 0 || startEdge >= n || endEdge < 0 || endEdge >= n) {
					System.out.println("the input node is out of bound.");
					break;
				}
				// get smallest start node.
				if (startEdge < startBFSNode)
					startBFSNode = startEdge;
				if (endEdge < startBFSNode)
					startBFSNode = endEdge;
				g.InsertVertex(startEdge, endEdge);
				*/
				int[][] inputData= {{0,1},{0,2},{0,3},{1,4},{2,4},{3,4},{3,5},{4,5}};
				for(int i=0;i<inputData.length;i++)
					g.InsertVertex(inputData[i][0], inputData[i][1]);
				break;
			case 2:
				// display
				g.displayAdjacencyLists();
				break;

			case 3:
				System.out.println("Start BFS from node: " + startBFSNode);
				g.BFS(startBFSNode);
				break;
			case 4:
				System.out.println("Start DFS from node: " + startBFSNode);
				g.DFS(startBFSNode);
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  " + "\n" + "Re-Enter");
				break;
			}
		}

		return;
	}
}
