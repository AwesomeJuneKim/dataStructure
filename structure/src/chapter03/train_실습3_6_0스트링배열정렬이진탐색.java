package chapter03;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
public class train_실습3_6_0스트링배열정렬이진탐색 {


	static void reverse(String[] a) {//교재 67페이지

	}
	public static void main(String[] args) {
		String []data = {"사과","포도","복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외"};//홍봉희 재배 과수

		showData("정렬전: ", data);
		sortData(data);
		showData("정렬후: ", data);
		reverse(data);//역순으로 재배치
		showData("역순 재배치후: ", data);
		Arrays.sort(data);//Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후: ",data);
      
		String key = "포도";
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		//!!!!!!!!!!!왜 -1이 반환되지 않는거지???????????????
		//->linearSearch의 while이 닫히지 않아서 무한루프 돌고 있었음
		/*
		 * 교재 109~113
		 */
		
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}
	
	static int linearSearch(String[] data, String key) {
		int i=0;
		while(i<data.length) {
			if(data[i].equals(key)) {
				return i;
			}
			i++;
			}
		return -1;
	}
	
	static int binarySearch(String[] data, String key) {
		int left=0;
		int right=data.length-1;
		while(left<=right) {
			int mid=(left+right)/2;
			if(data[mid]==key)
				return mid;
			if(data[mid].compareTo(key)<0)
				left=mid+1;
			else
				right=mid-1;
		}
		return -1;
	}
	
	static void sortData(String[] data) {
		for(int i=0;i<data.length;i++)
			for(int j=i+1; j<data.length;j++)
				if(data[i].compareTo(data[j])>0)
					swap(data,i,j);
		
	}
	static void showData(String string, String[] data) {
		System.out.println(string);
		for(int i=0;i<data.length;i++)
			System.out.print(data[i]+" ");
		System.out.println();
		
	}
	static void swap(String[]arr, int ind1, int ind2) {
		String t= arr[ind1];
		arr[ind1]=arr[ind2];
		arr[ind2]=t;
	}


}
