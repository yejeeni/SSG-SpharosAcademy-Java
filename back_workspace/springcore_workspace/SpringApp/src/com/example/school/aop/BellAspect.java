package com.example.school.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.school.domain.Bell;


@Aspect // AOP 대상 클래스 지정
@Component // 스프링이 이 객체를 자동으로 메모리에 올림
public class BellAspect {
	
	@Autowired // 스프링 컨테이너가 보유한 Bell을 아래 멤버 변수에 자동으로 주입
	private Bell bell; // 공통코드. 횡단점 관심사
	
	@Before("execution(* com.example.school.service.StudentService.*(..))") // 인터페이스 기준으로 적용
	public void ringBell() {
		bell.sound(); // bell이 보유한 메서드 호출
	}
}