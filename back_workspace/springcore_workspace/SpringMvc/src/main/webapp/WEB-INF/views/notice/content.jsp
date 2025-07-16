<%@page import="myframework.admin.model.Notice"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Notice notice=(Notice)session.getAttribute("notice");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

body {
	font-family: 'Noto Sans KR', sans-serif;
	background: linear-gradient(135deg, #ffeef2, #fbe0ff);
	margin: 0;
	padding: 40px 20px;
}

h3 {
	text-align: center;
	color: #d63384;
	margin-bottom: 30px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	padding: 30px 40px;
	border-radius: 16px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
}

label {
	display: block;
	margin-top: 20px;
	font-weight: 600;
	color: #333;
	font-size: 15px;
}

input[type="text"], textarea {
	width: 100%;
	padding: 12px 16px;
	border: 1px solid #ccc;
	border-radius: 10px;
	background-color: #fffafc;
	font-size: 15px;
	margin-top: 8px;
	box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.04);
	transition: all 0.3s ease;
}

input[type="text"]:focus, textarea:focus {
	border-color: #ff99bb;
	box-shadow: 0 0 6px rgba(255, 135, 180, 0.3);
	outline: none;
}

input[type="button"] {
	margin-top: 30px;
	background-color: #ff66a3;
	color: white;
	padding: 12px 28px;
	font-size: 16px;
	border: none;
	border-radius: 10px;
	cursor: pointer;
	transition: background-color 0.3s ease, transform 0.2s ease;
	box-shadow: 0 6px 12px rgba(255, 102, 163, 0.3);
}

input[type="button"]:hover {
	background-color: #e25592;
	transform: translateY(-2px);
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(()=>{		
		$("#content").summernote({
			height:250,
		});	//서머노트 연동 
		$("#content").summernote('code', "<%=notice.getContent()%>");
		
		//버튼에 이벤트 연결 
		//0번째-수정
		$("#bt_edit").click(()=>{
			if(confirm("수정하시겠어요?")){
				//서버로 입력폼의 내용을 모두 가져가야 하므로, Post 방식으로 보내야 함 
				$("form").attr({
					method:"POST", 
					action:"/notice/update"
				});
				$("form").submit();
			}
		});
		
		//1번째-삭제
		$("#bt_del").click(()=>{
			if(confirm("삭제하시겠어요?")){
				//Get방식 요청(링크)
				location.href="/notice/del?notice_id=<%=notice.getNotice_id()%>";
			}
		});
		
		//2번째-목록
		$("#bt_list").click(()=>{
			location.href="/notice/list.jsp";
		});
		
	});
</script>
</head>
<body>
<h3>Contact Form</h3>

<div class="container">
  <form>
  <!-- hidden 은 html 컴포넌트의 역할을 수행하지만, 시각적으로 표현되지는 않음
  	노출되지 않은 상태로 데이터를 전송할때 사용
   -->
    <input type="hidden" name="notice_id" value="<%=notice.getNotice_id()%>">
    
    <label for="fname">Title</label>
    <input type="text" id="fname" name="title" value="<%=notice.getTitle()%>">

    <label for="lname">Writer</label>
    <input type="text" id="lname" name="writer" value="<%=notice.getWriter()%>">

    <label for="subject">Content</label>
    <textarea id="content" name="content" placeholder="내용입력" style="height:200px"></textarea>

    <input type="button" value="수정" id="bt_edit">
    <input type="button" value="삭제" id="bt_del">
    <input type="button" value="목록" id="bt_list">
    
  </form>
</div>

</body>
</html>