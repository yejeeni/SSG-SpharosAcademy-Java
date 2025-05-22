package gui.graphic;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class MovePanel extends JPanel{
	private int x;
	private int y;

	// JPanel의 paint() 메서드 오버라이딩
	public void paint(Graphics g){
			// 색이 채워진 원 그리기
			g.setColor(Color.RED);
			g.fillOval(x, y, 45, 45);			
	}
	
	public int getX(){
		return x;	
	}

	public int getY(){
		return y;	
	}
	
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}
	
	public void move(){
		x++;
		y++;
	}
}