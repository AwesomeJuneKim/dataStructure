package sql;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			String Driver="com.mysql.cj.jdbc.Driver"
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			
			System.out.println("Success to Loading");
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading");
			System.out.println(e.getMessage());		
			}

	}

}
