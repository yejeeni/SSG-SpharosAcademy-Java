package mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * admin 페이지의 xml을 대신하는 java 클래스
 */
@Configuration // 설정용
@EnableWebMvc
@ComponentScan(basePackages = {
	    "mall.shop.controller",
	})
public class UserWebConfig {

	/**
	 * 하위 컨트롤러가 3, 4단계를 수행 후, DispatcherServlet에게 ModelAndView를 반환
	 * DispathcerServlet은 View에 대한 해석을 담당하는 전담 객체인 ViewResolver에게 전달 (JSP의 경우 주로
	 * InternalResourceVuewResolver)
	 * 
	 * ex. 하위 컨트롤러가 notice/list를 심어서 보내면 ViewResolver가 접두어, 접미어를 붙여
	 * /WEB-INF/views/notice/list.jsp를 완성
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		 resolver.setOrder(2);  // 우선순위 2
		return resolver;
	}

}
