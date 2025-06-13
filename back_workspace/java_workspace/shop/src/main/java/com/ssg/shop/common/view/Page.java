package com.ssg.shop.common.view;

import javax.swing.JPanel;

import com.ssg.shop.AppMain;

/**
 * 쇼핑몰의 모든 페이지의 최상위 객체
 */
public class Page extends JPanel{
	
	public Page() {
		setVisible(false);
	}
	
	AppMain appMain;
	
	public Page(AppMain appMain) {
		this.appMain = appMain;
	}
}
