package graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AniTest extends JFrame{
	/*
	 * 자바의 모든 컴포넌트는 스스로를 그린다. 하지만 그리는 과정을 뺏어도 되는 컴포넌트는 주로 내용이 비어있는 컨테이너 유형(JPanel, Canvas)이다.
	 * */
	JButton bt;
	JPanel p_center;
	int x;
	int y;
	
	public AniTest() {
		bt = new JButton() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.RED);
				g.fillRect(0, 0, 70, 30);
			}
		};
		
		p_center = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawRect(x, y, 200, 200); // 내부클래스는 외부클래스의 변수에 접근 가능
			}

		};
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				x+=5;
				y+=5;
				
				p_center.repaint();
			}
		});
		add(bt, BorderLayout.NORTH);
		add(p_center);
		
		setSize(800, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	public static void main(String[] args) {
		new AniTest();
	}

}
