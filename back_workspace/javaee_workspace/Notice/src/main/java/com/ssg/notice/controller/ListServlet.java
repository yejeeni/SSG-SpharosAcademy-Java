package com.ssg.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 게시물의 목록 요청을 처리하는 서블릿
 */
public class ListServlet extends HttpServlet{
	
	/**
	 * 서블릿은 서버측에서 수행되는 자바 클래스
	 * 디자인을 표현할 때 html 태그를 모두 문자열로 처리해야 한다는 문제가 있어, asp, php처럼 html과 프로그램 코드를 함께 작성할 수 있는 jsp로 디자인을 표현
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
}
