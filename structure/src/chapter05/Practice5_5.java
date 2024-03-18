package chapter05;

import java.util.Scanner;

public class Practice5_5 {
    static class Recur {
        static void recur(int n) {
            IntStack s = new IntStack(n); // IntStack 객체 생성
            while (true) {
                if (n > 0) {
                    s.push(n);
                    n = n - 1;
                    continue;
                }
                if (!s.isEmpty()) {
                    n = s.pop();
                    System.out.println(n);
                    n = n - 2;
                    continue;
                }
                break;
            }
        }
    }

    // IntStack 클래스 정의
    static class IntStack {
        private int max; // 스택 용량
        private int ptr; // 스택 포인터
        private int[] stk; // 스택 본체

        // 생성자
        public IntStack(int capacity) {
            ptr = 0;
            max = capacity;
            try {
                stk = new int[max]; // 스택 본체용 배열을 생성
            } catch (OutOfMemoryError e) { // 생성할 수 없음
                max = 0;
            }
        }

        // 스택에 x를 푸시
        public int push(int x) throws OverflowIntStackException {
            if (ptr >= max) // 스택이 가득 참
                throw new OverflowIntStackException();
            return stk[ptr++] = x;
        }

        // 스택에서 데이터를 팝 (꼭대기의 데이터를 꺼냄)
        public int pop() throws EmptyIntStackException {
            if (ptr <= 0) // 스택이 비어 있음
                throw new EmptyIntStackException();
            return stk[--ptr];
        }

        // 스택에서 데이터를 피크 (꼭대기의 데이터를 들여다봄)
        public int peek() throws EmptyIntStackException {
            if (ptr <= 0) // 스택이 비어 있음
                throw new EmptyIntStackException();
            return stk[ptr - 1];
        }

        // 스택에서 x를 찾아 인덱스(없으면 -1)를 반환
        public int indexOf(int x) {
            for (int i = ptr - 1; i >= 0; i--) // 꼭대기쪽부터 선형 검색
                if (stk[i] == x)
                    return i; // 검색 성공
            return -1; // 검색 실패
        }

        // 스택을 비움
        public void clear() {
            ptr = 0;
        }

        // 스택의 용량을 반환
        public int capacity() {
            return max;
        }

        // 스택에 쌓여있는 데이터 수를 반환
        public int size() {
            return ptr;
        }

        // 스택이 비어 있는가?
        public boolean isEmpty() {
            return ptr <= 0;
        }

        // 스택이 가득 찼는가?
        public boolean isFull() {
            return ptr >= max;
        }
        public class EmptyIntStackException extends RuntimeException {
    		public EmptyIntStackException() {
    			super("스택이 비어있습니다.");
    		}
    	}

    //--- 실행시 예외: 스택이 가득 참 ---//
    	public class OverflowIntStackException extends RuntimeException {
    		public OverflowIntStackException() {
    			super("스택이 가득찼습니다.");
    		}
    	}
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.print("정수를 입력하세요: ");
        int x = scn.nextInt();
        Recur.recur(x);
    }
}
