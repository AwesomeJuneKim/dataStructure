package chapter10;

import java.util.Scanner;
//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

class Node {
	int key; // 키값
	Node next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	public Node(int key, Node next) {
		this.key = key;
		this.next = next;
	}
}

class SimpleChainHash {
	private int size; // 해시 테이블의 크기
	private Node[] table; // 해시 테이블

	// 생성자 만들기
	public SimpleChainHash(int capacity) {
		try {
			table = new Node[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}

	}
	public int hashValue(int key) {
		return key % size;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(int key) {
		int hash=hashValue(key);//key값의 주소를 hash에 참조
		Node p= table[hash];//key값의 주소를 가진 테이블의 노드를 p로 설정
		
		while(p!=null) {
			if(p.key==key) {
				return p.key;
			}
			p=p.next;//다음검색으로 넘어 감
		}
		return -1;//검색 실패

	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int hash= hashValue(key);//table의 인덱스를 계산
		Node p=table[hash];
		
		while(p!=null) {//p는 인덱스를 이미 알고 있으므로 해당 링크드 리스트를 순회한다.
			if(p.key==key) {//이미 같은키가 존재하면
				return 1;//등록할 수 없다
				}
			p=p.next;//아닐경우 마지막노드를 찾는다.
			}
			//마지막 노드에 새 노드 생성
			Node temp= new Node(key,table[hash]);
			table[hash]=temp;
			
		return 0;
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int delete(int key) {
		int hash=hashValue(key);
		Node p=table[hash], q=null;
		while(p!=null) {
			if(p.key==key) {
				if(q==null)//첫번째 데이터인 경우
					table[hash]=p.next;
				else
					q.next=p.next;
				return 0;//삭제 완료
			}
			q=p;
			p=p.next;//찾으러 돌아다님
		}
		return 1;//삭제 실패

	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for(int i=0;i<size;i++) {
			Node p=table[i];
			System.out.println("["+i+"]");
			while(p!=null) {
				System.out.print(p.key+" > ");
				p=p.next;
			}
			System.out.println();
		}
	}
}

public class Test_실습10_1정수체인해시 {

	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

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
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

//체인법에 의한 해시 사용 예
	public static void main(String[] args) {
		Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:

				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data");
				}
				break;
			case Delete:
				// Delete
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);

	}
}
