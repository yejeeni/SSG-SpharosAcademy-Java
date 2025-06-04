package com.ssg.threadApp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// 하나의 프로세스 내에서 독립적으로 실행할 수 있는 단위인 쓰레드를 2개를 생성하여 각각 별도로 작동하게 하기
    	ThreadA threadA = new ThreadA();
    	ThreadB threadB = new ThreadB();
    	
    	// 쓰레드 생성. start()로 JVM에 전달
    	threadA.start();
    	threadB.start();
    	
//    	for(int i=0; i<50; i++) {
//    		System.out.println("A");
//    	}
//    	for(int i=0; i<50; i++) {
//    		System.out.println("B");
//    	}
    }
}
