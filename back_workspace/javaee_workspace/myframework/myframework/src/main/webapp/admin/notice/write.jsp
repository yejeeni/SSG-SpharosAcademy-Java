<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<meta charset="UTF-8">
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
<script>
$(() => {
    // Summernote 활성화
   
    // 버튼 클릭 시 알림창
    $("input[type='button']").click(function() {
        $("form").attr({
        	action: "/notice/regist.do",
        	method: "POST" //머리 데이터를 실어 나르게 됨, 따라서 정보가 노출된다.
        })
        $("form").submit();//전송
    });
    	
});
</script>

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



</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form>
			<label for="fname">Title</label> 
			<input type="text" id="fname" name="title" placeholder="Your title..">
			<label for="lname">Writer</label> 
			<input type="text" id="lname" name="writer" placeholder="Your writer..">
			<label for="subject">Content</label>
			<textarea id="content" name="content" placeholder="Write something.."style="height: 200px"></textarea>
			<input type="button" value="submit">
		</form>
	</div>

</body>
</html>
