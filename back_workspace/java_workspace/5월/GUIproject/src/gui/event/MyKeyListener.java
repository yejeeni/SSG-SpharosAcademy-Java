package gui.event;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/* os를 거쳐 jvm으로부터 전달되는 키보드 이벤트를 청취하기 위한 객체인 KeyListener을 재정의*/
public class MyKeyListener implements KeyListener{
	// 키보드를 눌렀을 때
	public void	keyPressed(KeyEvent e){
		System.out.println("keyPressed");
	}

	// 키보드를 눌렀다 뗄 때
	public void	keyReleased(KeyEvent e){
		System.out.println("keyReleased");
	}
	
	public void	keyTyped(KeyEvent e){
	}
}