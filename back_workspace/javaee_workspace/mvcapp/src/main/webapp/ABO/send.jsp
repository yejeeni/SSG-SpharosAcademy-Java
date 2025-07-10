<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>색상 성격 분석</title>
</head>
<body>

<form action="/mvcapp/ABO.do" method="post">
	<select name="abo">
		<option value="">혈액형 선택</option>
		<option value="A">A</option>
		<option value="B">B</option>
		<option value="O">O</option>
		<option value="AB">AB</option>
	</select>
	<button>전송</button>
</form>


</body>
</html>
