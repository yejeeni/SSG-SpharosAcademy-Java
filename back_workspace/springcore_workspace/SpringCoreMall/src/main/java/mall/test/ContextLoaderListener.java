package mall.test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 톰캣이 가동될 때를 감지하는 클래스
 */
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		System.out.println("서버가동");
		
		// 애플리케이션 수준의 객체인 ServletContext에 이름을 저장
		ServletContext context = sce.getServletContext();
		context.setAttribute("text", "hello world");
		
		// 
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		System.out.println("서버중단");
	}

}
