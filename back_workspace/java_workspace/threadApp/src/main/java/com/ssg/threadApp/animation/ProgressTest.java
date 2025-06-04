package com.ssg.threadApp.animation;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * Swing의 UI 컴포넌트 중, 진행율을 표현하는 컴포넌트인 JProgressBar를 사용해보는 예제
 */
public class ProgressTest extends JFrame{
	
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	
	JButton button;
	
	int n;
	
	ProgressTestThread thread;
	
	Thread thread1;
	Thread thread2;
	Thread thread3;
	
	boolean flag = true;
	
	public ProgressTest() {
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
		button = new JButton("진행");
		
		thread = new ProgressTestThread();
				
		thread1 = thread.returnThread(bar1, 100);
		thread2 = thread.returnThread(bar2, 100);
		thread3 = thread.returnThread(bar3, 100);
		
		// 스타일
		Dimension dimension = new Dimension(750, 45);
		bar1.setPreferredSize(dimension);
		bar2.setPreferredSize(dimension);
		bar3.setPreferredSize(dimension);
		
		setLayout(new FlowLayout());
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				thread1.start();
				thread2.start();
				thread3.start();
			}
		});
		
		add(bar1);
		add(bar2);
		add(bar3);
		add(button);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		new ProgressTest();
	}
}
