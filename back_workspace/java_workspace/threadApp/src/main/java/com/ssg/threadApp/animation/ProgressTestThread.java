package com.ssg.threadApp.animation;

import javax.swing.JProgressBar;

public class ProgressTestThread {

	
	public Thread returnThread(JProgressBar bar, int sleep) {
		int n = 2;
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					move(bar, n);
				}
			}
		};
		
		return thread;
	}
	

	public void move(JProgressBar bar, int n) {
		n+=2;
		bar.setValue(n); // bar 증가
	}
}

