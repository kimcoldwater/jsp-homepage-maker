<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="user.UserDAO" %>
    <%@ page import="java.io.PrintWriter" %>
    <% request.setCharacterEncoding("UTF-8"); %>
    <jsp:useBean id="user" class="user.User" scope="page"/>
    <jsp:setProperty name="user" property="userID" />
    <jsp:setProperty name="user" property="userPassword" />
    <jsp:setProperty name="user" property="userName" />
    <jsp:setProperty name="user" property="userGender" />
    <jsp:setProperty name="user" property="userEmail" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 5번째 시작</title>
</head>
<body>
	<%
		if(user.getUserID() == null || user.getUserPassword() == null || user.getUserName() == null
		|| user.getUserGender() == null || user.getUserEmail() == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안됐습니다')");
			script.println("histroy.back()");
			script.println("</script>");	
		}else {
			UserDAO userDAO = new UserDAO();
			int result = userDAO.join(user);
			//Servlet에 요청
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('이미 존재하는 아이디입니다')");
				script.println("histroy.back()");
				script.println("</script>");	
			}
			else {
				session.setAttribute("userID", user.getUserID());
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href='login.jsp'");
				
				script.println("</script>");
			
		}
	}
		
		//응답
	%>
</body>
</html>
