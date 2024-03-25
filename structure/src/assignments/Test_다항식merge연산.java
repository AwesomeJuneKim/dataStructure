package assignments;

class Polynomial implements Comparable<Polynomial>{
    double coef;           // 계수
    int    exp;            // 지수
	@Override
	public int compareTo(Polynomial o) {
		// TODO Auto-generated method stub
		if(this.coef>o.coef)
			return 1;
		else if (this.coef<o.coef)
			return -1;
		return 0;
	}
	public Polynomial(double d, int i) {
		// TODO Auto-generated constructor stub
		this.coef=d;
		this.exp=i;
	}


}
public class Test_다항식merge연산 {

	static void merge(Polynomial[] a, int lefta, int righta, int leftb, int rightb ) {
		//배열을 반으로 나누었을때 왼쪽배열의 양끝을 lefta, leftb라고 하고 오른쪽 양끝을 righta, rightb라고 한다.
		Polynomial[] temp=new Polynomial[30];
		int ix=0;//임시배열에 새로운 요소를 삽입할 때 사용하는 인덱스
		int p=lefta, q=leftb;
		while(p<righta && q<rightb) {
			if(a[p].compareTo(a[q])<0) temp[ix++]=a[p++];
			else if(a[p].compareTo(a[q])>0) temp[ix++]=a[q++];
			else {
				temp[ix++]=a[p++];
				temp[ix++]=a[q++];
			}
		}
		while(p>righta && q<=rightb) {
			temp[ix++]=a[q++];
		}
		while(p<=rightb && q>rightb) {
			temp[ix++]=a[p++];
		}
		for(int j=0; j<ix;j++) {
			System.out.print(a[lefta+j]+" ");//lefta+j 왜죠??????????
		}

	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		Polynomial[] x = {
		         new Polynomial(1.5, 3),
		         new Polynomial(2.5, 7),
		         new Polynomial(3.3, 2),
		         new Polynomial(4.0, 1),
		         new Polynomial(2.2, 0),
		         new Polynomial(3.1, 4),
		         new Polynomial(3.8, 5),
		     };
		Polynomial[] y = {
		         new Polynomial(1.5, 1),
		         new Polynomial(2.5, 2),
		         new Polynomial(3.3, 3),
		         new Polynomial(4.0, 0),
		         new Polynomial(2.2, 4),
		         new Polynomial(3.1, 5),
		         new Polynomial(3.8, 6),
		     };
		int nx = x.length;


		ShowPolynomial(x);
		ShowPolynomial(y);
		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial(x);
		ShowPolynomial(y);
		Polynomial[] z = new Polynomial[20];
		AddPolynomial(x,y,z);//다항식 덧셈 z = x + y
		ShowPolynomial(z);
		ShowPolynomial(y);
		MultiplyPolynomial(x,y,z);//다항식 곱셈 z = x * y
		ShowPolynomial(y);
		int result = EvaluatePolynomial(z, 10);//다항식 값 계산 함수 z(10) 값 계산한다 
		System.out.println(" result = " + result );
	}

	static int EvaluatePolynomial(Polynomial[] z, int i) {
		int result=0;
		for(int j=0; j<z.length;j++) {
			result += z[j].coef*Math.pow(i,z[j].exp);
		}
		return result;
	}

	static void MultiplyPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int k=0;
		for(int i=0; i< x.length;i++) {
			for(int j=0; j<y.length;j++) {
				z[k++]=new Polynomial(x[i].coef*y[j].coef, x[i].exp+y[j].exp);
			}
		}
		//동류항 더하기 
		for(int i=0;i<z.length;i++) {
			for(int j=1;j<z.length;j++) {
				if(z[i].exp==z[j].exp) {
					z[i].coef+=z[j].coef;
					z[j].coef=0;
				}
			}
		}
			
	}

	static void AddPolynomial(Polynomial[] x, Polynomial[] y, Polynomial[] z) {
		int i=0,j=0,k=0;
		while(i<x.length &&j<y.length) {//길이가 같은부분 계산 
			if(x[i].exp==y[j].exp) {
				z[k++]=new Polynomial(x[i].coef+y[j].coef, x[i].exp);
				i++;
				j++;
			}else if(x[i].exp<y[j].exp) {
				z[k++]=new Polynomial(y[j].coef,y[j].exp);
				j++;
			}else {
				z[k++]=new Polynomial(x[i].coef,x[i].exp);
				i++;
			}

		}//엑스식이 더 길다면 추가해 줌 
		while(i<x.length) {
			z[k++]=new Polynomial(x[i].coef,x[i].exp);
			i++;
		}//와이식이 더 길때도 추가해 
		while(j<y.length) {
			z[k++]=new Polynomial(y[j].coef,y[j].exp);
			j++;
		}
	}

	static void ShowPolynomial(Polynomial[] x) {
		for(int i=0; i<x.length;i++) {
			System.out.print(x[i].coef+"X^"+x[i].exp+" + ");
		}
		System.out.println();
		
	}
}
