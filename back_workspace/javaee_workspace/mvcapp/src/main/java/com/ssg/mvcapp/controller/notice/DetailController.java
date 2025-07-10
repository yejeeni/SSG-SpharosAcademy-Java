package com.ssg.mvcapp.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssg.mvcapp.controller.Controller;
import com.ssg.mvcapp.model.Notice;
import com.ssg.mvcapp.repository.NoticeDAO;

public class DetailController implements Controller {
	
	NoticeDAO noticeDAO=new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계 : 일 시키기 
		String notice_id=request.getParameter("notice_id");
		Notice notice=noticeDAO.select(Integer.parseInt(notice_id));
		
		//4단계: 결과 페이지로 가져갈 것이 잇으므로 저장
		HttpSession session=request.getSession();
		session.setAttribute("notice", notice);
	}
	
	public String getViewPage() {
		return "/notice/detail/view";
	}
}
