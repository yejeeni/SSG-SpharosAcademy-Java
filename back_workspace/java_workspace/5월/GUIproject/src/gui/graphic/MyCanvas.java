
/*
패널에 그림을 그리기 위해 paint 메서드 재정의
JPanel은 컨테이너형이므로 내부에 그림이 없어, 재정의하기 좋음
*/
package gui.graphic;

import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MyCanvas extends JPanel{
	Image image;
	
	// 이미지를 넘겨받을 메서드를 선언하거나, 프레임을 보유하면 프레임이 보유한 배열로 쓸 수 있음
	// 이미지 넘겨받을 메서드 선언
	public void setImage(Image image){
		this.image = image;	
	}
	
	public void paint(Graphics g){
			// 이미지 그리기
			// ImageObserver: 이미지 로드는 비동기이다. 이미지 로드가 완료되지 않았다면 옵저버에 의해 완전히 로드될 수 있도록 내부적인 처리가 지원되는데, 이때 옵저버 역할을 수행할 객체를 지정할 수 있음
			// JPanel은 ImageObjserver 인터페이스를 구현한 객체이므로, 옵저버 역할이 가능
			g.drawImage(image, 0, 0, 600, 450, this);
	}
}
