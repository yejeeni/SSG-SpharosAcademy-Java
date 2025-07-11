package mvcproject.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 최상위 컨트롤러 인터페이스 
 */
public interface Controller {
	/**
	 * DispatcherServlet 대신 요청을 처리하는 메서드. request, response가 필요함
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) 	throws ServletException, IOException;

	/**
	 * 포워딩 여부를 결정하는 메서드
	 * @return
	 */
	public boolean isForward();
	
	/**
	 * DispacherServlet에게 뷰 페이지를 검색할 수 있는 검색어를 반환하는 메서드
	 * @return 뷰페이지 검색어
	 */
	public String getViewName();
}
