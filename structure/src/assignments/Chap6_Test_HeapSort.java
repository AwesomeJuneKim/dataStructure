package assignments;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	public int n; // current size of MaxHeap 현재 입력된 데이터의 갯수
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public Heap(int sz) {//생성자
		this.MaxSize=sz;
		n=0;// 현재 아무 데이터가 없음
		heap=new int[MaxSize+1];

	}

	public void display() {//출력메서드
		for(int i=1;i<=n;i++)
			System.out.print(heap[i]+" < ");
		System.out.println();

	}
	@Override
	public void Insert(int x) {
		int i;
		if(n==MaxSize) {
			HeapFull();
			return;
		}
		n++;
		for(i=n; i>=1;i /=2) {
			if(i==1) break;
			else if(x<=heap[i/2]) break;
			else {
				heap[i]=heap[i/2];
			}
		}
		heap[i]=x;

	}
	@Override
	public int DeleteMax() {
		if(n==0) {HeapEmpty(); return 0;}
		int x=heap[1];//루트
		int k=heap[n];//마지막 수
		n--;//마지막 수를 빼낸다.
		
		int i=1;
			for(int j=2; j<=n;j*=2) {
				if(j<n) 
					if(heap[j]<heap[j+1]) j++;
				if(k>=heap[j]) break;
				heap[i]=heap[j];
				i=j;
				
				
			}
			heap[i]=k;
		return x;

	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}
public class Chap6_Test_HeapSort {
	 static void showData(int[] d) {
	     for (int i = 0; i < d.length; i++)
	         System.out.print(d[i] + " ");
	     System.out.println();
	 }
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10;//정렬된 배열의 크기
	    int[] x = new int[count+1];
	    int []sorted = new int[count];
	    

		do {
			System.out.print("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1://입력
				int value=rnd.nextInt(1, 50);
				heap.Insert(value);//rnd만 입력하면 안됌
				heap.display();
	
				break;
			case 2:
				heap.display();
				break;
			case 3://정렬 for루프로 delete를 계속 함
				for(int i=1;i<=heap.n;i++) {
					sorted[i-1]=heap.DeleteMax();
				}
				System.out.println("정렬된 데이터 :");
				showData(sorted);
				break;

			case 4:
				System.exit(1);
				return;

			}
		} while (select < 5);

		return;
	}
}
