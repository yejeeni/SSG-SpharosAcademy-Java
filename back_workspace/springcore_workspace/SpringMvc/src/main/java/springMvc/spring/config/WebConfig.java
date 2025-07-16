package springMvc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "springMvc.controller")
@EnableWebMvc
public class WebConfig {
	/**
	 * 하위 컨트롤러들이 DispatcherServlet에게 View의 이름을 반환하면, DispatcherServlet은 이 이름을 이용하여 해당 jsp를 찾아서 forward
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		/**
		 * DispatcherServlet이 추출한 뷰 이름 notice/list의 실제 파일 경로
		 * prefix: "/WEB-INF/views/"
		 * viewName: "notice/list"
		 * suffix: ".jsp"
		 */
		
		return viewResolver; // ex, => /WEB-INF/views/notice/list.jsp
	}
	
//	// SpringBoot
//	@SpringBootApplication
//	public class MyApp {
//	    public static void main(String[] args) {
//	        SpringApplication.run(MyApp.class, args);
//	    }
//	}

}
