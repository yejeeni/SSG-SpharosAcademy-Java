package gui.event;

import java.awt.*;

/* java gui에서도 이벤트 처리가 가능. html에서 클릭이벤트는 click이라고 하지만, java에서는 X. acion에 소속됨*/

public class ActionTest{
	public static void main(String[] args) {
		Frame frame = null;
		Button bt = null;
		TextField t = null;
		Choice ch = null; // = selectBox
		
		frame = new Frame();
		bt = new Button("click me");
		t = new TextField(20);
		ch = new Choice();
		
		ch.addItem("Choose your mail server");
		ch.addItem("@naver.com");
		ch.addItem("@gmail.com");
		ch.addItem("@daum.net");
		
		// 버튼에 이벤트 처리 함수를 연결
		MyActionListener myActionListner = new MyActionListener();
		bt.addActionListener(myActionListner); // 이벤트를 구현한 객체의 인스턴스
				
		t.addKeyListener(new MyKeyListener()); // 텍스트필드와 리스너 연결
		
		MyItemListener myItemListener = new MyItemListener();
		ch.addItemListener(myItemListener);
		
		frame.addMouseListener(new MyMouseListener());
		
		frame.setLayout(new FlowLayout());
		
		frame.add(bt);
		frame.add(t);
		frame.add(ch);
		
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}