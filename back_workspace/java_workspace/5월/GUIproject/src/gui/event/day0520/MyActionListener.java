package gui.event.day0520;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MyActionListener implements ActionListener {
	Button bt1;
	Button bt2;
	
	// 생성자 메서드를 통해 다른 클래스에 존재하던 버튼들을 전달받음: 생성자 주입(injection)
	public MyActionListener(Button bt1, Button bt2){
			this.bt1 = bt1;
			this.bt2 = bt2;
	}
	
	// 사용자가 액션 이벤트를 일으키면, os로부터 이벤트 정보를 받은 JVM은 해당 이벤트 정보를 알맞는 이벤트 객체로 인스턴스화시킴
	// 그리고 생성된 이벤트 인스턴스는 재정의 메서드인 actionPerformed()로 전달함. 알고싶은 정보는 ActionEvent로부터 모두 알아낼 수 있음
	public void actionPerformed(ActionEvent e){
		// 이벤트를 일으킨 주체를 가리켜 이벤트 소스 event source
		// System.out.println(e.getSource());
		
		Object obj = e.getSource();
		if (obj == bt1){
			System.out.println("A 클릭");
		} else if(obj == bt2){
			System.out.println("B 클릭");
		}
	}
}
