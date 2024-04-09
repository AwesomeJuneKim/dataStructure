package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class StatePractice_1 {
	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in)) {
			System.out.print("Table Name: ");
			String tblname=sc.next();
			test(tblname);
		}
			
		} 
	

	public static void test(String tblname) {
		Connection con = null;
		Statement st = null;
		ResultSet ct = null;
		ResultSet ctr = null;
		ResultSet ctl = null;
		// 프로젝트 생성 및 드라이버 설정
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";

			// jdbc 드라이버 로딩하기(class.forname())
			Class.forName(driver);
			// dbms 서버와 접속하기(DriverManager.getConnection())
			con = DriverManager.getConnection(url, username, password);

			// sql문 실행하기 -statement
			st = con.createStatement();
			// sql문 실행하기-preparedStatement
			
			ct = st.executeQuery("select * from "+tblname+" limit 30");//쿼리문이 만들어진다고 바로 서버에서 데이터가 로컬로 오는것은 아니다.
			ResultSetMetaData meta1 = ct.getMetaData();
			int countCt = meta1.getColumnCount();
			
			while (ct.next()) {//sql문의 statement가 실행되면 서버에서 데이터가 하나씩 불러온다.
				for (int i = 1; i <= countCt; i++) {
					System.out.print(ct.getString(i) + ((i == countCt) ? "" : ","));
				}
				System.out.println();
			}
			
			
			// 자원 해제하기

		} catch (Exception e) {
			System.out.println("실행 실패 : " + e.getMessage());
		} finally {
			try {
				if (ct != null)
					ct.close();
				if (ctr != null)
					ctr.close();
				if (ctl != null)
					ctl.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("자원 해제 실패: " + e2.getMessage());
			}
		}
	}

}

