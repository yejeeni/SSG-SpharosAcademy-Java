<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script></script>
</head>
<body>
	<%
	// 서블릿과는 달리, jsp이므로 session을 얻어올 때 내장객체로 접근. session은 java.uti.Map을 계승했으므로, key-value 쌍으로 데이터를 관리
	String img = (String)session.getAttribute("img");
	out.print(img);
	
	%>
	<img src="<%=request.getContextPath()%>/upload/<%=img%>">

</body>
</html>