package gui.chat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class ChatActionListener implements ActionListener {
	
	
	// 오버라이딩
	public void actionPerformed(ActionEvent e){
		new ChatB();
	}
}