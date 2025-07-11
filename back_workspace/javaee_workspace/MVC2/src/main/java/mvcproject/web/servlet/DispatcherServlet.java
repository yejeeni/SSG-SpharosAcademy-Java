package mvcproject.web.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 웹애플리케이션에서의 모든 요청을 받는 서블릿
 */
public class DispatcherServlet extends HttpServlet {
	Logger logger = LoggerFactory.getLogger(getClass());
	Properties properties; // key-value 쌍의 데이터를 이해할 수 있는 객체

	/**
	 * 요청이 들어오기 전에 처리해야 하는 것을 처리하는 메서드
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// ServletContext: 이 서블릿이 실행되고 있는 환경. 현재 웹 애플리케이션
		ServletContext context = config.getServletContext();
		String realPath = context.getRealPath(config.getInitParameter("contextConfigLocation"));
		logger.debug(realPath);

		try (FileInputStream fis = new FileInputStream(realPath)) {
			properties = new Properties();
			properties.load(fis); // 스트림 대입
			logger.debug("properties 로딩 성공");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * GET, POST 요청을 처리하는 메서드
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 클라이언트의 요청 URI를 이용하여, 매핑 파일을 검색하고 value를 반환
		String uri = request.getRequestURI();
		String className = properties.getProperty(uri);
		logger.debug(uri);
		logger.debug(className);

		try {
			// 텍스트인 className을 실제 클래스로 로드
			Class myClass = Class.forName(className);
			// new 연산자가 아니라 메서드로 인스턴스 생성
			Controller controller = (Controller) myClass.newInstance();
			
			// 하위 컨트롤러 동작
			controller.execute(request, response); 
			// 하위 컨트롤러가 로직 수행 후, 컨트롤러에 뷰 검색어 반환
			String viewName = controller.getViewName();
			
			// 하위 컨트롤러가 반환한 키워드로 다시 매핑 파일을 검색하여 실제 페이지를 반환
			String viewPage = properties.getProperty(viewName);

			// 클라이언트에게 응답 정보로 재접속을 강요하지 않고, 응답을 보류한 채로 현재 요청의 흐름을 서버의 또다른 서블릿이나 jsp에게 전달하는 포워딩을 수행 (RequestDispatcher 객체 이용)
			if (controller.isForward()) { // 포워딩으로 요청을 유지할 경우
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPage);
				requestDispatcher.forward(request, response); // 요청/응답 객체를 전달하여 포워딩 발생시킴
			} else {
				response.sendRedirect(viewPage);
			}
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}
}
