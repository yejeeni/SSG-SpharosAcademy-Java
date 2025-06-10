package com.ssg.shopadmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ssg.common.config.Config;
import com.ssg.common.view.Page;
import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.config.view.ConfigPage;
import com.ssg.shopadmin.cs.view.CustomerPage;
import com.ssg.shopadmin.main.view.MainPage;
import com.ssg.shopadmin.member.view.MemberJoin;
import com.ssg.shopadmin.member.view.MemberPage;
import com.ssg.shopadmin.order.view.OrderPage;
import com.ssg.shopadmin.product.view.ProductPage;
import com.ssg.shopadmin.security.LoginForm;
import com.ssg.shopadmin.security.model.Admin;

public class AppMain extends JFrame {
	JPanel p_north;
	JPanel p_west; // 사이드 메뉴 영역
	JPanel p_container; // 페이지가 교체될 영역
	JLabel la_user; // 현재 로그인한 유저

	JLabel la_product;
	JLabel la_order;
	JLabel la_member;
	JLabel la_cs;
	JLabel la_config;

	DBManager dbManager = DBManager.getInstance();
	Connection connection; //??
	public Admin admin = new Admin();

	// 모든 페이지를 담을 배열
	Page[] pages;

	public AppMain() {

		p_north = new JPanel();
		p_west = new JPanel(); // 사이드 메뉴 영역
		p_container = new JPanel(); // 페이지가 교체될 영역
		la_user = new JLabel(); // 현재 로그인한 유저

		la_product = new JLabel("상품관리");
		la_order = new JLabel("주문관리");
		la_member = new JLabel("회원관리");
		la_cs = new JLabel("cs관리");
		la_config = new JLabel("쇼핑몰관리");

		// 스타일
		p_north.setPreferredSize(new Dimension(Config.UTIL_WIDTH, Config.UTIL_HEIGHT));
		p_north.setBackground(Color.CYAN);

		p_west.setPreferredSize(new Dimension(Config.SIDE_WIDTH, Config.SIDE_HEIGHT));
		p_west.setBackground(Color.YELLOW);

		p_container.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH - Config.SIDE_WIDTH,
				Config.ADMIN_MAIN_HEIGHT - Config.UTIL_HEIGHT));
		p_container.setBackground(Color.PINK);

		Dimension labelDimension = new Dimension(185, 100);
		la_product.setPreferredSize(labelDimension);
		la_order.setPreferredSize(labelDimension);
		la_member.setPreferredSize(labelDimension);
		la_cs.setPreferredSize(labelDimension);
		la_config.setPreferredSize(labelDimension);

		Font font = new Font(null, Font.BOLD, 15);
		la_product.setFont(font);
		la_order.setFont(font);
		la_member.setFont(font);
		la_cs.setFont(font);
		la_config.setFont(font);

		// 조립
		p_west.add(la_product);
		p_west.add(la_order);
		p_west.add(la_member);
		p_west.add(la_cs);
		p_west.add(la_config);

		add(p_north, BorderLayout.NORTH);
		add(p_west, BorderLayout.WEST);
		add(p_container);

		// 데이터베이스 접속
		connect();

		// 모든 버튼에 이벤트 연결
		// 리스너 중 재정의할 매서드가 3개 이상 되는 경우 미리 메서드를 오버라이딩 해둔 중간 객체인 어댑터가 지원될 가능성이 있음
		la_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.PRODUCT_PAGE);
//				System.out.println("click");
			}
		});
		la_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.ORDER_PAGE);
			}
		});
		la_member.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.MEMBER_PAGE);
			}
		});
		la_cs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CUSTOMER_PAGE);
			}
		});
		la_config.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(Config.CONFIG_PAGE);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// 데이터베이스 접속 해제
				dbManager.release(connection);
				
				// 프로세스 종료
				System.exit(0);
			}
		});

		createPage();
		showPage(Config.LOGIN_PAGE); // 최초 실행시에는 로그인폼이 떠있게

		setBounds(300, 50, Config.ADMIN_MAIN_WIDTH, Config.ADMIN_MAIN_HEIGHT);
		setVisible(true);
	}
	
	public Connection getConnection() {
	    return this.connection;
	}


	/**
	 * 데이터베이스 연결
	 */
	public void connect() {
		connection = dbManager.getConnection();
	}

	/**
	 * 쇼핑몰에 사용할 모든 페이지 생성 및 부착
	 */
	public void createPage() {
		pages = new Page[8];

		pages[Config.LOGIN_PAGE] = new LoginForm(this);
		pages[Config.MAIN_PAGE] = new MainPage(this);
		pages[Config.PRODUCT_PAGE] = new ProductPage(this);
		pages[Config.ORDER_PAGE] = new OrderPage(this);
		pages[Config.MEMBER_PAGE] = new MemberPage(this);
		pages[Config.CUSTOMER_PAGE] = new CustomerPage(this);
		pages[Config.CONFIG_PAGE] = new ConfigPage(this);
		pages[7] = new MemberJoin(this);

		for (int i = 0; i < pages.length; i++) {
			p_container.add(pages[i]);
		}
	}

	/**
	 * 부착된 페이지들을 대상으로, 어떤 페이지들을 보여줄지 결정하는 메서드
	 */
	public void showPage(int target) {
		// 로그인 체크
		if (admin == null && target != Config.JOIN_PAGE && target != Config.LOGIN_PAGE) {
			JOptionPane.showMessageDialog(this, "로그인이 필요한 서비스입니다");
			pages[Config.LOGIN_PAGE].setVisible(true);
			return;
		}

		for (int i = 0; i < pages.length; i++) {
			pages[i].setVisible((i == target) ? true : false);
		}
	}

	public static void main(String[] args) {
		new AppMain();
	}
}
