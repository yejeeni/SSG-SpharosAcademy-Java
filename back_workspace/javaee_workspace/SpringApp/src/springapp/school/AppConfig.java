package springapp.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 스프링 컨테이너에게 빈을 관리하도록 하는 설정파일
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("springapp.school") // 메모리에 올릴 대상 패키지
public class AppConfig {
	
	@Bean
	public Bell bell() {
		return new Bell();
	}
	
	@Bean
	public Student student() {
		return new Student();
	}
}
