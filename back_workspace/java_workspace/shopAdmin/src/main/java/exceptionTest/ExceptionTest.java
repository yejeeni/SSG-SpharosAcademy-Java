package exceptionTest;

/*
 * Exception에 대해 공부한 클래스
 * 에러: 시스템 레벨에서 발생하여 프로그램 코드에 발생하는 치명적인 문제 
 * 예외: 직접 처리가 가능한 오류
 * 		    1. 강요된 예외 - 컴파일러 처리를 강요하는 예외 (ex, 인덱스를 벗어남).
 * 			2. 강제하지 않는 예외 - 코드의 논리적 오류로 인해 발생하는 심각하지 않은 예외. 최상위 객체로 RuntimeException을 갖는다 
 * 
 */
public class ExceptionTest {

	public void test() throws ClassNotFoundException {
		load();
	}
	
	/**
	 * throws를 가진 메서드를 호출한 메서드에 예외 처리 책임을 전가시킴
	 * @throws ClassNotFoundException
	 */
	public void load() throws ClassNotFoundException{
		Class.forName("");
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		ExceptionTest exceptionTest = new ExceptionTest();
		exceptionTest.test();
		
	}
}
