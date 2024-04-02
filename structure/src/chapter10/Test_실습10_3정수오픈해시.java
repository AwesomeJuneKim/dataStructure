package chapter10;

//오픈 주소법에 의한 해시의 사용 예

import java.util.Scanner;

//오픈 주소법에 의한 해시

class OpenHash2 {

	//--- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	} // {데이터 저장, 비어있음, 삭제 완료}

	//--- 버킷 ---//
	static class Bucket {
		private int data; // 데이터
		private Status stat; // 상태

		// 생성자
		Bucket() {
			stat = Status.EMPTY;
		}

		// 값 설정
		void set(int data, Status stat) {
			this.data = data;
			this.stat = stat;
		}
		void setStat(Status stat) {
			this.stat=stat;
		}

		int getValue() {
			return data;
		}

	}
		private int size; // 해시 테이블의 크기
		private Bucket[] table; // 해시 테이블

		//--- 생성자(constructor) ---//
		public OpenHash2(int size) {
			this.size = size;
			table = new Bucket[size];//bucket이 들어가는 테이블을 만들고
			for(int i=0;i<size;i++)
				table[i]=new Bucket();//각각의 배열에 bucket객체를 만든다.

		}

		//--- 해시값을 구함 ---//
		public int hashValue(int key) {
			return Integer.hashCode(key) % size;
		}

		//--- 재해시값을 구함 ---//
		public int rehashValue(int hash) {
			return (hash + 1) % size;
		}

		//--- 키값 key를 갖는 버킷 검색 ---//
		private Bucket searchNode(int key) {// 해당키를 가지는 노드를 찾는다
			int hash = hashValue(key);
			Bucket p = table[hash];
			for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {// 비어있지 않는 p를 검색한다.
				if (p.stat == Status.OCCUPIED) {// p에 데이터가 있고
					if (p.data == key) {// 그 데이터가 찾는 키와 같은 경우
						return p;// 해당 버킷을 반환한다.
					} else {// 못찾은 경우 계속 반복함
						hash = rehashValue(hash);
						p = table[hash];
					}
				}
			}
			return null;// 검색 실패
		}

		//--- 키값이 key인 요소를 검색(데이터를 반환)---//
		public int search(int key) {
			Bucket p = searchNode(key);
			if (p != null) {
				return p.data;// 검색 성공
			}
			return 0;// 검색 실패
		}

		//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
		public int add(int data) {
			int hash = hashValue(data);
			Bucket p = table[hash];
			for (int i = 0; i < size; i++) {
				if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
					p.set(data, Status.OCCUPIED);
					return 0;
				}
				hash=rehashValue(hash);
				p=table[hash];
			}
			return 2;
		}

		//--- 키값이 key인 요소를 삭제 ---//
		public int remove(int key) {
			Bucket p=searchNode(key);
			if(p==null)
				return 1;//삭제할 데이터가 없음
			p.setStat(Status.DELETED);//상태를 삭제로 변경-실제 데이터를 삭제할 필요는 없다.
			return 0;//삭제완료
		}

		//--- 해시 테이블을 덤프(dump) ---//
		public void dump() {
			for(int i=0; i<size;i++) {
				System.out.print(table[i].getValue()+" > ");
			}
			System.out.println();
		}
	}

	public class Test_실습10_3정수오픈해시 {
		//--- 메뉴 열거형 ---//
		enum Menu {
			ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

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

		//--- 메뉴 선택 ---//
		static Menu SelectMenu() {
			Scanner stdIn = new Scanner(System.in);
			int key;
			do {
				for (Menu m : Menu.values())
					System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				System.out.print(" : ");
				key = stdIn.nextInt();
			} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

			return Menu.MenuAt(key);
		}

		public static void main(String[] args) {
			Menu menu; // 메뉴
			int select = 0, result = 0, val = 0;
			final int count = 8;
			Scanner stdIn = new Scanner(System.in);
			OpenHash2 hash = new OpenHash2(8);
			do {
				switch (menu = SelectMenu()) {
				case ADD: // 추가
					int[] input = new int[count];
					for (int ix = 0; ix < count; ix++) {
						double d = Math.random();
						input[ix] = (int) (d * 20);
						System.out.print(" " + input[ix]);
					}
					System.out.println();
					for (int i = 0; i < count; i++) {
						int k = hash.add(input[i]);
						switch (k) {
						case 1:
							System.out.printf("(%d) -> ", input[i]);
							System.out.println("그 키값은 이미 등록되어 있습니다.");
							break;
						case 2:
							System.out.println("해시 테이블이 가득 찼습니다.");
							break;
						}
					}
					break;

				case REMOVE: // 삭제
					System.out.println("Remove Value:: ");
					val = stdIn.nextInt();
					result = hash.remove(val);
					if (result == 0)
						System.out.println(" Delete Complete ");
					else
						System.out.println(" No Data");
					System.out.println();
					break;

				case SEARCH: // 검색
					System.out.println("Search Value:: ");
					val = stdIn.nextInt();
					result = hash.search(val);
					if (result != 0)
						System.out.println(" 검색 데이터가 존재한다");
					else
						System.out.println(" 검색 데이터가 없음");
					System.out.println();
					break;

				case DUMP: // 표시
					hash.dump();
					break;
				}
			} while (menu != Menu.TERMINATE);
		}
	}
