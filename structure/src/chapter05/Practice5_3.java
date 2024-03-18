package chapter05;

import java.util.Scanner;

public class Practice5_3 {
	static class Recur{
		static void recur(int n) {
			while(n>0) {
				recur(n-1);
				System.out.println(n);
				n=n-2;
			}
		}
	}

	public static void main(String[] args) {
		Scanner scn= new Scanner (System.in);
		
		System.out.print("정수를 입력하세요: ");
		int x=scn.nextInt();
		Recur.recur(x);
		
	
	}
	

}
