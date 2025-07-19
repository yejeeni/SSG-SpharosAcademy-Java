package mall.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * admin 페이지의 xml을 대신하는 java 클래스
 */
@Configuration // 설정용
@EnableWebMvc
@Import(RootConfig.class) // 루트 컨텍스트 설정 불러오기(빈 공유)
@ComponentScan(basePackages = { "mall.admin.controller", "mall.repository", // DAO
		"mall.service" // Service
})
public class AdminWebConfig extends WebMvcConfigurerAdapter {
	/**
	 * 하위 컨트롤러가 3, 4단계를 수행 후, DispatcherServlet에게 ModelAndView를 반환
	 * DispathcerServlet은 View에 대한 해석을 담당하는 전담 객체인 ViewResolver에게 전달 (JSP의 경우 주로
	 * InternalResourceVuewResolver)
	 * 
	 * ex. 하위 컨트롤러가 notice/list를 심어서 보내면 ViewResolver가 접두어, 접미어를 붙여
	 * /WEB-INF/views/notice/list.jsp를 완성
	 */
	@Bean("adminViewResolver")
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1); // 우선순위 1
		return resolver;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// pom.xml에 추가한 jackson-bind 라이브러리의 객체 추가
		converters.add(new MappingJackson2HttpMessageConverter());
	}
	
	/**
	 * 파일 업로드 설정
	 * 아파치 파일 업로드를 스프링에서 내부적으로 처리한 업로드 빈
	 * 클라이언트가 파일을 전송할 때 사용
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(10*1024*1024); // 10M
		return resolver;
	}
	
	
	
	
	
	
}
