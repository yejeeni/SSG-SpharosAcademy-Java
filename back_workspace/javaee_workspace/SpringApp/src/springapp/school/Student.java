package springapp.school;

/**
 * 애플리케이션에 중복되는 공통 코드는 별도의 객체로 분리시켜 공통 관심사로 지정
 */
public class Student {
	public void getUp() {
		System.out.println("기상합니다");
	}
	public void gotoSchool() {
		System.out.println("등교합니다");
	}
	public void study() {
		System.out.println("수업시작");
	}
	
	public void rest() {
		System.out.println("쉬는 시간 갖기");
	}
	
	public void haveLunch() {
		System.out.println("점심 먹기");
	}
	
	public void goHome() {
		System.out.println("집에 갑니다");
	}
	
	
}


