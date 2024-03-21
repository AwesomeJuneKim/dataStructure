package assignments;

import java.util.ArrayList;
import java.util.List;

enum Directions2 {N, NE, E, SE, S, SW, W, NW}
class Items3 {
	int x;//현재위치의 x
	int y;//현재위치의 y
	int dir;//다음 이동방향 
	public Items3(int x, int y, int d) {
		this.x = x; this.y = y; this.dir = d;
	}
	@Override
	public String toString() {
		return "x = " + x + ", y = " + y + ", dir = " + dir;
	}
}
class Offsets3 {//현재위치에서 다음위치로 이동할때 방향에 대한 정보 
	int a;//x좌표의 변화량 
	int b;//y좌표의 변화량
	public Offsets3(int a, int b) {
		this.a = a; this.b = b;
	}
}
	class StackList {
	private List<Items3> data; // 스택용 배열
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 실행시 예외 : 스택이 비어있음 ---//
	public class EmptyIntStackException extends RuntimeException {
		public EmptyIntStackException() {
		}
	}

	// --- 실행시 예외 : 스택이 가득 참 ---//
	public class OverflowIntStackException extends RuntimeException {
		public OverflowIntStackException() {
		}
	}

	// --- 생성자(constructor) ---//
	public StackList(int maxlen) {
		top = 0;
		capacity = maxlen;
		try {
			data = new ArrayList<>(0); // 스택 본체용 배열을 생성
		} catch (OutOfMemoryError e) { // 생성할 수 없음
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Items3 p) throws OverflowIntStackException {
		if (top >= capacity) // 스택이 가득 참
			throw new OverflowIntStackException();
		data.add(p);top++;
		return;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Items3 pop() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.remove(--top);
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Items3 peek() throws EmptyIntStackException {
		if (top <= 0) // 스택이 빔
			throw new EmptyIntStackException();
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(벌견하지 못하면 –1)를 반환 ---//
	public int indexOf(Items3 x) {
		for (int i = top - 1; i >= 0; i--) // 정상 쪽에서 선형검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("스택이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

	public class Test_MazingProblem_미로찾기 {

		static Offsets3[] moves = new Offsets3[8];//static을 선언하는 이유를 알아야 한다

		public static void path(int[][] maze, int[][] mark, int ix, int iy) {
			//offset=다음위치의 방향에 대한 정보
			//Item= 현재위치의 정보 

			mark[1][1] = 1;//미로의 출발지점을 표시 함
			StackList st = new StackList(50);//현재위치를 저장할 스택을 생성
			Items3 temp = new Items3(0, 0, 0);//N :: 0 현재위치를 temp에 저장
			temp.x = 1;//x좌표가 1
			temp.y = 1;//y좌표가 1
			temp.dir = 2;//E:: 2 북쪽, 북서쪽으로 갈 수 없으므로 동쪽인 2로 설정 함
			mark[temp.x][temp.y] = 2;//미로 찾기 궤적은 2로 표시
			st.push(temp);//현재 위치를 스택에 저장
			
			boolean flag=false;
			while (!st.isEmpty()) // stack not empty 스택이 비어있지 않음->좌표를 더 넣을 수 있음->미로찾기를 계속할 수 있음
			{//현재 위치에서 다른위치로 움직여야 함
				Items3 tmp = st.pop(); // unstack 직전의 위치를 없어줌
				int i = tmp.x;
				int j = tmp.y;
				int d = tmp.dir;
				mark[i][j] = 1;//backtracking 궤적은 1로 표시//이미 지나온 곳을 1로 표시함
				while (d < 8) {// 모든 방향으로 움직임을 시도 함
					int g=i+moves[d].a;//1~8의 방향으로 움직일때의 x변화량인 a를 원래의 i에 더해서 새로운 자리를 지정한다.
					int h=j+moves[d].b;
					
					if ((g== ix) && (h == iy)) { // 출구에 도착한 경우
						// output path
						mark[i][j]=2;
						mark[g][h]=2;//미로찾기 궤적
						flag=true;
						break;
					}
					if((maze[g][h]==0) && (mark[g][h]==0)){//새로운 위치인 경우
						Items3 next= new Items3(i,j,d+1);//현재위치정보를 객체로 만듦
						mark[i][j]=2;//미로찾기 궤적이므로 2를 표시한다.
						st.push(next);//만든 객체를 스택에 저장 함
						i=g;//i에 g를 다시 대입해서 while루프를 반복하게 함
						j=h;
						d=0;//다른방향에서 탐색시작
					}else {
						d++; 
					} 

				}
				if(flag)
					break;
			}
			System.out.println("no path in maze ");
		}
		static void showMatrix(int[][]d, int row, int col) {
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.print(d[i][j] + " ");

				}
				System.out.println();
			}
		}
		public static void main(String[] args) {
			int[][] maze = new int[14][17];
			int[][] mark = new int[14][17];

			int input[][] = { // 12 x 15
					{ 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
					{ 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
					{ 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
					{ 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
					{ 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
					{ 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
					{ 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
					{ 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
					{ 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }};
			for (int ia = 0; ia < 8; ia++)
				moves[ia] = new Offsets3(0, 0);//배열에 offsets 객체를 치환해야 한다.
			moves[0].a = -1;	moves[0].b = 0;
			moves[1].a = -1;	moves[1].b = 1;
			moves[2].a = 0;		moves[2].b = 1;
			moves[3].a = 1;		moves[3].b = 1;
			moves[4].a = 1;		moves[4].b = 0;
			moves[5].a = 1;		moves[5].b = -1;
			moves[6].a = 0;		moves[6].b = -1;
			moves[7].a = -1;	moves[7].b = -1;
			//Directions d;
			//d = Directions.N;
			//d = d + 1;//java는 지원안됨
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 17; j++) {
				if(i==0 || j==0||i==13||j==16) {
					maze[i][j]=1;
				}else {
					maze[i][j]=input[i-1][j-1];
				}
							

					// input[][]을 maze[][]로 변환
				}
			}
			System.out.println("maze[12,15]::");
			showMatrix(maze, 13, 16);
		
			System.out.println("mark::");
			showMatrix(mark, 13, 16);

			path(maze, mark, 12, 15);
			System.out.println("mark::");
			showMatrix(mark, 13, 16);
		}
	}

