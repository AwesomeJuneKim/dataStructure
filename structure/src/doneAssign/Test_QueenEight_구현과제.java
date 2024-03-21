package doneAssign;

//해가 256개 확인 필요 23.12.12
import java.util.ArrayList;
import java.util.List;

import doneAssign.Stack4.EmptyGenericStackException;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
 * 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
 * Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
 * pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
 * Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
 * 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
 */
class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point) p).ix) && (this.iy == ((Point) p).iy))
			return true;
		else
			return false;
	}
}

class Stack4 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Point> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack4(int capacity) {
		this.capacity=capacity;
		top=0;
		try {
			data= new ArrayList<>();
		}catch(OutOfMemoryError e){
			capacity=0;
		}

	}

	// --- 스택에 x를 푸시 ---//
	public boolean push(Point x) throws OverflowGenericStackException {
		if (isFull()) { // 스택이 가득 참
			throw new OverflowGenericStackException("스택이 가득찼습니다.");
		} else {
			data.add(x);
			top++;
		}return true;

	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyGenericStackException {
		 if (isEmpty()) { // 스택이 빔
		        throw new EmptyGenericStackException("스택이 비어있습니다.");
		    } else {
		        top--;
		        return data.remove(top); // 리스트에서 요소 제거하고 반환
		    }
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point peek() throws EmptyGenericStackException {
		if (isEmpty()) { // 스택이 빔
			throw new EmptyGenericStackException("스택이 비어있습니다.");
		}else {
			return data.get(top-1);
		}

	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
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

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException{
		if (top <= 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class Test_QueenEight_구현과제 {
	public static void EightQueen(int[][] d) {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0;
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack4 st = new Stack4(100); // 100개를 저장할 수 있는 스택을 만들고
		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고
		d[ix][iy] = 1;// 현 위치에 queen을 넣었다는 표시를 하고
		count++;
		ix++;//다음 행으로 이동
		st.push(p);// 스택에 현 위치 객체를 push
		while (true) {
			int nextCol=nextMove(d, ix, iy);//nextMove(...)를 nextCol에 대입해서 사용할 준비
			if(nextCol!=-1) {//체스판에 퀸을 놓을 자리가 있으면
				iy=nextCol;//가능한 자리에 객체를 만들어서 퀸이 들어갈 자리는 만들어 줌
				st.push(new Point(ix,iy));//퀸이 들어간 자리의 객체를 스택에 푸시해 줌
				d[ix][iy]=1;//배열에 1을 남김
				ix++;//다음행으로 넘어감
				iy=0;//다음행의 첫번째 열에 해당하는 요소에 위치함
				count++;//퀸의 갯수를 증가시킴
				
				
				if(count==8) {//한판의 퀸 8개를 다 찾은 경우
					numberSolutions++;// 솔루션의 갯수를 증가시킴
					System.out.println("["+numberSolutions+"]");//솔루션을 구별할 수 있는 고유번호 출력
					showQueens(d);//한판 출력
					System.out.println();//한칸 띄움
					try {
						p=st.pop();
					} catch (EmptyGenericStackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ix=p.getX();
					iy=p.getY()+1;//팝한 후 다음열로 이동
					d[ix][iy-1]=0;//이전의 열의 내용을 초기화 해줌
					count--;//아직 답이 안나왔으므로 퀸의 숫자를 줄여 줌
					continue;
				}
				
			}else {
				if(st.isEmpty()) {//솔루션 스택이 비어있다-> 퀸의 위치를 다 찾음
				//팝한 위치를 사용해서 다음 열을 조사하고 더 이상 없으면 종료??
					break;
				}else {
					try {
						p=st.pop();
					} catch (EmptyGenericStackException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ix=p.getX();
					iy=p.getY();
					d[ix][iy]=0;
					count--;
					iy++;
				}
				
			}
		}

	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	//퀸을 남동쪽 방향 대각선으로 이동할 수 있는지 확인
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		int x=cx, y=cy;
		while(x <d.length && y >= 0) {
			if(d[x][y]==1) {
		return false;
			}
			x++;
			y--;
		}
		x=cx; 
		y=cy;
		while(x>=0 && y<d.length) {
			if(d[x][y]==1) {
				return false;
			}
			x--;
			y++;
		}
		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	//+ 퀸을 남서쪽
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		int x=cx, y=cy;
		while(x >= 0 && y >=0) {
			if(d[x][y]==1) {
				return false;
			}
			x--;
			y--;
		}
		x=cx; 
		y=cy;
		while(cx < d.length && cy < d.length) {
			if(d[x][y]==1) {
				return false;
			}
			x++;
			y++;
		}
	return true;

	}

	public static boolean checkRow(int[][] d, int x, int y) {
		for(int i=0; i<8; i++) {
			if(d[x][i]==1) {
				return false;
			}
		}
		return true;
		
	}
	public static boolean checkCol(int[][] d, int x, int y) {
		for(int i=0; i<8;i++) {
			if(d[i][y]==1) {
				return false;
			}
		}
		return true;
		
	}
	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		if(checkRow(d, x, y) & checkCol(d, x, y) & checkDiagSE(d, x, y) & checkDiagSW(d, x, y)==true)
			return true;
		return false;

	}

	// 배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		for(int i=col;i<8;i++) {
			if(checkMove(d,row,i)==true)
				return i;  
		}
		return -1;

	}

	static void showQueens(int[][] data) {// 배열 출력
		for(int [] i: data) {
			for(int j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}

	}                                 

	public static void main(String[] args) {// 체스한판은 원래 다 0으로 구성되어 있음
		int row = 8, col = 8;
		int[][] data = new int[8][8];
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		EightQueen(data);

	}
}