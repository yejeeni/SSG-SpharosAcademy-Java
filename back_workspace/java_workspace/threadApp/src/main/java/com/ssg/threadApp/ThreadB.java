package com.ssg.threadApp;

public class ThreadB extends Thread{
	@Override
	public void run() {
    	for(int i=0; i<50; i++) {
    		System.out.println("B");
    	}
	}
}
