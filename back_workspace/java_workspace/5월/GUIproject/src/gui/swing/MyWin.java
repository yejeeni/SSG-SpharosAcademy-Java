/*
AWT는 자바의 초창기 GUI 패키지이지만, OS마다 다른 디자인으로 실행
swing은 os, 플랫폼에 상관 없는 중립적인 Look&Feel 디자인을 유지
최근에는 swing처럼 os에 어울리지 않는, java에 최적화된 디자인을 지양하여 javaFX가 지원. 기존 AWT 컴포넌트명에 접두어로 J가 붙는다.
*/

package gui.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyWin extends JFrame implements ActionListener{
	JTextArea area;
	JPanel p_south;
	JButton bt;
	
	public MyWin(){
		area = new JTextArea(4, 15); // 행,열
		p_south = new JPanel();
		bt = new JButton("환경설정");
		
		// area의 배경
		area.setBackground(Color.YELLOW);
		
		add(area); // 센터에 부착
		p_south.add(bt); // 남쪽 패널에 버튼 부착
		
		// 남쪽에 패널 부착
		add(p_south, BorderLayout.SOUTH);
		
		// 버튼에 리스너 부착
		bt.addActionListener(this); // this: 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		
		//setSize(300, 400);
		setBounds(1200, 200, 300, 400);
		setVisible(true);
	}
	
	/* 부모의 메서드 오버라이딩 */
	public void actionPerformed(ActionEvent e){
			new Config(this);
	}
	
	public static void main(String[] args){
		new MyWin();
	}
}
