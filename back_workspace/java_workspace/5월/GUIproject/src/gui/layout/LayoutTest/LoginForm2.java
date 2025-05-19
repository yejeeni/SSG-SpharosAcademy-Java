package gui.layout;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Dimension;

class LoginForm2 {
	public static void main(String[] args) {
		
		Frame frame = new Frame();
		Panel panelCenter = new Panel(); 
		Panel panelSouth = new Panel();
		Label labelId = new Label("ID");
		Label labelPassword = new Label("Password");
		TextField textFieldId = new TextField(20);
		TextField textFieldPW = new TextField(20);		
		Button buttonLogin = new Button("로그인");
		Button buttonSignin = new Button("회원가입");
		
		// 크기 설정
		/*
		처음 보는 객체에 대한 대처법
		1) 객체명으로 기능 예측. 예측이 안 되면 조사
		2) 사용하기 위해 메모리에 올리는 방법을 파악(객체 유형은 3가지)
			일반 클래스: new 생성자() / 추상 클래스: 자식으로 구현한 후 자식을 new 생성자() / 인터페이스: 자식으로 구현한 후 자식을 new 생성자() 
		*/
		Dimension dimension = new Dimension(100, 25);
		labelId.setPreferredSize(dimension);
		textFieldId.setPreferredSize(dimension);
		labelPassword.setPreferredSize(dimension);
		textFieldPW.setPreferredSize(dimension);
		
		panelCenter.add(labelId);
		panelCenter.add(textFieldId);
		
		panelCenter.add(labelPassword);
		panelCenter.add(textFieldPW);
		
		// 중앙 패널을 윈도우에 부착
		frame.add(panelCenter);
		
		panelSouth.add(buttonLogin);
		panelSouth.add(buttonSignin);
		
		frame.add(panelSouth, BorderLayout.SOUTH);
		
		frame.setSize(300, 135);
		frame.setVisible(true);
	}
}
