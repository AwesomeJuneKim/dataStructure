package doneAssign;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;
import java.util.List;
public class Test_스트링배열합병 {
    static void showList(String topic, String [] list) {
    	System.out.println(topic);
    	for(int i=0; i<list.length;i++) {
    		System.out.print(list[i]+" ");
    	}
    	System.out.println();

    }
    static String[] mergeList(String[]s1, String[] s2) {
    	int i = 0, j = 0,k =0;
    	String[] s3 = new String[10];
    	while(i<s1.length) {
    		s3[k++]=s1[i++];
    	}
    	while(j<s2.length) {
    		s3[k++]=s2[j++];
    	}
    	return s3;
    }
    public static void main(String[] args) {
	String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "우도"};
	Arrays.sort(s1);//comparable의 compareTo를 사용한다.
	Arrays.sort(s2);
	
	showList("s1배열 = ", s1);
	showList("s2배열 = ", s2);

	String[] s3 = mergeList(s1,s2);
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
}
}
