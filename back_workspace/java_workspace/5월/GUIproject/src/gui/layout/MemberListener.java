package gui.layout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MemberListener implements ActionListener{
	JoinForm joinForm;
	Button bt1;
	Button bt2;
	
	// 매개변수로 가입버튼과 로그인버튼을 받는 생성자
	public MemberListener(Button bt1, Button bt2, JoinForm joinForm){
		this.bt1 = bt1;
		this.bt2 = bt2;
		this.joinForm = joinForm;
	}
	
	// 오버라이딩
	public void actionPerformed(ActionEvent e){
		Button bt = (Button)e.getSource();
		if(bt == bt1){
			System.out.println("로그인 클릭");
			joinForm.checkForm();
			
		} else if(bt == bt2){
			System.out.println("회원가입 클릭");	
		}
	}
}