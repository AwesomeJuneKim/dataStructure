package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparedStatementPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			
			PreparedStatement pt= con.prepareStatement("select id,name,countrycode,district,population from city where countrycode=? and name=?");
			pt.setString(1,"kor");
			pt.setString(2,"seoul");
			ResultSet rs=pt.executeQuery();
			
			while(rs.next()) {
				System.out.print(rs.getInt("id")+", ");
				System.out.print(rs.getString("name")+", ");
				System.out.print(rs.getString("countrycode")+", ");
				System.out.print(rs.getString("district")+", ");
				System.out.print(rs.getInt("population")+"\n");

			}
			rs.close();
			pt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결실패"+e.getMessage());
		}

	}

}
