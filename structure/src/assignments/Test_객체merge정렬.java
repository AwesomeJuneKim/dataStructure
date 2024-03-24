
package assignments;
/*
 * 6장 구현 실습과제1 
 */
class PhyscData implements Comparable<PhyscData>{
    String name;              // 이름
    int    height;            // 키
    double vision;            // 시력
	@Override
	public int compareTo(PhyscData o) {
		// TODO Auto-generated method stub
		if(this.height==o.height) {
			return 0;
		}else if(this.height>o.height){
			return 1;
		}else {
			if(this.vision==o.vision)
				return 0;
			else if(this.vision>o.vision)
				return 1;
			else
				return -1;
		}
	}
	public PhyscData(String name, int height, double vision) {
		this.name=name;
		this.height=height;
		this.vision=vision;
	}



}
public class Test_객체merge정렬 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		
		PhyscData[]test=new PhyscData[30];
		//정렬한 배열을 저장할 새로운 배열을 생성 한다.
		int tx=0; //test배열의 인덱스를 나타냄
		while(lefta<=righta && leftb<=rightb) {
			if(a[lefta].compareTo(a[leftb])<0)
				test[tx++]=a[lefta++];
			//lefta<leftb이면 test[tx]에 a[lefta]를 대입하고 1씩 증가시킨다.
			else if(a[lefta].compareTo(a[leftb])>0)
				test[tx++]=a[leftb++];
			else
				test[tx++]=a[lefta++];
				test[tx++]=a[leftb++];
		}
		while(lefta>righta && leftb<=rightb) {
			test[tx++]=a[leftb++];//제일 작은 수는 leftb가 되므로 
		}while(lefta<=righta && leftb>rightb) {
			test[tx++]=a[lefta++];
		}
//		!!!!!!!!!!!왜 a배열에 대입해서 출력해야 하는지 잘 모르겠음!!!!!!!!!!!!!!
		System.out.println();
		for(int i=0; i<a.length;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println();
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
		int nx = x.length;

		   MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
			System.out.println("오름차순으로 정렬했습니다.");
		     System.out.println("■ 신체검사 리스트 ■");
		     System.out.println(" 이름     키  시력");
		     System.out.println("------------------");
		     for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
