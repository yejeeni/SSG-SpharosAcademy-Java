package gui.font;

import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Font;

/* 폰트 설정을 입력하는 클래스 */
class FontSetting extends Frame implements ActionListener, KeyListener{
	
	Frame frame;
	Label la_size;
	Label la_color;
	TextField t_size;
	TextField t_color;
	Panel panel;
	Button btn;
	FontViewer fontViewer;
	
	public FontSetting(FontViewer fontViewer){
		frame = new Frame();
		la_size = new Label("font size");
		la_color = new Label("font color");
		t_size = new TextField(20);
		t_color = new TextField(20);
		btn = new Button("setting");
		panel = new Panel();
		this.fontViewer = fontViewer;
		
		frame.setLayout(new BorderLayout());
		
		btn.addActionListener(this);
	
		panel.add(la_size);
		panel.add(t_size);
		panel.add(la_color);
		panel.add(t_color);
		panel.add(btn);
		
		frame.add(panel);
				
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
	
	// ActionListener 인터페이스를 오버라이딩
	public void actionPerformed(ActionEvent e){
		// System.out.println("FontSetting 버튼 클릭");
		// 폰트 사이즈 변경
		int size = Integer.parseInt(t_size.getText()); // 문자를 숫자로 변환
		this.fontViewer.area.setFont(new Font("굴림", Font.PLAIN, size));
		// 폰트 색 변경
		
	}
	
	// KeyListener 인터페이스를 오버라이딩
	public void keyPressed(KeyEvent e) {
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
	
}
