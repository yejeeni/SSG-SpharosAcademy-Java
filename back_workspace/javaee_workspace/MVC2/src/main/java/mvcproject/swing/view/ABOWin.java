package mvcproject.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ABOWin extends JFrame{
	JComboBox<String> box;
	JButton bt;
	
	public ABOWin() {
		box = new JComboBox<>();
		bt = new JButton("판단 요청");
		
		// style
		box.setPreferredSize(new Dimension(185, 30));
		
		// 데이터 채우기
		box.addItem("A");
		box.addItem("B");
		box.addItem("O");
		box.addItem("AB");
		
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
	 * 입력한 혈액형에 대한 판단을 하는 메서드
	 */
	public void getAdvice() {
		String abo = (String)box.getSelectedItem();
		String msg = null;
		
		if (abo.equals("A")){
			msg = "신중함";
		} else if(abo.equals("B")){
			msg = "개성강함";
		} else if(abo.equals("O")){
			msg = "외향적";
		} else if(abo.equals("AB")){
			msg = "이성적";
		}
		
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new ABOWin();
	}
}
