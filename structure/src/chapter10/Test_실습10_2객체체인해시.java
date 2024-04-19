package chapter10;


import java.util.Comparator;
//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Scanner;

//체인법에 의한 해시

class SimpleObject5 {
	static final int NO = 1;
	static final int NAME = 2;
	public static final Comparator<? super SimpleObject5> NO_ORDER = null;
	public static final Comparator<? super SimpleObject5> NO_ORDER = null;
	int no; // 회원번호
	String name; // 이름
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+no+"]-"+name;
	}
	public SimpleObject5() {
		// TODO Auto-generated constructor stub
		no=-1;
		name=null;
	}
	public SimpleObject5(int no, String name){
		this.no=no;
		this.name=name;
	}
	public void scanData(String string, int i) {
		// TODO Auto-generated method stub
		
	}

	
}

class ChainHash5 {
//--- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)
		// --- 생성자(constructor) ---//

		Node2(SimpleObject5 data){
			this.data=data;
			this.next=null;
			}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

//--- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		this.size=capacity;
		table= new Node2[capacity];
		
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx=st.no%size;
		Node2 p=table[idx];
		
		while(p!=null) {
			if(c.compare(p.data, st)<0) {
				p=p.next;
			}else {
				if(c.compare(p.data, st)==0) {
					return 1;
				}else {
					return -1;
				}
			}
		}
		return -1;
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx=st.no%size;
		Node2 p=table[idx];
		Node2 q=null;
		Node2 temp= new Node2(st);
		
		while(p!=null) {
			if(c.compare(p.data, temp.data)<0) {//새 데이터가 더 큰 경우 
				q=p;
				p=p.next;
			}else {//새데이터가 더 작거나 같은 경우 
				if(c.compare(p.data, temp.data)==0) {//새 데이터와 같은 경우
					return 1;
				}else {//새 데이터가 작은 경우 
					if(q==null){//새 데이터가 첫데이터 보다 작은 경우
						 temp.next=p;
						 table[idx]=temp;
						 return 0;
					}else {
						q.next=temp;
						temp.next=p;
						return 0;
					}
				}
			}
			//맨뒤에 넣는 경우
			q.next=temp;
			return 1;
		}
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int idx=st.no%size;
		Node2 p=table[idx];
		Node2 q=null;
		
		while(p!=null) {
			if(c.compare(p.data, st)==0) {
				if(q==null) {//맨 앞 데이터 삭제 
					table[idx]=p.next;
				}else {//중간데이터 삭제 
					q.next=p.next;
				}
				return 1;//삭제 완료 
				
			}else {
				q=p;
				p=p.next; //찾으러 돌아다님 
			}
			return 0;//삭제 미완료 
		}
		return 0;
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for(int i=0;i<table.length;i++) {
			System.out.print("["+i+"]");
			Node2 p=table[i];
			if(table[i]==null) {
				System.out.print("No Data");
				return;
			}
			while(p!=null) {
				System.out.print(p.next!=null ? p.data+">" :p.data);
			}
		}
		System.out.println();
	}
}

public class Test_실습10_2객체체인해시 {
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


	public static void main(String[] args) {
		Menu menu;
		Scanner stdIn = new Scanner(System.in);
		ChainHash5 hash = new ChainHash5(15);
		SimpleObject5 data;
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
