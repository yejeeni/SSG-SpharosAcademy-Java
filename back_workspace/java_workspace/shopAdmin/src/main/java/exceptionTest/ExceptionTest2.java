package exceptionTest;

public class ExceptionTest2 {

	public void test1() {
		try {
			test2();
		} catch (NumberConvertFailException e) {
			System.out.println("NumberConvertFailException이 전달됨 - " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void test2() throws NumberConvertFailException{
		String s = "100k";
		
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// 직접 만든 예외를 메모리에 생성하고, 고의로 에러를 일으키기
			throw new NumberConvertFailException("숫자로 변경할 수 없는 문자입니다", e);
		}
	
	}
	
	public static void main(String[] args) {
		new ExceptionTest2().test1();
	}
}
