package com.sinse.electroshop.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String msg) {
        super(msg);
    }
    public MemberNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }
}
