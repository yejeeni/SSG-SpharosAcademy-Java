package com.ssg.mvcapp.abo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssg.mvcapp.abo.repository.ABOManager;
import com.ssg.mvcapp.controller.Controller;

/**
 * 혈액형에 대한 판단 요청을 처리하는 컨트롤러 정의
 */
public class ABOController implements Controller{
										// is a
	ABOManager aboManager = new ABOManager();
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청을 받음
		String abo = req.getParameter("abo");
		
		// 일 시키기
		String result = aboManager.getAdvice(abo);
		
		// 뷰로 가져갈 값을 저장
		HttpSession session = req.getSession();
		session.setAttribute("msg", result);
		
		// 알맞는 뷰 보여주기
//		resp.sendRedirect("/mvcapp/ABO/result/view"); // 그러나 이건 컨트롤러에서 하는 일이 아님
	}
	
	@Override
	public String getViewPage() {
		return "/mvcapp/ABO/result/view";
	}
}
