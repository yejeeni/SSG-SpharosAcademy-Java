<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>색상 성격 분석</title>
</head>
<body>

<form action="/mvcapp/color.do" method="get">
	<select name="color">
		<option value="">색상 선택</option>
		<option value="red">Red</option>
		<option value="blue">Blue</option>
		<option value="yellow">Yellow</option>
		<option value="green">Green</option>
	</select>
	<button>전송</button>
</form>


</body>
</html>
