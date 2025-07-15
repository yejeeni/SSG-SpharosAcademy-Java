package springapp.school;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 종 울리기 AOP 메서드
 * 
 * AOP : Aspect Oriented Programming
 * Aspect: AOP 기능을 담은 클래스임을 선언
 * 공통 관심 사항과 핵심 관심 사항(실제 비즈니스 기능 로직)을 분리하여, 원하는 곳에 공통 관심 사항인 BellAspect 벨소리가 울리는 로직을 설정
 */
@Aspect
@Component // 스프링이 이 객체를 자동으로 메모리에 올림
public class BellAspect {
	
	@Autowired // 스프링 컨테이너가 보유한 Bell을 아래 멤버 변수에 자동으로 주입
	private Bell bell; // 공통코드. 횡단적 관심사
	
	@Before("execution(* Student.*(..))") // 어디에 적용할지 범위 타겟팅 (아래 경로의 범위만 적용)
	public void ringBell() {
		bell.sound(); // bell이 보유한 메서드 호출
	}
}
