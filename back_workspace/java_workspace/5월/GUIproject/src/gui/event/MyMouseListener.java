package gui.event;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MyMouseListener implements MouseListener{
	public void	mouseClicked(MouseEvent e){
		System.out.println("mouseClicked");
	}
	public void	mouseEntered(MouseEvent e){
		System.out.println("mouseEntered");
	}
	public void	mouseExited(MouseEvent e){
		System.out.println("mouseExited");
	}
	public void	mousePressed(MouseEvent e){
		System.out.println("mousePressed");
	}
	public void	mouseReleased(MouseEvent e){
		System.out.println("mouseReleased");
	}
}
