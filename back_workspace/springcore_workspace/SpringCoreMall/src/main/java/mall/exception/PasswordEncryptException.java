package mall.exception;

public class PasswordEncryptException extends RuntimeException {
	public PasswordEncryptException(String msg) {
		super(msg);
	}

	public PasswordEncryptException(String msg, Throwable e) {
		super(msg, e);
	}

	public PasswordEncryptException(Throwable e) {
		super(e);
	}
}
