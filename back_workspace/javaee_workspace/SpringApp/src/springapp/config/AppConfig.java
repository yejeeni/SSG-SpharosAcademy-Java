package springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springapp.cook.Cook;
import springapp.cook.Frypan;
import springapp.cook.Induction;

/**
 * 전통적인 스프링 개발 방식에서는 XML이 주로 사용되었으음
 * Spring 2.5 버전부터 java 클래스에서도 어노테이션 기반의 설정 방식을 지원하기 시작하여, 스프링 부트에 이르러서는 java 기반 설정을 이용
 */

@Configuration
public class AppConfig {
	
	// 스프링의 관리 대상이 되는 클래스: 빈
	@Bean
	public Frypan frypan() {
		return new Frypan();
	}
	
	@Bean
	public Induction induction() {
		return new Induction();
	}
	
	/**
	 * 요리사 빈을 생성자 주입으로 위빙(weaving)
	 * @return
	 */
	@Bean
	public Cook cook() {
		return new Cook(frypan()); // 생성자 주입
	}
	
}
