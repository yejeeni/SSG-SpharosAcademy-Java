package gui.chat;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/*					is a					  is a					*/
class ChatA extends Frame implements ActionListener, KeyListener{
	/* has a */
	Frame frame;
	TextArea area;
	Button bt_open;
	Panel p_south;
	TextField t_input;
	ChatB chatB;	
		
	public ChatA() {
		
		frame = new Frame();
		area = new TextArea();
		bt_open = new Button("오픈");
		p_south = new Panel();
		t_input = new TextField(20);
		chatB = null;
		/*
		ChatActionListener chatActionListener = new ChatActionListener(this);
		bt_open.addActionListener(chatActionListener);
		*/
		
		frame.setLayout(new BorderLayout());
		p_south.add(t_input);
		p_south.add(bt_open);
		
		area.setBackground(Color.YELLOW);
		frame.add(area);
		frame.add(p_south, BorderLayout.SOUTH);
		
		// 리스너 연결
		bt_open.addActionListener(this);
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
	
	// ActionListener 인터페이스를 오버라이딩
	public void actionPerformed(ActionEvent e){
		this.chatB = new ChatB(this);
		
		//this.chatB.getTextField().addKeyListener(this);
		
		// System.out.println("click");
		//ChatKeyActionListener chatKeyActionListener =  new ChatKeyActionListener(this, chatB);
		//t_input.addKeyListener(chatKeyActionListener);
	}
	
	// KeyListener 인터페이스를 오버라이딩
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println(e.getSource());
			
			String msg = this.t_input.getText();
			this.area.append("[A] " + msg + "\n");
			this.t_input.setText("");
			
			chatB.area.append("[A] " + msg + "\n");
			chatB.t_input.setText("");
		}
	}

	public void	keyReleased(KeyEvent e){
	}
	
	public void	keyTyped(KeyEvent e){
	}
	
	
	public static void main(String[] args) {
		new ChatA();
	}
}
