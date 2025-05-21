package gui.font;

import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;

/* 폰트 설정을 적용한 텍스트를 출력하는 클래스 */
class FontViewer extends Frame implements ActionListener, KeyListener{
	
	Frame frame;
	TextArea area;
	Panel panel;
	Button btn;
	FontSetting fontSetting;
	
	
	public FontViewer(){
		frame = new Frame();
		area = new TextArea();
		btn = new Button("setting open");
		panel = new Panel();
		
		btn.addActionListener(this);
		
		frame.setLayout(new BorderLayout());
	
		area.setBackground(Color.YELLOW);
		panel.add(area);
		panel.add(btn);
		
		frame.add(panel);
				
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
	
	// ActionListener 인터페이스를 오버라이딩
	public void actionPerformed(ActionEvent e){
		this.fontSetting = new FontSetting(this);
	}
	
	// KeyListener 인터페이스를 오버라이딩
	public void keyPressed(KeyEvent e) {
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
	
	public static void main(String args[]){
		new FontViewer();
	}
}
