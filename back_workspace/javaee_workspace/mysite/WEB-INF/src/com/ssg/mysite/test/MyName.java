package com.ssg.mysite.test;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/*
* java의 클래스를 이용하여 나의 이름을 웹 브라우저에 출력
* java EE 기반의 서버에서만 해석 및 실행될 수 있는 클래스를 가리켜 setvlet이라고 한다.
*/
public class MyName extends HttpServlet{
											// Servlet으로 정의하려면 Servlet을 상속받아야 한다. 
	
	// 부모인 서블릿의 doGet을 오버라이드
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		// 웹브라우저에 보여질 html문서를 작성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter(); // 응답 정보를 출력할 스트림 얻기
		out.print("this is my first app");
	}

}