package sql;

public class DriverLoading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Success to Loading");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading");
			System.out.println(e.getMessage());		
			}//해당 드라이버의 인스턴스를 만들어 줌

	}

}
