<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(()=>{
		$("button").click(()=>{
			// 폼의 데이터를 서버로 전송
			// $("form").attr("method", "post");
			// $("form").attr("action", "/upload/regist");
			//$("form").attr("enctype", "multipart/form-data"); // 바이너리 파일로 폼 전송
			
			// 위의 html DOM 속성을 하나씩 명시하는 법 대신 JSON을 이용하는 방법
			$("form").attr({
				"method": "post",
				"action": "/upload/regist",
				"enctype" : "multipart/form-data"
			});
			$("form").submit(); // 전송
		});
	});
</script>
</head>
<body>
	<form>
	<input type="text" name="title">
	<input type="file" name="photo">
	<button type="button">버튼</button>
	</form>
</body>
</html>