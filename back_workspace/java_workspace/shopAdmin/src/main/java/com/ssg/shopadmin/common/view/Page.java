package com.ssg.shopadmin.common.view;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.common.config.Config;

/**
 * 쇼핑몰 관리자 페이지의 최상단 객체
 */
public class Page extends JPanel{
	// 모든 페이지는 AppMain의 안에 소속되어 있으므로, 서로 공유할 데이터가 있다면 AppMain을 통해 공유하도록 함
	public AppMain appMain;
	
	public Page(AppMain appMain) {
		this.appMain = appMain;
		
		setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH - Config.SIDE_WIDTH, Config.ADMIN_MAIN_HEIGHT-Config.UTIL_HEIGHT));
		setVisible(false);
	}
}
