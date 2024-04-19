<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>JDBC 테스트 1</h2>
	<%//자바에 url, id, pwd 적어놓고 연결시키는 방법
	JDBConnect jdbc1= new JDBConnect();
	jdbc1.close();
	%>
	<h2>JDBC 테스트 2</h2>
	<%//web.xml에 name,value 적어놓고 끌어와서 쓰는 방법
	String driver = application.getInitParameter("MySQLDriver");
	String url= application.getInitParameter("MySqlURL");
	String id=application.getInitParameter("MySQLId");
	String pwd = application.getInitParameter("MySQLPwd");
	
	JDBConnect jdbc2= new JDBConnect(driver,url,id,pwd);
	jdbc2.close();
	%>
	<h2>JDBC 테스트 3</h2>
	<%//같은 객체를 불러올 때 jsp에서 코드를 간결하게 하기위한 방법
	JDBConnect jdbc3= new JDBConnect(application);
	jdbc3.close();
	%>
</body>
</html>