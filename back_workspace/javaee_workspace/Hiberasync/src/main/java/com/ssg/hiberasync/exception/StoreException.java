package com.ssg.hiberasync.exception;

public class StoreException extends RuntimeException {
    public StoreException() {
        super();
    }

    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreException(Throwable cause) {
        super(cause);
    }
}