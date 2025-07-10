package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.model.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

/**
 * 등록을 처리하는 컨트롤러
 */
public class RegistController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 일 시키기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.insert(notice);
		
		// 저장할 데이터는 없음
	}
	
	@Override
	public boolean isForward() {
		return false; // 포워딩 X
	}

	@Override
	public String getViewName() {
		return "/notice/regist/view";
	}


}
