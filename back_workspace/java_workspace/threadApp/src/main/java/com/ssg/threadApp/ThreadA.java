package com.ssg.threadApp;

/**
 * 하나의 자바 프로그램 내에서 비동기적으로 동시에 실행하고 싶은 코드가 있다면 쓰레드로 정의하면 됨
 * run()은 직접 호출하는 것이 아니라 OS가 해당 쓰레드를 스케줄러에 의해 선택하는 순간 JVM이 호출하는 형식.
 */
public class ThreadA extends Thread{
	@Override
	public void run() {
    	for(int i=0; i<50; i++) {
    		System.out.println("A");
    	}
	}
}
