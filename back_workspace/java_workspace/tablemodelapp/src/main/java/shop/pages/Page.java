package shop.pages;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 쇼핑몰의 모든 페이지들의 최상의 객체
 */
public class Page extends JPanel{
	JLabel la_title;
	
	public Page(String title) {
		la_title = new JLabel(title);
		la_title.setFont(new Font("고딕", Font.BOLD, 30));
			
		add(la_title);
		
		setPreferredSize(new Dimension(800, 650));
		setVisible(false);
	}
}
