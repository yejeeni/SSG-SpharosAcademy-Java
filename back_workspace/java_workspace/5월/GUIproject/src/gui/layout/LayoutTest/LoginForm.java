package gui.layout;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.GridLayout;
import java.awt.Button;

class LoginForm {
	public static void main(String[] args) {
		
		Frame frame = new Frame();
		Panel panelCenter = new Panel(new GridLayout(2, 2)); 
		Panel panelSouth = new Panel();
		Label labelId = new Label("ID");
		Label labelPassword = new Label("Password");
		TextField textFieldId = new TextField(20);
		TextField textFieldPW = new TextField(20);		
		Button button_login = new Button("로그인");
		Button button_signin = new Button("회원가입");
		
		frame.setLayout(new GridLayout(2, 2));
		
		panelCenter.add(labelId);
		panelCenter.add(textFieldId);
		panelCenter.add(labelPassword);
		panelCenter.add(textFieldPW);
		
		frame.add(panelCenter);
		
		panelSouth.add(button_login);
		panelSouth.add(button_signin);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		frame.setSize(220, 135);
		frame.setVisible(true);
	}
}
