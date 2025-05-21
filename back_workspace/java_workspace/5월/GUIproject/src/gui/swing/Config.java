package gui.swing;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Config extends JFrame implements ActionListener{
	JTextField t_size;
	JButton bt;
	MyWin myWin;
	
	public Config(MyWin myWin){ // 외부에서 MyWin을 전달받아, call-by-reference
		t_size = new JTextField(20);
		bt = new JButton("설정 적용");
		this.myWin = myWin;
		
		setLayout(new java.awt.FlowLayout()); // 컴포넌트들이 자신 본연의 크기를 유지해야 함
		
		add(t_size);
		add(bt);
		
		bt.addActionListener(this);
		
		setBounds(1500, 200, 300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		// MyWin이 보유한 area의 폰트의 크기를 설정. 이때 폰트의 크기값은 나의 TextField로부터 얻은 값
		
		/* java에는 Wrapper 클래스가 있음
		자바의 기본/객체 자료형
		1.	숫자
					정수: byte		short		int				long
			Wrapper:  Byte		Short	Integer		Long
					실수: float		double
			Wrapper: Float		Double
			
		2.			문자: char
			Wrapper: Character
			
		3.		논리값: bollean
			Wrapper:  Boolean
		
		기본자료형과 객체자료형과 변환이 가능하도록 지원되는 객체들이 있는데, 이런 객체를 가리켜 wrapper 클래스라고 한다.
		int x = 3; <- 기본자료형 3
		Integer(int x = 3); <- 객체자료형 3
		
		객체자료형은 메서드나 속성을 이용할 수 있으므로, 기본자료형으로 할 수 없었던 일을 할 수 있다.
		ex) Integer.parseInt("45"); -> 45
		
		*/
		int size = Integer.parseInt(t_size.getText());
		myWin.area.setFont(new Font(null, 0, size));
	}
}