package practice;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;
public class train_실습2_5정수배열정렬 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		/*
		sortData(data); 정렬프로그램을 사용하는것
		showData(data);
		*/
		reverse(data);//역순으로 재배치 - 정렬 아님 
		showData(data);

		reverseSort(data);//역순으로 재배치 - 정렬 아님 
		showData(data);
		
		Arrays.sort(data);//자바 라이브러리를 이용
	}
	static void showData(int[]data) {
		for(int num:data) {
			System.out.print(num+" ");
		}
		System.out.println();

	}
	static void inputData(int []data) {
		Random rnd= new Random();
		for(int i=0; i<data.length;i++) {
			data[i]=rnd.nextInt(100);
		}

	}
	static void swap(int[]arr, int ind1, int ind2) {//교재 67페이지
		int t=arr[ind1];
		arr[ind1]=arr[ind2];
		arr[ind2]=t;
	}
	static void sortData(int []arr) {//swap함수를 사용한다.두개의 데이터를 맞바꾸는것을 의미함
		for(int i=0; i<arr.length;i++) {
			for(int j=i+1; j<arr.length;j++) {
				if(arr[i]>arr[j]) 
					swap(arr,i,j);
			}
		}

	}
	static void reverse(int[] a) {//교재 67페이지
		for(int i=0; i<a.length/2;i++) {
			swap(a,i,a.length-i-1);
		}

	}
	static void reverseSort(int []arr) {
		for(int i=0; i<arr.length;i++) {
			for(int j=i+1; j<arr.length;j++) {
				if(arr[i]<arr[j])
					swap(arr,i,j);
			}
		}
	}

}
