package com.ssg.shop.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.ssg.shop.AppMain;
import com.ssg.shop.common.config.Config;
import com.ssg.shop.common.view.Page;
import com.ssg.shop.home.MainPage;

public class ProductDetailPage extends Page{
	
	JPanel p_wrapper; // 컨텐츠 크기
	JPanel p_img; 
	JPanel p_content;
	JPanel p_bigImg;
	
	MainPage mainPage;
	public ProductDetailPage(AppMain appMain) {
		super(appMain);
		mainPage = (MainPage)appMain.pages[Config.MAIN_PAGE];
		
		// 생성
		p_wrapper = new JPanel();
		p_img = new JPanel();
		p_content = new JPanel();
		p_bigImg = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
//				g.setColor(Color.RED);
//				g.fillRect(0, 0, this.getPreferredSize().getSize().width, this.getPreferredSize().getSize().height);
				Image image = null;
				try {
					image = ImageIO.read(new File("c:/public/"+mainPage.product.getFilenames().getFirst()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				g.drawImage(image, 0, 0, this.getPreferredSize().getSize().width, this.getPreferredSize().getSize().height, this);
			}
		};
		
		// 스타일
		p_wrapper.setBackground(Color.BLUE);
		p_wrapper.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH - 300, Config.SHOPMAIN_HEIGHT - Config.NAVI_HEIGHT - Config.UTIL_HEIGHT));
		p_img.setPreferredSize(new Dimension((p_wrapper.getPreferredSize().getSize().width / 2 - 5), 350));
		p_content.setPreferredSize(new Dimension(p_wrapper.getPreferredSize().getSize().width/2 - 5, 350));
		p_img.setBackground(Color.PINK);
		p_content.setBackground(Color.CYAN);
		p_bigImg.setPreferredSize(new Dimension(p_img.getPreferredSize().getSize().width-20, p_img.getPreferredSize().getSize().height - 60));
		
		// 조립
		p_img.add(p_bigImg);
		
		add(p_wrapper);
		p_wrapper.add(p_img);
		p_wrapper.add(p_content);
		
		setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.SHOPMAIN_HEIGHT - Config.NAVI_HEIGHT - Config.UTIL_HEIGHT));
		setBackground(Color.ORANGE);
		System.out.println("productDetail");
		
		setVisible(true);
	}
}
