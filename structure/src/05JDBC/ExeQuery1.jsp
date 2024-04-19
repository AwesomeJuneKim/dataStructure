<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원 목록조회 테스트(executeQuery() 사용)</h2>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>방문수</th>
		</tr>
		<%
		JDBConnect jdbc = new JDBConnect();
		String id=request.getParameter("id");
		//String sql = "SELECT b.num, b.title, b.content, m.name, b.postdate, b.visitcount FROM member m, board b WHERE m.id=b.id and b.id='musthave'";
		//Statement stmt = jdbc.con.createStatement();
		PreparedStatement pt= jdbc.con.prepareStatement("SELECT b.num, b.title, b.content, m.name, b.postdate, b.visitcount FROM member m, board b WHERE m.id=b.id and b.id=?");
		pt.setString(1,id);

		ResultSet rs = pt.executeQuery();
		while (rs.next()) {

			String num = rs.getString("num");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String name = rs.getString("name");
			java.sql.Date postdate = rs.getDate("postdate");
			int visitcount = rs.getInt("visitcount");

			//out.println(String.format("번호 : %s 제목 : %s 내용: %s 이름 : %s postdate : %s 방문 수 : %s", num, title, content, name, postdate, visitcount + "<br/>"));
		%>
		<tr>
			<td><%=num%></td>
			<td><%=title%></td>
			<td><%=content%></td>
			<td><%=name%></td>
			<td><%=postdate%></td>
			<td><%=visitcount%></td>
		</tr>
		<%
		}
		jdbc.close();
		%>
	</table>

</body>
</html>