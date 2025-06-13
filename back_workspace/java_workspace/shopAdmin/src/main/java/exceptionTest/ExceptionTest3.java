package exceptionTest;

public class ExceptionTest3 {

	public static void main(String[] args) throws NumberConvertFailException{
		
		System.out.println("a");
		try {
			throw new NumberConvertFailException("수제 예외");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("b"); // 위에서 에러를 강제로 일으켰기 때문에, throw를 try-catch를 해놓지 않으면 실행될 수 없는 코드 라인
	}
}
