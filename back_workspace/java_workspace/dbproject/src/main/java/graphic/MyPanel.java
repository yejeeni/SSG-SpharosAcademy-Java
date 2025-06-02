package graphic;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	AniTest aniTest;
	
	public MyPanel(AniTest aniTest) {
		this.aniTest = aniTest;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(aniTest.x, aniTest.y, 200, 200);
	}
	
	
}
