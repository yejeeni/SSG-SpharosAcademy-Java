package com.ssg.threadApp.animation;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameAnimation extends JFrame {

	JPanel p_center;
	JButton bt;
	Image image;
	Image[] images = new Image[18];
	int index = 0;

	public FrameAnimation() {

//		image = getImage("images/hero/image2.png", 300, 300);

		// 이미지를 담는 배열 생성
		for (int i = 0; i < 18; i++) {
			images[i] = getImage("images/hero/image" + (i+1) + ".png", 200, 200);
		}

		p_center = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); // super을 남겨놓아야 update()에 의해 지워진 배경을 스스로 복구

				// Toolkit은 이미지를 구성하는 바이트 정보에 접근 불가
				// BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 다양한 제어가 가능
				// 원하는 그림 draw
				g.drawImage(images[index % images.length], 0, 0, 200, 200, p_center); // index는 계속 증가하므로 길이만큼 나눴을 때의 나머지를 인덱스로 하게 해야 함
			}
		};
		
		add(p_center);
		
		Thread thread = new Thread() {
			// 쓰레드로 구현하고 싶은 코드를 run() 메서드 안에 넣어야 함
			public void run() {
				while(true) {
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					index++;
					p_center.repaint();
				}
			}
		};
		
		thread.start();
		

		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * 클래스패스로부터 이미지를 반환하는 메서드
	 * 
	 * @param filename
	 * @param width
	 * @param height
	 * @return
	 */
	public Image getImage(String filename, int width, int height) {
		// 패키지 경로로부터 이미지 얻어오기
		// url로 이미지 얻어오기
		URL url = this.getClass().getClassLoader().getResource(filename);
		Image image = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(url);
			image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

	public static void main(String[] args) {
		new FrameAnimation();
	}
}
