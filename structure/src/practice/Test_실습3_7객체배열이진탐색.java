
package practice;

//3장 객체 배열 정렬 - binary search
/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* Arrays.sort(array, myComparator);
* Collections.sort(list, myComparator);
*/

import java.util.Arrays;
import java.util.Comparator;
class Fruit4 {
	String name;
	int price;
	String expire;



	public int getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public Fruit4(String name, int price, String expire) {
		this.name=name;
		this.price=price;
		this.expire=expire;
	}
	public String toString() {
		return "[ name:"+name+"\tprice:"+price+"\texpire: "+expire+"]";
	}
}
//교재 123~129 페이지 참조하여 구현
class FruitNameComparator2 implements Comparator<Fruit4>{
	public int compare(Fruit4 f1, Fruit4 f2) {
		return (f1.price>f2.price)?1:(f1.price<f2.price)?-1:0;
	}


	}
public class Test_실습3_7객체배열이진탐색 {

	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {
//		for(Fruit4 i:arr) {
//			for(Fruit4 j:arr)
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr.length;j++) {
				if(cc_price.compare(arr[i], arr[j])>0) {
					swap(arr,i,j);
				}
			}
		}

	}
	static void swap(Fruit4[]arr, int ind1, int ind2) {
		Fruit4 temp = arr[ind1]; 
		arr[ind1] = arr[ind2]; 
		arr[ind2] = temp;
	}
	static void sortData(Fruit4 []arr, FruitNameComparator2 cc) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++)
				if (cc.compare(arr[i],arr[j])> 0) {
					swap(arr, i, j);
				}
		}
	}

	public static void main(String[] args) {

		Fruit4[] arr = {new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") };
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitNameComparator2 cc = new FruitNameComparator2();
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
		reverse(arr);
		showData("역순 재배치 후", arr);
		
		sortData(arr, cc);
		showData("sortData(arr,cc) 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다
		Comparator<Fruit4> cc_price2 = (a, b) -> a.getPrice() - b.getPrice();
		Arrays.sort(arr, cc_price2); // 람다식으로 만들어진 객체를 사용
		showData("람다식 변수 cc_price2을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);
		
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit4에 compareTo()가 있어도 람다식 우선 적용
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr);

		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("comparator 객체를 사용한 정렬:", arr);
	
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명클래스 사용

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				// TODO Auto-generated method stub
				return (f1.name.compareTo(f2.name));
			}

		};
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용

		};

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
		result3Index = Arrays.binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);

	}
	static int binarySearch(Fruit4[] arr, Fruit4 newFruit4, Comparator<Fruit4> cc_name) {
		// TODO Auto-generated method stub
		int left=0;
		int right=arr.length-1;
		while(left<=right) {
			int mid=(left+right)/2;
			if(arr[mid]==newFruit4) {
				return mid;
			}
			else if(cc_name.compare(arr[mid], newFruit4)>0) {
				right=mid-1;
			}else {
				left=mid+1;
			}
		}
		return -1;
	}
	static void reverse(Fruit4[] arr) {
		// TODO Auto-generated method stub
		for(int i=0; i<arr.length/2;i++) {
			swap(arr, i, i=arr.length-i-1);
		}
		
	}
	static void showData(String string, Fruit4[] arr) {
		// TODO Auto-generated method stub
		System.out.println(string);
		for(Fruit4 item:arr) {
			System.out.println(item+" ");
		}
		System.out.println();
	}

}

