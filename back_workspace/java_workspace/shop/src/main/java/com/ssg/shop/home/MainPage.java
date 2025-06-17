package com.ssg.shop.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import com.ssg.shop.AppMain;
import com.ssg.shop.common.config.Config;
import com.ssg.shop.common.util.ImageUtil;
import com.ssg.shop.common.view.Page;
import com.ssg.shop.product.model.Product;
import com.ssg.shop.product.repository.ProductDAO;
import com.ssg.shop.product.view.ProductItem;

public class MainPage extends Page{
	JPanel p_visual; // 메인 비주얼 영역(배너)
	JPanel p_content; // 상품 출력 영역
	ImageUtil imageUtil = new ImageUtil();
	Image image;
	ProductDAO productDAO = new ProductDAO();
	// 최신 상품 목록 중, 현재 선택한 상품
	public Product product;
	
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
				if (image != null) {
					g.drawImage(image, 0, 0, Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT, p_visual);
				} else {
					System.err.println("MainPage: image가 null이어서 그리지 않음");
				}
			}
		};
		
		p_content = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

		// 스타일
		p_visual.setPreferredSize(new Dimension(Config.MAIN_VISUAL_WIDTH, Config.MAIN_VISUAL_HEIGHT));
		p_content.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, 410));
		
		this.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT - (Config.UTIL_HEIGHT+Config.NAVI_HEIGHT)));
		
		p_visual.setBackground(Color.CYAN);
		p_content.setBackground(Color.RED);
		
		// 최신 상품 생성하기
		createRecentList();
		
		// 조립
		add(p_visual);
		add(p_content);
		
		setVisible(true);
	}
	
	/**
	 * 최신 상품 패널에 상품을 원하는 수 만큼 p_content에 출력하는 매서드
	 */
	//최신 상품 패널 원하는 수만큼 p_content에 출력 
		public void createRecentList() {
			List<Product> productList=productDAO.selectRecentList(2);
			System.out.println(productList);
			
			for(int i=0;i<productList.size();i++) {
				Product product = productList.get(i); //리스트에서 상품을 하나씩 꺼내자!!
				
				for(int a=0;a<product.getFilenames().size();a++) {
					System.out.println(i+"번째 "+product.getFilenames().get(a));
				}
				
				ProductItem productItem = new ProductItem(this, product);//상품 하나를 표현하는 디자인 카드
				
				p_content.add(productItem);
			}
			
		}
}
