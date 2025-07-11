<%@page import="mvcproject.ABO.model.ABOManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%!
	ABOManager aboManager = new ABOManager();
%>
<%
// ver3. 모델 2 방식: MVC 패턴을 JavaEE 기술로 구현해놓은 모델. Model - .java(POJO) | View: jsp | Controller: servlet
	String msg = null;	
	out.print(msg);

/*
	// ver2. 모델 1 방식: JSP 또는 서블릿이 MVC 중 V, C를 담당하는 개발 방식
	
	String abo = request.getParameter("abo");
	String msg = null;
	
	if (abo != null){
		msg = aboManager.getAdvice(abo);
		out.print(msg);
	}
*/

/*
	// ver1. jsp 파일 하나로 모든 요청을 처리
	
	String abo = request.getParameter("abo");
	String msg = null;
	
	if (abo != null){
		if (abo.equals("A")){
			msg = "신중함";
		} else if(abo.equals("B")){
			msg = "개성강함";
		} else if(abo.equals("O")){
			msg = "외향적";
		} else if(abo.equals("AB")){
			msg = "이성적";
		}
		out.print(msg);		
	}
*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>혈액형 성격 분석</title>
</head>
<body>

	<form action="/ABO.do" method="post">
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
