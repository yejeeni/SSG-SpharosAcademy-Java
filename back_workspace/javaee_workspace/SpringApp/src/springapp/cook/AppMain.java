package springapp.cook;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springapp.config.AppConfig;

public class AppMain {
	
	public static void main(String[] args) {
		// 인스턴스를 싱글톤으로 생성 및 보관해주는 객체인 ApplicationContext (=스프링 컨테이너)
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 구성 정보를 AppConfig.class로 지정
		
		Cook cook = applicationContext.getBean(Cook.class);
		cook.makeFood();
		
	}
}
