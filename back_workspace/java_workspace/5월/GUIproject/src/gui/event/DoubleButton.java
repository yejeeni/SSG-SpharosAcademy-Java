package gui.event;

import java.awt.*;
import gui.event.day0520.MyActionListener;

public class DoubleButton {
	public static void main(String[] args) {
		Frame frame = null;
		Button bt1 = null;
		Button bt2 = null;
		
		frame = new Frame();
		bt1 = new Button("A");
		bt2 = new Button("B");
		
		frame.setLayout(new FlowLayout());
		
		frame.add(bt1);
		frame.add(bt2);
		
		MyActionListener myActionListener = new MyActionListener(bt1, bt2);
		
		// 버튼1과 리스너 연결
		bt1.addActionListener(myActionListener);
		// 버튼2와 리스너 연결
		bt2.addActionListener(myActionListener);
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}