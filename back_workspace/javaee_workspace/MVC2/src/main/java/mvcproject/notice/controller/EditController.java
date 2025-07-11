package mvcproject.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcproject.notice.model.Notice;
import mvcproject.notice.repository.NoticeDAO;
import mvcproject.web.servlet.Controller;

/**
 * 게시물 수정 요청을 처리하는 컨트롤러
 */
public class EditController implements Controller{

	NoticeDAO noticeDAO = new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeId = Integer.parseInt(request.getParameter("notice_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Notice notice = new Notice();
		notice.setNotice_id(noticeId);
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		// 3단계 일시키기
		noticeDAO.update(notice);
		
		// 4단계 저장
		request.setAttribute("notice", notice);
	}
	
	@Override
	public boolean isForward() {
		// 수정 후에는 게시물 페이지를 리다이렉트 해야하나, 현재 상황에서는 매핑 파일에 변수가 올 수 없으므로 포워딩으로 처리해야 함
		return true;
	}
	
	@Override
	public String getViewName() {
		return "/notice/edit/view";
	}
}
