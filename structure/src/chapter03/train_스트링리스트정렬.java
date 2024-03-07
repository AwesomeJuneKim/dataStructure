package chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class train_스트링리스트정렬 {

	    public static String[] removeElement1(String[] arr, String item) {
	    	List<String> list=new ArrayList<>(Arrays.asList(arr));
	    		list.remove(item);
	    	return list.toArray(String[]::new);

	    }
	    
	    static void getList(List<String> list) {
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");


			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");

			list.add(1, "LA");
	    }
	    static void showList(String topic, List<String> list) {
	    	System.out.println(topic+" ::");
	    	for(String item:list) {
	    		System.out.println(item+" ");
	    	}

	    }
	    static void sortList(List<String> list) {
	    	//방법1. list.sort(null);-> Collections.sort(list)를 사용해도 됨
	    	//방법2. 리스트를 스트링 배열로 반환
	    	list.sort(null);
	    	String[] arr=list.toArray(new String[0]);
	    	
	    	

	    }
	    
	    static String[] removeDuplicateList(List<String> list) {
		    String cities[] = new String[0];
		    cities = list.toArray(cities);//배열로 바꿔 줌
		    //for문으로 중복도시를 체크 함 compareTo를 사용해서 같으면 removeElement(arr, item)를 호출하여 삭제함
		    for(int i=0;i<)
		    return cities;
	    }
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();
			getList(list);
			showList("입력후", list);
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

//		    Collections.sort(list);

//배열의 정렬
			sortList(list);
		    System.out.println();
		    showList("정렬후", list);
// 배열에서 중복제거
		    System.out.println();
		    System.out.println("중복제거::");
		  
		    String[] cities = removeDuplicateList(list);
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));//배열을 리스트로 바꿔 줌
		    showList("중복제거후", lst);
		}
	}

