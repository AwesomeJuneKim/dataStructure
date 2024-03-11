package doneAssign;

public class Test_실습2_14스트링배열정렬 {

	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};
		System.out.println("원본 데이터: ");
		showData(data);
		System.out.println("정렬 데이터: ");
		sortData(data);
		showData(data);
	}
	static void showData(String[]arr) {
		for(int i=0; i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();

	}

	static void swap(String[]arr, int ind1, int ind2) {
		String t= arr[ind1];
		arr[ind1]=arr[ind2];
		arr[ind2]=t;
	}
	static void sortData(String []arr) {
		for(int i=0; i<arr.length;i++)
			for(int j=i+1;j<arr.length;j++ ) {
				if(arr[i].compareTo(arr[j])>0)//compareTo() 사용
					swap(arr,i,j);
			}
	}

	}

