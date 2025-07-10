package com.ssg.mvcapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 컨트롤러의 최상위 객체
 */
public interface Controller {
	
	/**
	 * 업무를 수행할 때 호출하는 메서드
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * 어느 페이지를 검색해야 할지 DispatcherServlet에게 알려주기 위한 메서드
	 * @return
	 */
	public String getViewPage();
}
