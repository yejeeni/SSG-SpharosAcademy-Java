package com.sinse.electroshop.exception;

public class StoreNotFoundException extends RuntimeException {
    public StoreNotFoundException(String msg) {
        super(msg);
    }
    public StoreNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public StoreNotFoundException(Throwable cause) {
        super(cause);
    }
}
