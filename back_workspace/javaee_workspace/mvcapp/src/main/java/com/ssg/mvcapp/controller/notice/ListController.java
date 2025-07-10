package com.ssg.mvcapp.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssg.mvcapp.controller.Controller;
import com.ssg.mvcapp.repository.NoticeDAO;

public class ListController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 알맞는 객체에게 일 시키기
		List list = noticeDAO.selectAll();
		
		// 결과 페이지에서 보여질 데이터 저장
		HttpSession session = request.getSession();
		session.setAttribute("noticeList", list);
		
		
	}
	
	@Override
	public String getViewPage() {
		return "/notice/list/view";
	}
}
