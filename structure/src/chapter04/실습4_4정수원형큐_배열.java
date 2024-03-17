package chapter04;
/*
 * 큐에서는 예외 클래스를 만드는 연습
 */
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

import chapter04.IntQueue3.EmptyIntQueueException;
import chapter04.IntQueue3.OverflowIntQueueException;

//int형 고정 길이 큐

class IntQueue3 {
	private int[] que; // 큐용 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
//	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueueException extends RuntimeException {
		public EmptyIntQueueException(String message) {
			super(message);
		}
	}

//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowIntQueueException extends RuntimeException {
		public OverflowIntQueueException(String message) {
			super(message);
		}
	}

//--- 생성자(constructor) ---//
	public IntQueue3(int maxlen) {
		front=rear=0;
		this.capacity=maxlen;
		try {
			que= new int[maxlen];
		}catch(OutOfMemoryError e){
			maxlen=0;
		}

	}

//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowIntQueueException {
		if(isFull())
			throw new OverflowIntQueueException("배열이 가득 찼습니다.");
		que[rear++]=x;
		return x;

	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyIntQueueException {
	    if (isEmpty())
	        throw new EmptyIntQueueException("배열이 비어있습니다.");
	    int x = que[front++];
	    if (front == capacity)
	        front = 0; // front가 capacity와 같아지면 0으로 초기화
	    return x;
	}


//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueueException {
		if (isEmpty())
			throw new EmptyIntQueueException("배열이 비어있습니다.");
		return que[front];

	}

//--- 큐를 비움 ---//
	public void clear() throws EmptyIntQueueException {
	    if (isEmpty())
	        throw new EmptyIntQueueException("배열이 비어있습니다.");
	    front = rear = 0;
	}


//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {
		for (int i = 0; i < que.length; i++) {
			int idx = (i + front) % capacity;
			if (que[idx] == x) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if(rear>front) {
			return rear-front;
		}else if(rear<front) {
			return rear-front+capacity;
		}else{
			if((rear+1)%capacity==front) {
				return capacity;
			}else {
				return 0;
			}
				
		}
	}

//--- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		
		return (size()==0);
	}

//--- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		return size()==capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		for(int i=0; i<capacity;i++) {
			System.out.println(que[i]+" ");
		}
		System.out.println();
	}

}

public class 실습4_4정수원형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(IntQueue3.OverflowIntQueueException e) {
					System.out.println("stack이 가득차있습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (IntQueue3.EmptyIntQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p=oq.peek();
					System.out.println("피크한 데이터는"+p+"입니다.");
				} catch (IntQueue3.EmptyIntQueueException e) {
					System.out.println(e.getMessage());
				}

				break;

			case 4: // 덤프
				try {
					oq.dump();
				} catch (IntQueue3.EmptyIntQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: //clear
				try {
					oq.clear();
				}catch(IntQueue3.EmptyIntQueueException e) {
					System.out.println(e.getMessage());
				}

				break;
			default:
				break;
			}
		}
	}

}