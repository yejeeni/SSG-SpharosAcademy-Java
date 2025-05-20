package gui.chat;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.TextField;
import java.awt.TextArea;

class ChatKeyActionListener implements KeyListener{
	
	TextField t_input;
	TextArea area;
	
	public ChatKeyActionListener(ChatA chatA, ChatB chatB){
		/*
		this.t_input = t_input;
		this.area = area;
		*/
	}
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("엔터 키");
			
			String msg = t_input.getText();
			area.append(msg + "\n");
			t_input.setText("");
			
			
		}
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
}