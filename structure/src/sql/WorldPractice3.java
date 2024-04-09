package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorldPractice3 {
	public static void main(String[] args) {
		Connection con=null;
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			//위의 코드를 한줄로 줄일 수 있다.
			//try(Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/world","scott","tiger")
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select name from city where countrycode in ('kor','chn','jpn')");
			
			while(rs.next()) {
				System.out.print("["+rs.getString("name")+"]");

			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결실패"+e.getMessage());
		}

	}
}
