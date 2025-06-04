package com.ssg.threadApp.animation;

import javax.swing.JProgressBar;

public class ProgressTestThread {

	public Thread returnThread(JProgressBar bar, int sleep) {
		Thread thread = new Thread() {
			int n = 0;

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					n += 2;
					bar.setValue(n);
				}
			}
		};
		return thread;
	}
}
