<%@page import="com.ssg.notice.model.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%!
// 선언부: JDBC 설정
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

<%
// 서비스 영역
connect();

String notice_id = request.getParameter("notice_id");

Notice notice = null;

if (notice_id != null) {
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT * FROM notice WHERE notice_id = ?");
    
    pstmt = con.prepareStatement(sql.toString());
    pstmt.setInt(1, Integer.parseInt(notice_id));
    rs = pstmt.executeQuery();
    
    if (rs.next()) {
        notice = new Notice();
        notice.setNotice_id(rs.getInt("notice_id"));
        notice.setTitle(rs.getString("title"));
        notice.setWtirer(rs.getString("writer"));
        notice.setHit(rs.getInt("hit"));
        notice.setRegDate(rs.getDate("regdate"));
        notice.setContent(rs.getString("content")); // 누락 방지
    }
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 및 jQuery -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- 서머노트 -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $('#summernote').summernote({
        height: 200
    });

    // 버튼 클릭 이벤트 등록
    $("input[name=update]").click(() => {
        $("form").attr("action", "/notice/update").submit();
    });

    $("input[name=delete]").click(() => {
        $("form").attr("action", "/notice/delete").submit();
    });

    $("input[name=list]").click(() => {
        location.href = "/notice/list.jsp";
    });
});
</script>

<style>
body { font-family: Arial, Helvetica, sans-serif; }
input[type=text], textarea {
    width: 100%; padding: 12px; border: 1px solid #ccc;
    border-radius: 4px; box-sizing: border-box;
    margin-top: 6px; margin-bottom: 16px;
}
input[type=button] {
    background-color: #04AA6D; color: white;
    padding: 12px 20px; border: none;
    border-radius: 4px; cursor: pointer;
}
input[type=button]:hover {
    background-color: #45a049;
}
.container {
    border-radius: 5px; background-color: #f2f2f2; padding: 20px;
}
</style>

</head>
<body>

<h3>공지 상세</h3>

<div class="container">
<form method="post">
    <input type="hidden" name="notice_id" value="<%= notice.getNotice_id() %>">
    
    <label for="title">제목</label>
    <input type="text" name="title" value="<%= notice.getTitle() %>">
    
    <label for="writer">작성자</label>
    <input type="text" name="writer" value="<%= notice.getWtirer() %>">
    
    <label for="content">내용</label>
    <textarea id="summernote" name="content"><%= notice.getContent() %></textarea>
    
    <input type="button" name="update" value="수정">
    <input type="button" name="delete" value="삭제">
    <input type="button" name="list" value="목록">
</form>
</div>

</body>
</html>

<%
if (rs != null) try { rs.close(); } catch(Exception e) {}
if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
if (con != null) try { con.close(); } catch(Exception e) {}
%>
