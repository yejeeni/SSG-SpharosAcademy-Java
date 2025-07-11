<%@page import="mvcproject.notice.model.Notice"%>
<%@page import="mvcproject.notice.repository.NoticeDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
Notice notice = (Notice) request.getAttribute("notice");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
	;

 body {
    font-family: 'Inter', sans-serif;
    background-color: #f9fafb;
    padding: 40px 20px;
    margin: 0;
  }
  .container {
    max-width: 700px;
    margin: 0 auto;
    background: #fff;
    padding: 30px 40px;
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.07);
  }

  h3 {
    font-weight: 600;
    color: #333;
    margin-bottom: 30px;
    font-size: 1.8rem;
    text-align: center;
  }

  label {
    font-weight: 500;
    color: #555;
    display: block;
    margin-bottom: 8px;
    margin-top: 20px;
  }

  input[type="text"], textarea {
    width: 100%;
    padding: 14px 16px;
    border: 1.5px solid #d1d5db;
    border-radius: 10px;
    font-size: 1rem;
    transition: border-color 0.25s ease;
    resize: vertical;
    box-sizing: border-box;
  }

  input[type="text"]:focus,
  textarea:focus {
    outline: none;
    border-color: #4b7bec;
    box-shadow: 0 0 0 3px rgba(75,123,236,0.2);
  }

  textarea {
    min-height: 160px;
    font-family: 'Inter', sans-serif;
  }

  input[type="button"] {
    background-color: #4b7bec;
    color: white;
    border: none;
    border-radius: 12px;
    padding: 14px 30px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    margin-top: 30px;
    display: block;
    width: 100%;
    transition: background-color 0.3s ease;
  }

  input[type="button"]:hover {
    background-color: #3a5fc1;
  }
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote.min.js"></script>
<script type="text/javascript">
	$(()=>{		
	
		//버튼에 이벤트 연결 
		//0번째-수정
		$("#bt_edit").click(()=>{
			if(confirm("수정하시겠어요?")){
				//서버로 입력폼의 내용을 모두 가져가야 하므로, Post 방식으로 보내야 함 
				$("form").attr({
					method:"POST", 
					action:"/notice/edit.do"
				});
				$("form").submit();
			}
		});
		
		//1번째-삭제
		$("#bt_del").click(()=>{
			if(confirm("삭제하시겠어요?")){
				//Get방식 요청(링크)
				location.href="/notice/del.do?notice_id=<%=notice.getNotice_id()%>";
			}
		});
		
		//2번째-목록
		$("#bt_list").click(()=>{
			location.href="/notice/list.do";
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
			<input type="hidden" name="notice_id"	value="<%=notice.getNotice_id()%>"> 
			<label for="fname">제목</label>
			<input type="text" id="title" name="title"	value="<%=notice.getTitle()%>"> 
			<label for="writer">작성자</label>
			<input type="text" id="writer" name="writer"	value="<%=notice.getWriter()%>"> <label for="lname">내용</label>
			<label for="content">내용</label>
			<textarea id="content" name="content"><%= notice.getContent() %></textarea>

			<input type="button" value="수정" id="bt_edit"> 
			<input type="button" value="삭제" id="bt_del"> 
			<input type="button" value="목록" id="bt_list">

		</form>
	</div>

</body>
</html>