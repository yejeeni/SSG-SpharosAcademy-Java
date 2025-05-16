/* 
모든 gui 프로그램은 컴포넌트들을 조합하여 화면을 구성하므로, 각각의 레이아웃을 지원 
awt의 배치방법은 다음과 같음
1) BorderLayout
	동, 서, 남, 북, 중앙의 방위를 갖는 배치 방법
	Frame은 아무것도 지정하지 않으면 디폴트로 BorderLayout이 적용됨
2) FlowLayout
3) GridLayout
*/
package test;

import java.awt.*;

class BorderLayoutTest{
	public static void main(String[] args) {
		/*
		UI 컴포넌트는 다른 컴포넌트를 포함할 수 있는 능력을 기준으로 2가지 유형으로 나뉨
		1) 컨테이너 유형: 다른 컴포넌트 포함 가능 (ex: html div, p, ...)
							    대표적으로 윈도우인 Frame
								배치능력이 있으므로, 여러 유형의 배치 관리자를 적용할 수 있음
		2) 비주얼 컴포넌트: 컨테이너 안에 소속되는 대상 (ex: button, checknox, choice, ...)
		*/
		
		Frame frame = new Frame(); // 윈도우 생성
		BorderLayout border = new BorderLayout();
		
		// 프레임에 보더 배치 적용
		frame.setLayout(border);
				
		Button bt_east = new Button("동");
		Button bt_west = new Button("서");
		Button bt_south = new Button("남");
		Button bt_north = new Button("북");
		Button bt_center = new Button("중앙");
		
		frame.add(bt_east, BorderLayout.EAST);
		frame.add(bt_north, BorderLayout.NORTH);
		frame.add(bt_west, BorderLayout.WEST);
		frame.add(bt_south, BorderLayout.SOUTH);
		frame.add(bt_north, BorderLayout.NORTH); // 북쪽에 버튼 부착
		
		frame.add(bt_center); // 방위를 설정하지 않으면 default가 center
		
		frame.setSize(500, 450);
		frame.setVisible(true);
	}
}
