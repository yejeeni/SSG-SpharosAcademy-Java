package gui.layout;

/*
GUI 프로그래밍을 윈도우 프로그래밍이라고도 함. 모든 UI 컴포넌트는 윈도우 안에서 구현되므로. (컴포넌트: 재사용 가능한 ui를 갖는 객체 단위 (ex, 버튼, 체크박스))

[java] 컴포넌트의 유형
- 남을 포함할 수 있는 유형: 컨테이너
	ex) Frame
	특징) 남을 포함하므로, 어떻게 배치할지를 고민해야 함. -> 컨테이너형에는 배치 관리자(Layout manager)가 지원됨
	- 배치관리자의 종류
		1) BorderLayout: 동, 서, 남, 북, 센터의 방향을 갖는 배치. 각 방향에 들어가는 컴포넌트는 자신의 크기를 잃어버리고 확장됨.
									컨테이너 유형은 배치관리자를 지정하지 않아도, 시스템에서 기본적으로 적용되는 배치 관리자가 있음
									ex, Frame: BorderLayout. 
		2) FlowLayout: 위치가 결정되지 않고 컨테이너 크기에 따라 흘러다님. 단, 방향성이 있어서 수평 방향의 흐름, 수직 방향의 흐름이 있음.
		3) GridLayout: 행과 열의 배치 방식. 각 행, 열에 들어가는, 즉 셀에 들어가는 컴포넌트가 자신의 크기를 잃어버리고 확장됨
		4) CardLayout: 카드처럼 오직 하나의 카드만 보여짐. 화면 전환 시 사용
- 남에게 포함되는 유형: 비주얼 컴포넌트형
	ex) 버튼, 체크박스, 초이스, ... 윈도우 안에 포함되는 모든 것들
	
*/

import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;

public class LayoutTest{
	public static void main(String[] args) {
		
		// 윈도우 생성
		Frame frame = new Frame("배치 학습");
		Panel panel = new Panel(); // 윈도우 안에 소속되는 컨테이너 컴포넌트. 다른 컴포넌트를 포함할 수 있다. Panel도 컨테이너형이므로 배치관리자가 지원되고, 지정하지 않을 시 디폴트는 FlowLayout
		Panel panel2 = new Panel();
				 
		// 버튼을 생성해서 부착. 방향을 지정하지 않으면 디폴트는 센터
		Button bt_center1 = new Button("center1");
		Button bt_center2 = new Button("center2");
		Button bt_west = new Button("west");
		Button bt_south = new Button("south");
		
		bt_center1.setBackground(Color.ORANGE);
		bt_center2.setBackground(Color.ORANGE);
		bt_west.setBackground(Color.ORANGE);

		panel.add(bt_south); // 패널에 버튼 부착
		panel2.add(bt_center1);
		panel2.add(bt_center2);
		 
		// frame.add(bt_center);		 
		// 상수는 public static final로 선언되었고, 클래스 소속이므로 인스턴스 생성없이 사용 가능. BorderLayout의 상수로 접근
		frame.add(bt_west, BorderLayout.WEST); // add의 두 번째 매개변수로 상수를 지정
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(panel2);
		 
		frame.setSize(500, 400);
		// 윈도우는 디폴트가 보이지 않음이므로, 보이게 해야함
		frame.setVisible(true);
	}
}
