package com.ssg.dbproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener을 프레임에 붙이는 것이 아니라 별도의 클래스에서 구현
 */
public class MyActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("click");
	}
	
}
