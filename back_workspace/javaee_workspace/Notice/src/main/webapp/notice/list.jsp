<%@page import="com.ssg.notice.model.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!--  
JSP(Java Server Page): 자바 기술로 만든 서버측에서 실행되는 페이지
서블릿과 목적은 같으나, 서블릿은 디자인을 표현하는 부분에 있어 매우 불편하므로 디자인을 처리하고 싶다면 jsp를 사용

JSP 코드를 작성할 수 있는 영역
1) 지시영역: <> 태그의 내부에 %page%.  현재 페이지에 대한 인코딩 등 페이지 자체에 대한 설정 정보 작성. 인코딩 다구겅 utf-8, 마임타입 등
2) 선언부: 태그 내부 %! %. 멤버변수와 멤버 메서드
3) 스크립틀릿 영역: 태그 내부 %%. 로직 작성할 수 있는 메서드 영역 -> 서비스 영역
4) 태그 내부 %=데이터(타입)%. out.print();의 줄임 표현

JSP의 실행 위치
서버에서만 실행되므로 JSP, ASP, PHP 같은 언어를 가리켜 서버 사이드 스크립트 언어라고 한다.

JS의 실행 위치
클라이언트의 웹 브라우저에서 실행. 클라이언트에 노출되기에 보안이 불가능하여, 이를 개선한 것이 Node.js - react.js
 -->

<%! // !를 선언하면 선언부라 하여, 멤버 변수와 멤버 메서드 작성이 가능
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
%>

<% // 서비스 메서드 영역
	connect(); // 접속

	pstmt = con.prepareStatement("select * from notice order by notice_id desc");
	rs = pstmt.executeQuery();

	List<Notice> list = new ArrayList();
	while(rs.next()){
		Notice notice = new Notice();
		notice.setNotice_id(rs.getInt("notice_id"));
		notice.setTitle(rs.getString("title"));
		notice.setWtirer(rs.getString("writer"));
		notice.setHit(rs.getInt("hit"));
		notice.setRegDate(rs.getDate("regdate"));
		list.add(notice);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 먼저 로드 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	/*
	제이쿼리는 JS 코드를 간단하게 작성하기 위함. (누구를-CSS선택자).어떻게()의 구조
	*/
	$(function(){
		$("button").click(function(){
			location.href = "/notice/write.html";
		});
	});
</script>

<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}
td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
tr:nth-child(even) {
	background-color: #dddddd;
}
a {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>no</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<% for(Notice notice : list) { %>
		<tr>
			<td><%= notice.getNotice_id() %></td>
			<td><a href="./detail.jsp?notice_id=<%= notice.getNotice_id() %>"><%=notice.getTitle() %></a></td>
			<td><%=notice.getWtirer() %></td>
			<td><%=notice.getRegDate() %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<% } %>
		<tr>
			<td colspan="5"><button type="button">글등록</button></td>
		</tr>
	</table>
</body>
</html>

<%
rs.close();
pstmt.close();
con.close();
%>
