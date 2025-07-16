package springapp.gui;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyWin extends JFrame{
	private JComponent name;
	private JComponent email;
	private JComponent bt;
	
	// 외부에 있는 스프링으로부터 필요한 빈들을 주입받는 방법
	// 1. 생성자 주입
	// 2. setter 주입
	public MyWin(JComponent name, JComponent email, JComponent bt) {
		this.name = name;
		this.email = email;
		this.bt = bt;
		
		setLayout(new FlowLayout());
		
		add(name);
		add(email);
		add(bt);
		
		setSize(200, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setName(JComponent name) {
		this.name = name;
	}

	public void setEmail(JComponent email) {
		this.email = email;
	}

	public void setBt(JComponent bt) {
		this.bt = bt;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyWin win = context.getBean(MyWin.class);
		
	}
}
