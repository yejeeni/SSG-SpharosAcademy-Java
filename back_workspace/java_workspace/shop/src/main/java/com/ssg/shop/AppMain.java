package com.ssg.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ssg.shop.common.config.Config;
import com.ssg.shop.common.view.Page;
import com.ssg.shop.home.MainPage;
import com.ssg.shop.member.view.MemberJoin;

public class AppMain extends JFrame {
	JPanel p_north; // p_util, p_navi를 담을 패널
	JPanel p_util; // 최상단 유틸리티 내비
	JPanel p_navi; // 메인 내비게이션
	JPanel p_container; // 모든 페이지가 전환될 컨테이너 영역
	
	// 유틸리티 내비 관련
	JLabel la_login;
	JLabel la_join;
	JLabel la_cart;
	JLabel la_wishList;
	
	// 메인 내비 관련
	JLabel la_home;
	JLabel la_category;
	JLabel la_new;
	JLabel la_best;
	JLabel la_cs;
	
	Page[] pages;
	
	public AppMain() {
		// 생성
		// 패널을 이름 없는 익명 클래스로 재정의. 장점: 별도의 .java 파일 생성 필요 X, 내부 클래스이므로 외부 클래스 MainPage의 멤버를 공유할 수 있음
		p_north = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		p_util = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // 우측 정렬
		p_navi = new JPanel();
		p_container = new JPanel();
		
		la_login = new JLabel("Login");
		la_join = new JLabel("Join");
		la_cart = new JLabel("Cart");
		la_wishList = new JLabel("WishList");
		
		la_home = new JLabel("Home");
		la_category = new JLabel("Category");
		la_new = new JLabel("New");
		la_best = new JLabel("Best");
		la_cs = new JLabel("CS");
		
		// 스타일
		p_util.setBackground(Color.YELLOW);
		p_navi.setBackground(Color.PINK);
		p_container.setBackground(Color.LIGHT_GRAY);
		
		p_north.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT + Config.NAVI_HEIGHT));
		p_util.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.UTIL_HEIGHT));
		p_navi.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.NAVI_HEIGHT));
		p_container.setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, 810));
						
		// 조립		
		p_util.add(la_login);
		p_util.add(la_join);
		p_util.add(la_cart);
		p_util.add(la_wishList);
		
		p_navi.add(la_home);
		p_navi.add(la_category);
		p_navi.add(la_new);
		p_navi.add(la_best);
		p_navi.add(la_cs);
		
		p_north.add(p_util);
		p_north.add(p_navi);		
	
		add(p_north, BorderLayout.NORTH);
		add(p_container);
		
		createPage();
		showPage(Config.MAIN_PAGE);
		
		// 회원가입 버튼 클릭 시 이벤트 발생
		la_join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.JOIN_PAGE);
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE); // DB연동 후 지워야 함
		setVisible(true);
		setBounds(300, 50, 1400, 900);
	}
	
	/**
	 * 쇼핑몰의 모든 페이지를 생성하여 부착하는 메서드
	 */
	public void createPage() {
		// 페이지 리스트
		pages = new Page[2]; // 만든 페이지 수
		
		// 페이지 생성
		pages[Config.MAIN_PAGE] = new MainPage(this);
		pages[Config.JOIN_PAGE] = new MemberJoin(this);
		
		// 모든 페이지를 p_container에 부착
		for (int i=0; i<pages.length; i++) {
			p_container.add(pages[i]);
		}
	}
	
	/**
	 * 원하는 페이지를 보여주는 메서드
	 */
	public void showPage(int target) {
		// 모든 페이지를 같은 자료형의 배열로 준비
		
		for (int i=0; i<pages.length; i++) {
			pages[i].setVisible((i==target)? true:false);
		}
	}
	
	public static void main(String[] args) {
		new AppMain();
	}
}
