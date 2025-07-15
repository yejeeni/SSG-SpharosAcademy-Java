package com.example.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 현재는 SpringCore+AOP이므로, AppConfig가 스프링 설정의 진입점
 * @ComponentScan이 있어야 @Component가 붙은 클래스들을 스프링이 스캔해서 자동으로 등록
 * @EnableAspectAutoProxy가 있어야 AOP가 작동
 * 
 * SpringBoot로 변경할 경우, 	@SpringBootApplication에 모두 포함돼있음
 */
@Configuration
@EnableAspectJAutoProxy // AOP 기능 활성화
@ComponentScan("com.example.school") // 메모리에 올릴 대상 패키지. 빈으로 등록되어야 프록시로 감쌀 수 있음
public class AppConfig {
	
}