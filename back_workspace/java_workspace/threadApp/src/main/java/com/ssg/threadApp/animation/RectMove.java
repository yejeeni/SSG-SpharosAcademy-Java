package com.ssg.threadApp.animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 지우고 그리는 작업을 연속적으로 하여 사각형을 이동시키기
 */
public class RectMove extends JFrame{
	JButton bt;
	JPanel p_north;
	JPanel p_center;
	
	int x = 0;
	int y = 0;
	
	Thread thread;
	
	public RectMove() {		
		bt = new JButton("동작");
		p_north = new JPanel();
		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.setColor(Color.BLUE);
				g.fillRect(x, y, 50, 50);
			}
		};
		
		thread = new Thread() {
			// 쓰레드로 구현하고 싶은 코드를 run() 메서드 안에 넣어야 함
			public void run() {
				while(true) {
					move();		
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread.start();
			}
		});
		p_north.add(bt);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
				
		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void move() {
		x++;
		y++;
		
		// 그림을 다시 그리기
		p_center.repaint(); // update()가 호출되어 화면을 지우고, paint()가 호출되어 그림을 그림
	}
	
	public static void main(String[] args) {
		new RectMove();
	}
}
