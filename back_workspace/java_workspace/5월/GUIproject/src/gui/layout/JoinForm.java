package gui.layout;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

// JoinForm은 extends를 선언하는 순간부터 is a 관계에 의해 Frame이 되어버림. 따라서 JoinForm을 대상으로 new하면 윈도우가 생성됨
class JoinForm extends Frame{
	// 필요한 변수들을 has a 관계로 보유
	Label la_title;
	
	Panel p_center;
	Label la_id;
	TextField t_id;
	Label la_pw;
	TextField t_pw;
	Label la_name;
	TextField t_name;
	
	Panel p_south;
	Button bt_login;
	Button bt_join;
	
	public JoinForm(){
		la_title = new Label("회원가입");
		p_center = new Panel();
		la_id = new Label("ID");
		t_id = new TextField();
		la_pw = new Label("PW");
		t_pw = new TextField();
		la_name = new Label("Name");
		t_name = new TextField();
		p_south = new Panel();
		bt_login = new Button("로그인");
		bt_join =  new Button("회원가입");
		
		// 스타일 적용
		la_title.setBackground(Color.YELLOW);
		t_id.setBackground(Color.YELLOW);
		t_pw.setBackground(Color.YELLOW);
		t_name.setBackground(Color.YELLOW);
		
		// 조립
		// 제목을 북쪽에
		add(la_title, BorderLayout.NORTH);
		
		// 센터 영역
		Dimension d = new Dimension(110, 25);
		la_id.setPreferredSize(d);
		t_id.setPreferredSize(d);
		la_pw.setPreferredSize(d);
		t_pw.setPreferredSize(d);
		la_name.setPreferredSize(d);
		t_name.setPreferredSize(d);
		
		// 센터영역 패널에 컴포넌트 부착
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pw);
		p_center.add(t_pw);
		p_center.add(la_name);
		p_center.add(t_name);
		
		// 패널을 센터영역에 부착
		add(p_center);
		
		// 남쪽영역 패널에 컴포넌트 부착
		p_south.add(bt_login);
		p_south.add(bt_join);
		
		add(p_south, BorderLayout.SOUTH);
		
		MemberListener memberListener = new MemberListener(bt_login, bt_join, this);
		bt_login.addActionListener(memberListener); // 로그인 버튼과 리스너 연결
		bt_join.addActionListener(memberListener); // 가입 버튼과 리스너 연결
		
		this.setSize(350, 200);	
		setVisible(true); // this는 생략 가능
	}
	
	// 회원가입과 관련된 컴포넌트가 전부 회원가입 폼 클래스에 존재하므로, 유효성 검사도 해당 클래스에서 진행하는 것이 효율적
	public void checkForm() {
		if (t_id.getText().length() < 1) {
			System.out.println("아이디를 입력하세요");
		} else if (t_pw.getText().length() < 1) {
			System.out.println("비밀번호를 입력하세요");
		} else if (t_name.getText().length() < 1) {
			System.out.println("이름을 입력하세요");
		}
	}

	public static void main(String[] args) {
		new JoinForm();
	}
}