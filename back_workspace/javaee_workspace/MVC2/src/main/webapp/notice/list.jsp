<%@page import="mvcproject.notice.model.Notice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
    List<Notice> list = (List<Notice>) request.getAttribute("notices");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
		font-family: "Segoe UI", "Apple SD Gothic Neo", sans-serif;
	}

	body {
		background-color: #f5f6fa;
		padding: 40px 20px;
		color: #2f3640;
		display: flex;
		justify-content: center;
	}

	.container {
		width: 100%;
		max-width: 900px;
	}

	h2 {
		font-size: 26px;
		margin-bottom: 8px;
	}

	p {
		color: #555;
		margin-bottom: 24px;
	}

	.notice-list {
		display: flex;
		flex-direction: column;
		gap: 12px;
	}

	.notice-item {
		background: white;
		padding: 16px 20px;
		border-radius: 10px;
		box-shadow: 0 2px 6px rgba(0,0,0,0.06);
		display: flex;
		justify-content: space-between;
		align-items: center;
		transition: background-color 0.2s ease;
	}

	.notice-item:hover {
		background-color: #f0f3f5;
	}

	.notice-info {
		display: flex;
		flex-direction: column;
		gap: 4px;
	}

	.notice-title a {
		color: #2980b9;
		font-size: 17px;
		font-weight: 600;
		text-decoration: none;
	}

	.notice-title a:hover {
		text-decoration: underline;
	}

	.notice-meta {
		font-size: 13px;
		color: #888;
	}

	.button-wrapper {
		text-align: right;
		margin-top: 24px;
	}

	button {
		background-color: #2980b9;
		color: white;
		padding: 10px 18px;
		border: none;
		border-radius: 6px;
		font-size: 14px;
		cursor: pointer;
		transition: background-color 0.2s;
	}

	button:hover {
		background-color: #21618c;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(() => {
		$("button").click(() => {
			location.href = "/notice/write.jsp";
		});
	});
</script>
</head>
<body>
<div class="container">
	<h2>공지사항</h2>
	<p>공지사항 목록을 확인하세요.</p>

	<div class="notice-list">
		<% for(int i = 0; i < list.size(); i++) { 
			Notice notice =(Notice) list.get(i); 
		%>
		<div class="notice-item">
			<div class="notice-info">
				<div class="notice-title">
					<a href="/notice/detail.do?notice_id=<%=notice.getNotice_id()%>">
						<%=notice.getTitle() %>
					</a>
				</div>
				<div class="notice-meta">
					작성자: <%=notice.getWriter() %> · 
					등록일: <%=notice.getRegDate().substring(0,10) %> · 
					조회수: <%=notice.getHit() %>
				</div>
			</div>
			<div>No. <%=notice.getNotice_id()%></div>
		</div>
		<% } %>
	</div>

	<div class="button-wrapper">
		<button>글 등록</button>
	</div>
</div>
</body>
</html>
