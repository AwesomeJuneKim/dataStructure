<%@ page import="membership.MemberDTO"%>
<%@ page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//사용자가 입력한 아이디와 패스워드
String userId = request.getParameter("user_id");
String userPwd = request.getParameter("user_pw");
//데이터 서버에 접속
String mysqlDriver = application.getInitParameter("MySQLDriver");
String mysqlURL = application.getInitParameter("MySqlURL");
String mysqlId = application.getInitParameter("MySQLId");
String mysqlPwd = application.getInitParameter("MySQLPwd");

MemberDAO dao = new MemberDAO(mysqlDriver, mysqlURL, mysqlId, mysqlPwd);
MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
dao.close();

if (memberDTO.getId() != null) {
	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
	// session.setAttribute("user", MemberDTO);
	response.sendRedirect("LoginForm.jsp");
} else {
	request.setAttribute("LoginErrMsg", "로그인 오류입니다");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>