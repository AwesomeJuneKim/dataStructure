package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			String Driver="com.mysql.cj.jdbc.Driver"
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			
			Statement st=con.createStatement();//con. -> con이 가지고 있는 클래스를 확인할 수 있음
			ResultSet rs=st.executeQuery("Select id,name,countrycode,district,population from city limit 10");
			
			while(rs.next()) {//커서프로세싱 이라고 부른다.
				System.out.print(rs.getString("id")+",");
				System.out.print(rs.getString("name")+",");
				System.out.print(rs.getString("countrycode")+",");
				System.out.print(rs.getString("district")+",");
				System.out.print(rs.getString("population")+"\n");
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to Loading");
			System.out.println(e.getMessage());		
			}

	}

}
