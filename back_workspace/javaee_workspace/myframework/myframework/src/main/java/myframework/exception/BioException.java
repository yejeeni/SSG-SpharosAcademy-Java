package myframework.exception;

public class BioException extends RuntimeException{
	
	public BioException(String msg) {
		super(msg); //생성자는 물려받지 못하므로, 부모의 생성자를 호출한다..
	}
	public BioException(String msg, Throwable e) {
		super(msg, e);
	}
	public BioException(Throwable e) {
		super(e);
	}
	
}