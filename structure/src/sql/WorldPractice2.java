package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class WorldPractice2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Code:");
		String code= sc.next();
		
		Connection con=null;
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select name, population from city where '"+code+"' order by countrycode asc, population desc");
			
			while(rs.next()) {
				System.out.print("["+rs.getString("name")+"]");
				System.out.println("-["+rs.getInt("population")+"] ");
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
