package mall.exception;

public class ProductColorException extends RuntimeException {
	public ProductColorException(String msg) {
		super(msg);
	}

	public ProductColorException(String msg, Throwable e) {
		super(msg, e);
	}

	public ProductColorException(Throwable e) {
		super(e);
	}
}
