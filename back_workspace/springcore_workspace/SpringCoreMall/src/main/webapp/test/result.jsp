<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)request.getAttribute("id");
	String name = (String)session.getAttribute("name");
	String nickname = (String)application.getAttribute("nickname");
	String text = (String)application.getAttribute("text");
	
	out.println("your id = " + id);
	out.println("<br>");
	out.println("your name = " + name);
	out.println("<br>");
	out.println("your nickname = " + nickname);
	out.println("<br>");
	out.println(text);
	
	
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