package com.ssg.hiberasync.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadServlet extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	String uploadPath;
	
	/**
	 * 이 서블릿의 인스턴스가 생성될 때, 서블릿의 초기화를 담당하는 메서드 init()를 이용하여 이 서블릿에 원하는 정보를 전달 가능 (필터같은 느낌)
	 */
	public void init(ServletConfig config) throws ServletException {
		uploadPath = config.getServletContext().getRealPath(config.getInitParameter("uploadPath"));
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String uploadPath = this.getServletContext().getRealPath("/data");
		logger.debug("저장할 실제 경로" + uploadPath);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		
		// 클라이언트의 요청을 분석
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			logger.debug("전송된 컴포넌트 수 "+list.size());
			
			for (FileItem item : list) {
				if(item.isFormField()) { // 텍스트 컴포넌트의 파라미터일 경우
					logger.debug(item.getString("utf-8"));
				} else { // 파일 컴포넌트의 파라미터일 경우
					logger.debug("파일명 "+item.getName());
					item.write(new File(uploadPath, item.getName()));
					
					HttpSession session = request.getSession(); // 세션은 웹컨테이너가 생성하므로 직접 new하지 않고, 이미 생성된 것을 얻어와야 함
					session.setAttribute("img", item.getName());
				}
			}
			
			// 클라이언트의 브라우저로 하여금 이미지를 볼 수 있는 JSP를 다시 요청
			response.sendRedirect("/Hiberasync/gallery/result.jsp"); // 지정된 url로 클라이언트의 브라우저가 재요청하게 함
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
