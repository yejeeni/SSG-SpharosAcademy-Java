package com.ssg.shop.common.exception;

public class MemberExpection extends RuntimeException{

	public MemberExpection(String msg) {
		super(msg);
	}
	
	public MemberExpection(Throwable e) {
		super(e);
	}
	
	public MemberExpection(String msg, Throwable e) {
		super(msg, e);
	}
}
