/*
버튼 뿐만 아니라, 눈에 보이는 모든 컴포넌트는 실행 시 스스로를 그림
따라서 원하는 그림으로 커스텀하려면 그 그림을 뺏어서 그리면 된다. => 버튼을 상속받아 오버라이딩하기
java에서 대상 클래스가 final로 선언되어있지 않으면 상속받을 수 있다.
*/

package gui.graphic;

import javax.swing.JButton;
import java.awt.Graphics;

public class MyButton extends JButton{
	// 생성자는 자식에게 물려주지 않으므로, 버튼의 제목을 출력하는 JButton 고유의 생성자를 호출하지 않으면 제목이 나올 수 없음
	// 그러므로 매개변수가 있는 super()을 호출한다.
	public MyButton(String str){
		// int x = 3; // 이 코드라인은 실행 불가. 부모의 초기화보다 앞서는 코드는 존재할 수 없음
		super(str);		
	}
	
	// 버튼 뿐 아니라 컴포넌트들이 보유하고 있는 paint() 메서드를 오버라이딩
	// 컴포넌트 중에서 본래의 그래픽을 사용해야 할 경우가 있고, 그림을 뺏어야 할 상황이 있음 
	public void paint(Graphics g){ // Graphics: 그림 그리는 도구 (도형, 이미지, 글씨)
			//System.out.println("버튼의 그림 탈취");
			g.drawOval(0, 0, 25, 25);
	}
}
