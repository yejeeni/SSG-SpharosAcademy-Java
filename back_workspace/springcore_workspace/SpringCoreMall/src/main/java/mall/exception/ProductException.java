package mall.exception;

public class ProductException extends RuntimeException {
	public ProductException(String msg) {
		super(msg);
	}

	public ProductException(String msg, Throwable e) {
		super(msg, e);
	}

	public ProductException(Throwable e) {
		super(e);
	}
}
