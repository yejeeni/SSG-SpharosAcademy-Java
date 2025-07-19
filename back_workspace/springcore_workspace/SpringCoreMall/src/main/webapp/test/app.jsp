<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* 
	웹 요청 처리에서 데이터를 저장할 수 있는 객체의 종류
	HttpServletRequest: 요청 처리가 끝날 때까지
	HttpSession: 세션의 유지 시간까지
	ServletContext: 애플리케이션이 실행되는 동안 유지 (톰캣이 살아있는 동안)
	*/
	
	request.setAttribute("id",	"1");
	session.setAttribute("name", "name");
	application.setAttribute("nickname", "nick");
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