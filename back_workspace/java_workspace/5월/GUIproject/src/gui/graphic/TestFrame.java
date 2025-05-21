/*
								그래픽 프로그래밍 비유
				[현실]									[프로그래밍]
				 화가					<--->	컴포넌트
		붓 (그림을 그리는 행위)	<--->	컴포넌트가 보유한 그리는 메서드 (paint())
			팔레트, 그림 도구		<--->	paint(Graphics g)
		  그려질 대상(캔버스)	    <--->	컴포넌트 자신	
			
			
모든 컴포넌트는 스스로 그림을 그림. 버튼이 스스로 그림을 그릴 때 빼앗아보기
*/

package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

class TestFrame extends JFrame{
	MyButton bt; // = JButton
	ImgPanel ip; // 재정의한 패널
	
	public TestFrame(){
		bt = new MyButton("커스텀 버튼");
		ip = new ImgPanel();

		setLayout(new FlowLayout());
		add(bt);
		add(ip);
		setSize(300, 400);
		setVisible(true);
		
		// 윈도우창을 닫을 때, 프로세스 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public static void main(String[] args) {
		new TestFrame();
	}
}
