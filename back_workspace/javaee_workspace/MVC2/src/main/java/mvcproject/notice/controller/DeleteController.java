package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class DeleteController implements Controller{
	
	NoticeDAO noticeDAO = new NoticeDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notice_id = request.getParameter("notice_id");
		
		noticeDAO.delete(Integer.parseInt(notice_id));
	}

	@Override
	public boolean isForward() {
		return false; // 새로 들어와야 함
	}

	@Override
	public String getViewName() {
		return "/notice/delete/view";
	}

}
