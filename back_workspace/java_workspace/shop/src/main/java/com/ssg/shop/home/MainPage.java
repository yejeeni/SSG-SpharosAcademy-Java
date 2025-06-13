package com.ssg.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.ssg.shop.AppMain;
import com.ssg.shop.common.config.Config;
import com.ssg.shop.common.util.ImageUtil;
import com.ssg.shop.common.view.Page;

public class MainPage extends Page{
	JPanel p_visual; // 메인 비주얼 영역(배너)
	JPanel p_content; // 상품 출력 영역
	ImageUtil imageUtil = new ImageUtil();
	Image image;
	AppMain appMain;
	
	public MainPage(AppMain appMain) {
		super(appMain);
		
		// 생성
		image = imageUtil.getImage("images/post-image1.jpg", Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT);

		p_visual = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g); // super을 남겨놓아야 update()에 의해 지워진 배경을 스스로 복구
				
				// Toolkit은 이미지를 구성하는 바이트 정보에 접근 불가
				// BufferedImage 객체를 이용하여 얻어온 이미지는 훨씬 다양한 제어가 가능
				// 원하는 그림 draw
				g.drawImage(image, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual);
			}
		};
		
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

		// 스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, 410));
		
		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT - (Config.UTIL_HEIGHT+Config.NAVI_HEIGHT)));
		
		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.RED);
		
		// 조립
		add(p_visual);
		add(p_content);
		
		setVisible(true);
	}
}
