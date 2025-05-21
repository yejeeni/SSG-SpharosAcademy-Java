package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Gallery extends JFrame implements ActionListener{
	JLabel la_title;
	JButton bt_prev;
	JButton bt_next;
	JPanel p_north;
	Toolkit kit; // 시스템의 이미지를 얻어오는 객체
	MyCanvas myCanvas;
	Image[] imgArray = new Image[9]; // 이미지 객체를 모아놓을 배열
	int index = 0; // 이미지 배열 접근을 위한 인덱스
	
	public Gallery(){
		la_title = new JLabel("제목");
		bt_prev = new JButton("prev");
		bt_next = new JButton("next");
		p_north = new JPanel();
		myCanvas = new MyCanvas();
		
		kit = Toolkit.getDefaultToolkit();
		// image =  kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/img2.png");
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		myCanvas.setBackground(Color.YELLOW);
		
		add(p_north, BorderLayout.NORTH);
		add(myCanvas);
		
		// 초기 이미지 보여주기
		createImage();
		
		// 패널에 초기 이미지 1개를 전달
		myCanvas.setImage(imgArray[index]);
		
		// 이전 버튼과 리스너 연결
		bt_prev.addActionListener(this);
		
		// 다음 버튼과 리스너 연결
		bt_next.addActionListener(this);
		
		setSize(600, 500);
		setVisible(true);
	}

	// 멤버 변수로 선언된 이미지 배열에 이미지 인스턴스 9개를 준비해놓아야 프로그램 가동 즉시 사용할 수 있음
	public void createImage(){
		String[] path = {
			"animal1.jpg",
			"animal2.jpg",
			"animal3.jpg",
			"animal4.jpg",
			"animal5.jpg",
			"animal6.jpg",
			"animal7.jpg",
			"animal8.jpg",
			"animal9.jpg"
		};
		
		for (int i=0; i<path.length; i++){
			// 툴킷으로부터 이미지 인스턴스 9개를 생성하여 image 배열에 넣어주기
			imgArray[i] = kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/"+path[i]);
		}
	}

	// p_center 영역에 이미지 출력
	/*
	컴포넌트에 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다
	
	[AWT]
	최초 컴포넌트 등장 시 paint()에 의해 눈에 보여짐
	사용자가 컴포넌트의 그림에 변화를 주면, JVM은 기존 그림을 지워야 하므로 update() 메서드를 호출하여 그림을 깨끗하게 지움
	그 다음 내부적으로 paint() 메서드를 호출하여 변경된 그림을 화면에 보여줌
	
	[Swing]
	사용자가 컴포넌트에 변화를 주게 되면 update() 메서드가 호출되는 것이 아니라, paintComponent()를 호출하여 화면을 깨끗하게 지움
	
	*/
	
	public void showImage(){
		myCanvas.setImage(imgArray[index]);
		myCanvas.repaint(); // 패널을 다시 그리기
		
		// 변경된 이미지를 보기 위해서는 사용자의 윈도우 조작이 아니라, 프로그래밍적으로 지우고 다시 그릴 것을 요청해야 함
		// 이때 호출되는 메서드가 repaint(), 즉 다시 그려줄 것을 요청하는 메서드
		// 이때 paint()를 직접 호출해서는 안 됨. 그림은 시스템의 영역
		// repaint() -> update(AWT)		------------> paint()
		//					  paintComponent(Swing) ----> paint()
	}
	
	public void actionPerformed(ActionEvent e){
		// 이벤트를 일으킨 컴포넌트를 이벤트 소스라고 함
		Object obj = e.getSource();
		
		if (obj == bt_prev){
			index--;
		} else if (obj == bt_next){
			index++;
		}
		showImage();
	}
	
	// 공부 목적 상, 프레임의 그림을 뺏어서 그려보기
	public void paintComponent(Graphics g){
			System.out.println("paint()");
	}
	
	public static void main(String[] args) {
		new Gallery();		
	}
}
