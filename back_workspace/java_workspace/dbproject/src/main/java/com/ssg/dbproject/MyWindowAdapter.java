package com.ssg.dbproject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * KeyListener, WindowListener 등 재정의할 메서드가 3개 이상되는 인터페이스의 경우, 이미 java api 차원에서 리스너 인터페이스를 구현해놓은 중간 객체들을 가리켜 Adapter라고 한다.
 * 메서드가 재정의되어있으므로 모든 메서드를 구현할 의무가 없음
 */
public class MyWindowAdapter extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing");
	}
}
