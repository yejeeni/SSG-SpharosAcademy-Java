package myframework.exception;

public class StaffException extends RuntimeException{
	
	public StaffException(String msg) {
		super(msg); //생성자는 물려받지 못하므로, 부모의 생성자를 호출한다..
	}
	public StaffException(String msg, Throwable e) {
		super(msg, e);
	}
	public StaffException(Throwable e) {
		super(e);
	}
	
}