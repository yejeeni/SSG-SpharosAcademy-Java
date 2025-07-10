package mvcproject.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ColorWin extends JFrame{
	JComboBox<String> box;
	JButton bt;
	
	public ColorWin() {
		box = new JComboBox<>();
		bt = new JButton("판단 요청");
		
		// style
		box.setPreferredSize(new Dimension(185, 30));
		
		// 데이터 채우기
		box.addItem("red");
		box.addItem("blue");
		box.addItem("yellow");
		box.addItem("green");
		
		setLayout(new FlowLayout());
		add(box);
		add(bt);
		bt.addActionListener((e)->{
			getAdvice();
		});
		
		setSize(200, 150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * 입력한 색상에 대한 판단을 하는 메서드
	 */
	public void getAdvice() {
		String color = (String)box.getSelectedItem();
		String msg = null;
		
		if (color.equals("red")){
			msg = "빨";
		} else if(color.equals("blue")){
			msg = "파";
		} else if(color.equals("yellow")){
			msg = "노";
		} else if(color.equals("green")){
			msg = "초";
		}
		
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new ColorWin();
	}
}
