/*
GridLayout: 행과 열을 지원하는 배치 방법
*/
package test;

import java.awt.*;

public class GridTest{
	public static void main(String[] args) {
		Frame f = new Frame("그리드 배치");
		f.setLayout(new GridLayout(3, 4));
		
		for (int i=0; i<3; i++){
			for (int j=0; j<4; j++){
				Button bt = new Button("hello");
				bt.setBackground(Color.YELLOW);
				f.add(bt);
			}
		}
	
		f.setSize(300, 400);
		f.setVisible(true);
	}
}
