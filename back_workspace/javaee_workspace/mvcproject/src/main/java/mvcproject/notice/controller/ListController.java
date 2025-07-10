package mvcproject.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.model.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

public class ListController implements Controller{

	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3단계: 일 시키기
		List<Notice> notices = noticeDAO.selectAll();
		
		// 4단계: 데이터 저장
		request.setAttribute("notices", notices);
	}

	@Override
	public String getViewName() {
		return "/notice/list/view";
	}

	@Override
	public boolean isForward() {
		return true;
	}
}
