package gui.graphic;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

/*
컴포넌트 중 주로 컨테이너형은 아무것도 그려지지 않은 투명 도화지같으므로, 커스터마이징하기에 좋음 (JPanel, Canvas)

*/

public class ImgPanel extends JPanel{
	// 이미지를 얻기 위해서, 시스템 상의 이미지를 대신 구해주는 객체를 통해 추상 클래스인 Imange 객체의 인스턴스를 얻어오기
	Toolkit kit = Toolkit.getDefaultToolkit();
	Image image; // 추상클래스이므로 툴킷으로부터 얻어와야 함
	
	public ImgPanel(){
		setBackground(Color.YELLOW);
		
		// 그림을 그리기 전, 이미지를 얻어오기
		image = kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/image1.png");
		
		// 270*350
		setPreferredSize(new Dimension(270, 350));
	}
	
	// 패널이 보유한 그리기 메서드를 재정의
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, this);
		
		// 페인트 통 교체 (팔레트 색상 선택)
		g.setColor(Color.RED);
		// 선 그리기
		g.drawLine(100, 0, 50, 200);
		// 타원 그리기
		g.drawOval(0, 0, 200, 200);
		// 글씨 그리기
		g.setColor(Color.BLUE);
		g.setFont(new Font("Verdana", Font.BOLD, 40));
		g.drawString("Graphic Text", 50, 100);
		
		g.drawRect(150, 250, 100, 100);
	}
}
