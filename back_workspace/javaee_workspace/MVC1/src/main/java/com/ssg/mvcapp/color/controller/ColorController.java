package com.ssg.mvcapp.color.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssg.mvcapp.color.model.ColorManager;
import com.ssg.mvcapp.controller.Controller;

/**
 * 서블릿으로 정의하는 이유
 * JSP는 View를 사용
 * 웹 기반의 컨트롤러는 클라이언트의 요청을 받을 수 있어야 함
 */
/**
 * 컨트롤러의 5대 업무
 * 1. 요청을 받는다
 * 2. 요청을 분석한다
 * 3. 직접 일하지 않고, 알맞는 로직 객체(모델)에게 일을 시킨다
 * 4. 결과에 보여질 데이터를 보관한다
 * 5. 알맞는 결과를 보여준다
 */
public class ColorController implements Controller{
	ColorManager manager = new ColorManager();

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String color = request.getParameter("color");
		String result = manager.getAdvice(color);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", result);
		
		// result.jsp를 클라이언트가 보도록 처리
		// 톰캣이 응답정보를 이용하여 응답 컨텐츠를 보내고 나서, 클라이언트가 지정된 url로 요청을 다시 시도하도록 함
//		response.sendRedirect("/mvcapp/color/result/view"); // 그러나 이건 여기서 실행할 일이 아님
		
	}
	
	@Override
	public String getViewPage() {
		return "/mvcapp/color/result/view";
	}
}
