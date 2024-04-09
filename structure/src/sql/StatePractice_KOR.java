package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatePractice_KOR {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//프로젝트 생성 및 드라이버 설정
		Connection con=null;
		//JDBC드라이버 로딩하기
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/world";
			String username="scott";
			String password="tiger";
			
			//DBMS서버와 접속히기
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			
			//Statement 입력
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select name, population from city where countrycode='kor' order by population asc");
			
			while(rs.next()) {
				System.out.print("도시이름 ["+rs.getString("name")+"], ");
				System.out.print("인구수: "+rs.getString("population")+"\n");

			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결실패 : "+e.getMessage());
		}

	}

}
