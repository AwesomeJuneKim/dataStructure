package doneAssign;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public String toString() {// toString은 모든 자바 객체에 포함되므로 오버라이딩 가능하다.
//객체출력 
		return "[" + name + "\t" + height + "\t" + vision + "]";
	}

	@Override
	public int compareTo(PhyscData p) {
		if (this.name.compareTo(p.name) > 0)
			return 1;
		else if (this.name.compareTo(p.name) < 0)
			return -1;
		else
			return 0;
	}

	public int equals(PhyscData p) {
		if (this.name.equals(p.name))
			return 1;
		else
			return 0;

	}
}

	public class Test_실습2_14객체배열정렬 {
		static void swap(PhyscData[] arr, int ind1, int ind2) {
			PhyscData t = arr[ind1];
			arr[ind1] = arr[ind2];
			arr[ind2] = t;

		}

		static void sortData(PhyscData[] arr) {
			for (int i = 0; i < arr.length; i++)
				for (int j = i + 1; j < arr.length; j++)
					if (arr[i].compareTo(arr[j]) > 0)
						swap(arr, i, j);

		}

		public static void main(String[] args) {
			PhyscData[] data = { new PhyscData("홍길동", 162, 0.3), new PhyscData("홍동", 164, 1.3),
					new PhyscData("홍길", 152, 0.7), new PhyscData("김홍길동", 172, 0.3), new PhyscData("이길동", 182, 0.6),
					new PhyscData("박길동", 167, 0.2), new PhyscData("최길동", 169, 0.5), };
			System.out.println("원본 데이터: ");
			showData(data);
			System.out.println("정렬 데이터: ");
			sortData(data);
			// Arrays.sort(null, null);//comparator가 필요하다
			showData(data);

		}

		static void showData(PhyscData[] arr) {
			for (PhyscData data : arr) {
				System.out.println(data.toString());
			}

		}

	}

