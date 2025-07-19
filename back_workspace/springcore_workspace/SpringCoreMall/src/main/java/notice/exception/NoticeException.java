package notice.exception;

public class NoticeException extends RuntimeException{
	   
	   public NoticeException(String msg) {
	      super(msg);   
	   }
	   
	   public NoticeException(String msg, Throwable e) {
	      super(msg, e);
	   }
	   
	   public NoticeException(Throwable e) {
	      super(e);
	   }
	}
