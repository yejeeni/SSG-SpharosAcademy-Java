package com.ssg.web0618;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudyServlet extends HttpServlet {

	public void init() throws ServletException {
		System.out.println("init");
	}

	/**
	 * 서블릿의 service() 메서드에서 요청을 받았을 때, 클라이언트의 HTTP 요청 방식이 GET이면 doGet() 메서드가, POST면 doPost(), ... doXXX()형 메서드를 호출
	 * 실제적인 로직은 doXXX()를 오버라이드하여 처리
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int x = 3;
		x = 7;
		
		// 요청을 마무리 짓는 단계에서 응답 정보 구성
		PrintWriter out = response.getWriter();
		
		out.print("결과는 " + x);
	}
	
	@Override
	public void destroy() {
	}

}
