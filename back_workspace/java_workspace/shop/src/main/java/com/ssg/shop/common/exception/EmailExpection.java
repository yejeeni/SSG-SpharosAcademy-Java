package com.ssg.shop.common.exception;

public class EmailExpection extends RuntimeException{

	public EmailExpection(String msg) {
		super(msg);
	}
	
	public EmailExpection(Throwable e) {
		super(e);
	}
	
	public EmailExpection(String msg, Throwable e) {
		super(msg, e);
	}
}
