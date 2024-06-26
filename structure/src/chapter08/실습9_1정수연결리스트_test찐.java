package chapter08;
//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}

	public boolean Delete(int element) // delete the element
	{
		Node1 q=null; 
		Node1 p = first;
		while(p != null) {
			if(p.data==element) {
				if(q==null) {//첫번째 값을 삭제할 때
					first=p.link;//current를 삭제하고 다음값을 연결한다.
				}else {//첫번째 값이외의 나머지를 삭제할 때
					q.link=p.link;
					return true;
				}
			
			}else{
				q = p;
				p=p.link;
			}
		}
		return false;// 삭제할 대상이 없다.
	}
	
	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node1 p = first;
		System.out.println("***리스트출력***");
		while(p!=null) {
			System.out.print(p.data+" > ");
			p=p.link;
		}
		System.out.println();
//		if(p==null)
//			System.out.println("선택한 노드가 없습니다.");
//		else
//			System.out.println("출력된 노드:"+p.data);

	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node1 newNode = new Node1(element);
		Node1 p=first, q=null;//first는 첫번째에 고정 p만 움직인다.
		
		if(first==null) {//first가 나타낼 node가 없는 경우
			first=newNode;//새로운 객체를 연결시킴
			return;
		}
			while(p!=null) {//first가 참조하는 노드가 존재하는 경우
				if(element>p.data){//p가 가리키는 데이터보다 새로 넣을 데이터가 크면 옮겨 감 
					q=p;
					p=p.link;
				}else {
					if(q==null){//데이터를 처음 넣는 경우 
						first=newNode;
						newNode.link=p;
						return;
						}else {//중간에 넣는 경우
							q.link=newNode;
							newNode=p;
							return;
				}
			}
		}
			q.link=newNode;//마지막에 넣는 경우(p==null)일 때 
	}
	

	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		Node1 ptr = first;
		while(ptr!=first) {
			System.out.print(ptr.data+" > ");
		}
		System.out.println();

		return false;
	}
}

public class 실습9_1정수연결리스트_test찐 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			//Menu class의 MenuAt이라는 메서드를 의미한다.
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}
		//"Add" 상수가 정의될 때 Menu("삽입") 생성자가 호출되어 message 필드가 "삽입"으로 초기화
		//생성자는 각 상수가 정의될 때 호출되며, 해당 상수의 message 필드를 초기화하는 역할
		//enum 상수가 언제 정의되는가를 찾아 보아야 한다 
		Menu(String string) { // 생성자(constructor)가 언제 호출되는지 파악하는 것이 필요하다 
			message = string;
			System.out.println("\nMenu 생성자 호출:: " + string);
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
			for (Menu m : Menu.values()) {//enum클래스의 모든상수를 배열로 반환한다//Menu 생성자 호출 됨
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴 참조 변수일 뿐이다 
		Random rand = new Random();
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		l.Show();
		do {
			switch (menu = SelectMenu()) {//Menu 생성자 호출 - menu 객체를 리턴한다 
			case Add: //[0]일때를 말하는 것과 같다.
				data = rand.nextInt(20);
				l.Add(data);
				System.out.print("삽입후 리스트: ");
				l.Show();
				break;
			case Delete: 
				System.out.print("삭제할 데이터: ");
				data = sc.nextInt();
				boolean num = l.Delete(data);
				System.out.println("데이터 삭제 유무: " + num);
				break;
			case Show: 
				l.Show();
				break;
			case Search: // 회원 번호 검색
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + " 데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + " 데이터가 존재합니다.");
				break;
			case Exit: 
				break;
			}
		} while (menu != Menu.Exit);
	}
}
