package com.ssg.shop.common.exception;

public class ProductImgException extends RuntimeException{
	
	// 에러의 원인 및 에러 메시지를 예외의 객체에 담으려면 생성자리에서 처리할 수 있는데, 부모의 생성자는 물려받을 수 없으므로 부모의 생성자를 직접 호출하여 에러 원인 및 에러 메시지를 객체에 담아보기
	public ProductImgException(String msg) {
		super(msg);
	}
	
	// 예외 객체들의 최상위 객체가 Throwable 인터페이스이므로, 어떤 예외가 전달되어도 모두 받을 수 있다
	public ProductImgException(Throwable e) {
		super(e);
	}
	
	public ProductImgException(String msg, Throwable e) {
		super(msg, e);
	}
}
