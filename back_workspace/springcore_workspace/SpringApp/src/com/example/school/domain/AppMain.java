package com.example.school.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.school.config.AppConfig;
import com.example.school.service.StudentService;


public class AppMain {
	public static void main(String[] args) {
		// Spring Core + AOP 기반 설정 방식이라 직접 설정 클래스를 넘김
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		// 스프링이 Student 클래스의 프록시 객체를 만들어 등록
		// getUp()을 호출 시 먼저 프록시가 BellAspect의 @Before을 실행 후, 그 다음에 진짜 student.getUp() 호출
		StudentService student = context.getBean(StudentService.class); // 인터페이스 기준
		
		System.out.println("Impl, @Component 학습 예제");
		student.getUp();
		student.gotoSchool();
		student.rest();
		student.haveLunch();
		student.goHome();
	}
}
