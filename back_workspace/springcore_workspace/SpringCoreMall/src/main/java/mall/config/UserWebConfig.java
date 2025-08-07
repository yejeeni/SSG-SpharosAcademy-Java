package mall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

import lombok.extern.slf4j.Slf4j;
import mall.domain.KakaoApi20;
import mall.domain.NaverApi20;

/**
 * admin 페이지의 xml을 대신하는 java 클래스
 */
@Slf4j
@Configuration // 설정용
@PropertySource("classpath:application.properties")
@EnableWebMvc
@ComponentScan(basePackages = { 
		"mall.advice",
		"mall.shop.controller", 
		"mall.shop.service",
		"mall.shop.repository" 
})
public class UserWebConfig {

	// 구글 OAuth 설정
	@Value("${oauth.google.clientId}")
	private String googleClientId;
	@Value("${oauth.google.clientSecret}")
	private String googleClientSecret;
	@Value("${oauth.google.callback}")
	private String googleCallback;

	// 네이버 OAuth 설정
	@Value("${oauth.naver.clientId}")
	private String naverClientId;
	@Value("${oauth.naver.clientSecret}")
	private String naverClientSecret;
	@Value("${oauth.naver.callback}")
	private String naverCallback;
	
	// 카카오 OAuth 설정
	@Value("${oauth.kakao.clientId}")
	private String kakaoClientId;
	@Value("${oauth.kakao.clientSecret}")
	private String kakaoClientSecret;
	@Value("${oauth.kakao.callback}")
	private String kakaoCallback;


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
		resolver.setOrder(2); // 우선순위 2
		return resolver;
	}

	/**
	 * [구글] 로그인 관련 서비스 객체 등록
	 */
	@Bean("googleAuth20Service")
	public OAuth20Service googleAuth20Service() {
		// 클라이언트 id, secret, 리소스 owner 접근 범위, call back 주소
		ServiceBuilder builder = new ServiceBuilder(googleClientId);
		builder.apiSecret(googleClientSecret);
		builder.defaultScope("email profile openid");
		builder.callback(googleCallback);

		return builder.build(GoogleApi20.instance());
	}
	
	/**
	 * [네이버] 로그인 관련 서비스 객체 등록
	 */
	@Bean("naverAuth20Service")
	public OAuth20Service naverAuth20Service() {
		// 클라이언트 id, secret, 리소스 owner 접근 범위, call back 주소
		ServiceBuilder builder = new ServiceBuilder(naverClientId);
		builder.apiSecret(naverClientSecret);
		builder.defaultScope("email profile openid");
		builder.callback(naverCallback);

		return builder.build(NaverApi20.instance());
	}

	/**
	 * [kakao] 로그인 관련 서비스 객체 등록
	 */
	@Bean("kakaoAuth20Service")
	public OAuth20Service kakaoAuth20Service() {
		// 클라이언트 id, secret, 리소스 owner 접근 범위, call back 주소
		log.debug("kakaoClientId: {}", kakaoClientId);
	    log.debug("kakaoCallback: {}", kakaoCallback);
		ServiceBuilder builder = new ServiceBuilder(kakaoClientId);
		builder.apiSecret(kakaoClientSecret);
		builder.defaultScope("profile_nickname profile_image");
		builder.callback(kakaoCallback);

		return builder.build(KakaoApi20.instance());
	}


}
