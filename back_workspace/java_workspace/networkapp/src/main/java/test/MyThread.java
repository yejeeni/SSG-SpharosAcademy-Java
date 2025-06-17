package test;

public class MyThread extends Thread{
	Test test;
	
	public MyThread(Test test) {
		this.test=test;
	}
	
	public void run() {
		test.x++;
		System.out.println("나의 쓰레드 실행"+test.x);
	}
}
