package gui.event;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
모든 gui 프로그래밍에서 ui 컴포넌트에 대한 이벤트가 발생했을 때 제일 문저 이벤트를 감지하는 주체는 OS
OS는 자신이 감지한 이벤트에 대해 정보 객체를 만들고, 이 정보를 해당 프로그램에 전달
해당 프로그램은 OS가 전달한 이벤트 정보에 대해 1:1 대응하는 객체를 인스턴스화시켜 메모리에 올리고, 이 인스턴스를 해당 프로그램으로 전달
*/

//  Action 이벤트만을 청취할 수 있는 청취자인 ActionListener을 현재 이 클래스에 완성할 것을 선언
// 인터페이스 구현을 선언하면 자식 클래스는 반드시 구현해야 함
public class MyActionListener implements ActionListener{
										 // [is a 관계] MyActionListener은 ActionListener다.
	// 부모인 ActionListener의 메서드 오버라이딩
	public void actionPerformed(ActionEvent e){
			// 액션 이벤트 처리 로직 작성
			System.out.println("click");
	}
}
