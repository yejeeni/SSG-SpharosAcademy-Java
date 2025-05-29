package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.pages.CS;
import shop.pages.Home;
import shop.pages.MyPage;
import shop.pages.Page;
import shop.pages.Product;
import util.ImageUtil;

/**
 * 화면을 4개 보유한 쇼핑몰 어플리케이션
 * 화면 전환 처리 방법 배우기
 */
public class ShopMain extends JFrame implements ActionListener{
	JPanel p_north; // 북쪽 패널
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center; // 프로그램 가동과 동시에 앞으로 사용할 페이지들을 미리 부착할 패널
	
	ImageUtil imageUtil;
	
	// 쇼핑몰을 구성하는 모든 페이지
	Page[] pageArray;
	Home home;
	Product product;
	MyPage myPage;
	CS cs;
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int MYPAGE = 2;
	public static final int CS = 3;
	
	public ShopMain() {
		imageUtil = new ImageUtil();
		
		
		// 생성
		p_north = new JPanel();
		bt_home = new JButton(imageUtil.getIcon("main.png", 23, 23));
		bt_product = new JButton(imageUtil.getIcon("shopping-cart.png", 25, 25));
		bt_mypage = new JButton(imageUtil.getIcon("user.png", 25, 25));
		bt_cs = new JButton(imageUtil.getIcon("call.png", 30, 30));
		
		// 버튼에 값을 심을 수 있음
		bt_home.putClientProperty("id", HOME);
		bt_product.putClientProperty("id", PRODUCT);
		bt_mypage.putClientProperty("id", MYPAGE);
		bt_cs.putClientProperty("id", CS);
		
		p_center = new JPanel();
		
		pageArray = new Page[4];
		pageArray[0] = new Home();
		pageArray[1] = new Product();
		pageArray[2] = new MyPage();
		pageArray[3] = new CS();
		
		// 스타일
		p_center.setPreferredSize(new Dimension(800, 50));
		p_north.setBackground(Color.LIGHT_GRAY);
		
		Dimension dimension = new Dimension(35, 35);
		bt_home.setPreferredSize(dimension);
		bt_product.setPreferredSize(dimension);
		bt_mypage.setPreferredSize(dimension);
		bt_cs.setPreferredSize(dimension);
		
		bt_home.addActionListener(this);
		bt_product.addActionListener(this);
		bt_mypage.addActionListener(this);
		bt_cs.addActionListener(this);
		
		// 조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		
		// 모든 페이지를 센터 패널에 부착
		for (int i=0; i<pageArray.length; i++) {
			p_center.add(pageArray[i]);			
		}
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);

		setSize(800, 650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 가운데 위치하게
	}
	
	/**
	 * 원하는 페이지만 보여지게 처리하는 메서드
	 */
	public void showPage(int target) {
		for(int i=0; i<this.pageArray.length; i++) {
			if (i==target) {
				this.pageArray[i].setVisible(true);	
			} else {
				this.pageArray[i].setVisible(false);
			}
		}
	}
	
	public static void main(String[] args) {
		new ShopMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton object = (JButton)e.getSource();
		int id = (int)object.getClientProperty("id");
//		System.out.println(id);
		
		showPage(id);
	}
}
