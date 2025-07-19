<%@page import="notice.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<%
   List<Notice> list = (List)request.getAttribute("notices");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Notice Board</title>

<style>
body {
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	background-color: #f9f9f9;
	color: #333;
	margin: 0;
	padding: 40px;
}

h2 {
	text-align: center;
	color: #222;
	font-weight: 500;
	font-size: 32px;
	margin-bottom: 8px;
}

p {
	text-align: center;
	color: #666;
	margin-bottom: 32px;
	font-size: 14px;
}

table {
	width: 90%;
	margin: 0 auto;
	border-collapse: collapse;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
	background-color: #fff;
}

th, td {
	padding: 14px 18px;
	text-align: center;
	border-bottom: 1px solid #e0e0e0;
	font-size: 15px;
}

th {
	background-color: #f4f4f4;
	font-weight: 600;
	color: #111;
}

tr:hover {
	background-color: #f0f0f0;
}

a {
	color: #1a1a1a;
	text-decoration: none;
	font-weight: 500;
}

a:hover {
	text-decoration: underline;
}

.btn-wrapper {
	text-align: right;
	width: 90%;
	margin: 24px auto 0;
}

button {
	background-color: #222;
	color: white;
	border: none;
	padding: 10px 22px;
	font-size: 14px;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

button:hover {
	background-color: #000;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
    $(() => {
      $("button").click(() => {
        location.href = "/admin/notice/registform";
      });
    });
  </script>
</head>
<body>

	<h2>Notice Board</h2>
	<p>View recent announcements and updates</p>

	<table>
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Author</th>
			<th>Date</th>
			<th>Views</th>
		</tr>

		<% for (int i = 0; i < list.size(); i++) {
     Notice notice = list.get(i);
     Date regDate = notice.getRegDate();
     String displayDate = (regDate != null) ? sdf.format(regDate) : "";
%>
	<tr>
		<td><%= list.size() - i %></td>
		<td><a href="/notice/content.do?notice_id=<%= notice.getNotice_id() %>"><%= notice.getTitle() %></a></td>
		<td><%= notice.getWriter() %></td>
		<td><%= notice.getRegDate() %></td>
		<td><%= notice.getHit() %></td>
	</tr>
<% } %>
	</table>

	<div class="btn-wrapper">
		<button>+ Write Notice</button>
	</div>

</body>
</html>
