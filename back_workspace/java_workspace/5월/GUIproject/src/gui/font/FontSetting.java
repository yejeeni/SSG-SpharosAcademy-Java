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

class FontSetting extends Frame implements ActionListener, KeyListener{
	
	Frame frame;
	Label la_size;
	Label la_color;
	TextField t_size;
	TextField t_color;
	Panel panel;
	Button btn;
	
	public FontSetting(){
		frame = new Frame();
		la_size = new Label("font size");
		la_color = new Label("font color");
		t_size = new TextField(20);
		t_color = new TextField(20);
		btn = new Button("setting");
		panel = new Panel();
		
		frame.setLayout(new BorderLayout());
	
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
		
	}
	
	// KeyListener 인터페이스를 오버라이딩
	public void keyPressed(KeyEvent e) {
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
	
}
