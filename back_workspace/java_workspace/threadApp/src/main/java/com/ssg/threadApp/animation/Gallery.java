package com.ssg.threadApp.animation;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Gallery extends JFrame{
	JPanel p_west; // 썸네일이 그려질 통 패널
	JPanel p_container; // 북쪽 큰 사진 패널들을 감쌀 바깥쪽 컨테이너
	JPanel p_north; // 북쪽 컨트롤러 영역
	JPanel p_center; // 큰 사진이 보여질 영역
	
	ImageUtil imageUtil = new ImageUtil();
	Image[] imgArray = new Image[9];
	
	int currentIndex; // 클릭한 이미지의 인덱스
	float rectY = 10;
	int target = 10;
	
	Thread thread;
	
	// 화면에 렌더링하지는 앉지만, 원하는 좌표에 사각형을 메모리상에 존재시키면 해당 영역에 이벤트를 부여할 수 있다.
	Rectangle[] rectangles = new Rectangle[imgArray.length];
	
	public Gallery() {
		createImage(); // 그려지기 전에 이미지를 준비해놓아야 함
		
		 thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(10);
						move(target);
						p_west.repaint();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} 
		 };
		 
		 thread.start();
		
		p_west = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				for (int i=0; i<imgArray.length; i++) {
					g.drawImage(imgArray[i], 5, 10+(i*95), 90, 90, p_west);					
				}
				
				// 그려진 이미지 위로 옮겨다닐 사각 포인터
				Graphics2D g2 = (Graphics2D)g;
				g2.setStroke(new BasicStroke(5));
				g.setColor(Color.RED);
				g.drawRect(5, (int)rectY, 90, 90);
			}
		};
		p_north = new JPanel();
		p_center = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(imgArray[currentIndex], 0, 0, 800, 850, p_center);
			}
		};
		p_container = new JPanel();
		
		// 스타일
		p_west.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p_west.setPreferredSize(new Dimension(100, 800));
		
		add(p_west, BorderLayout.WEST);
		p_container.setLayout(new BorderLayout());
		p_container.add(p_north, BorderLayout.NORTH);
		p_container.add(p_center);
		add(p_container);
		
		// 좌측 패널에 마우스 이벤트 연결
		p_west.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i=0; i<rectangles.length; i++) {
					if (rectangles[i].contains(e.getPoint())) {
						System.out.println("click");
						currentIndex = i;						
						p_center.repaint();
						target = rectangles[i].y;
					}
				}
			}
		});
		
		setSize(900, 910);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public void createImage() {
		for (int i=0; i<imgArray.length; i++) {
			imgArray[i] = imageUtil.getImage("images/geographic/animal"+(i+1)+".jpg", 90, 90);	
			rectangles[i] = new Rectangle(5, 10+(i*95), 90, 90);		
		}
	}
	
	/*
	 * 포인터가 목적지에 한 번에 도달하게 하지 않고 부드럽게 감속도운동을 하게 하는 메서드
	 */
	public void move(int target) {
		// y = 현재 y + a * (목적지 y - 현재 y)
		float a = 0.1f;
		
		rectY = (rectY + a * (target + - rectY));
	}
	
	public static void main(String[] args) {
		new Gallery();
	}
}
