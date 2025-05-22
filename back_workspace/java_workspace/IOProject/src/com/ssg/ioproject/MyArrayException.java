package com.ssg.ioproject;

/*
 * 언체크 예외의 경우, 예외를 커스텀할 수 있음
 * RunetimeException을 상속받아야 함 (언체크 예외의 상위 객체)
 * */

public class MyArrayException extends ArrayIndexOutOfBoundsException{

	public MyArrayException(String msg) {
		super(msg);
	}
}