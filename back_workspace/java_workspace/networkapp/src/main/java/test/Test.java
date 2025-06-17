package test;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Test implements Runnable{
	int x=3;
	MyThread mt,m2,m3,m4;
	
	Thread t;
	Thread t2;
	JPanel p;
	
	public Test() {
		
		
		//mt.start();
		t=new Thread(this);
		t2=new Thread(this);
		
		
		
		t.start();
		t2.start();
	}
	@Override
	public void run() {
		x++;
		System.out.println("나의 쓰레드 실행"+x);
	}
	public static void main(String[] args) {
		new Test();
	}

}
