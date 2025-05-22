package gui.graphic;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class MoveTest extends JFrame implements ActionListener{
	JPanel p_north;
	MovePanel p_center;
	JButton bt;
	
	public MoveTest(){
		p_north = new JPanel();
		p_center = new MovePanel();
		bt = new JButton("click");
		
		bt.addActionListener(this);
		
		p_north.setBackground(Color.YELLOW);
		p_north.add(bt);
		
		// p_center.setBackground(Color.GREEN);		
		
		setSize(600, 650);
		setLayout(new BorderLayout());
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		// MovePanel의 빨간색 원을 이동시키기
		p_center.move();
		p_center.repaint();
	}
	
	public static void main(String[] args) {
		new MoveTest();
	}
}
