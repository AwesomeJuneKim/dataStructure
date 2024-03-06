package assignments;

public class Test_실습2_14스트링배열정렬 {
public class 실습2_14스트링배열정렬 {
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		sortData(data);
		showData(data);
	}
	static void showData(String[]arr) {

	}

	static void swap(String[]arr, int ind1, int ind2) {
		
	}
	static void sortData(String []arr) {
		for(int i=0; i<arr.length;i++)
			for(int j=i+1;j<arr.length;j++ ) {
				if(arr[i]>arr[j])//compareTo() 사용
					swap(arr,i,j);
			}
	}

	}

}
