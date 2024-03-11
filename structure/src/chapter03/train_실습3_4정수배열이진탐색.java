package chapter03;

import java.util.Arrays;
import java.util.Random;
public class train_실습3_4정수배열이진탐색 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);// 구현 반복 숙달 훈련

		showList("정렬 전 배열[]:: ", data);
		sortData(data);// 구현 반복 숙달 훈련
		//Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련

		int key = 13;
		int resultIndex = linearSearch(data, key);//교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		//Arrays 클래스에 linear search는 없다
		System.out.println("\nlinearSearch(13): result = " + resultIndex);

		key = 19;
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(19): result = " + resultIndex);
		
		key = 10;
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		
		//!!!!!!!!아래꺼 주석처리하면 안되나요????????
//		Arrays.binarySearch(null, 0)
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);

	}
	static void inputData(int []data) {
		Random rnd= new Random();
		for(int i=0; i<data.length;i++)
			data[i]=rnd.nextInt(100);
	}
	static void showList(String str,int []data) {
		System.out.println(str);
		for(int item: data) {
			System.out.print(item+" ");
		}
		System.out.println();
	}
	
	static void sortData(int[]data) {
		for(int i=0;i<data.length;i++) {
			for(int j=i+1;j<data.length;j++) {
				if(data[i]>data[j]) {
					int t= data[i];
					data[i]=data[j];
					data[j]=t;
				}
			}
		}
	}


	static int linearSearch(int[]item, int key) {
		//교재 p109
		int i=0;
		while(i<item.length) {
			if(item[i]==key)
				return i;
			i++;
		}
		return -1;

	}

	static int binarySearch(int[]item, int key) {
		int pl = 0;
		int pr = item.length-1;
		while(pl<=pr) {
			int mid=(pl+pr)/2;
			if(item[mid]==key) {
				return mid;// 찾았을때 값 반환 
			}if(item[mid]<key){
				pl=mid+1;
			}else {
				pr=mid-1;
			}
		}return -1;//찾지 못했을 때 

	}
}








