package gui.chat;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class ChatB extends Frame implements KeyListener{
	Frame frame;
	TextArea area;
	Panel p_south;
	TextField t_input;
	ChatA chatA;
	
	public ChatB(ChatA chatA) {
		
		frame = new Frame();
		area = new TextArea();
		p_south = new Panel();
		t_input = new TextField(20);
		this.chatA = chatA;
		
		frame.setLayout(new BorderLayout());
		p_south.add(t_input);
		
		area.setBackground(Color.GREEN);
		frame.add(area);
		frame.add(p_south, BorderLayout.SOUTH);
		
		t_input.addKeyListener(this);
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
	
	public TextArea getArea(){
		return this.area;	
	}
	
	public TextField getTextField(){
		return this.t_input;	
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println(this.t_input.getText());
			
			String msg = this.t_input.getText();
			
			this.area.append("[B] " + msg + "\n");
			this.t_input.setText("");
			
			chatA.area.append("[B] " + msg + "\n");
			chatA.t_input.setText("");
		}
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
}
