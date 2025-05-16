package test;

import java.awt.*;

// 흐르는 배치 방법
public class FlowTest{
	public static void main(String[] args) {
		Frame f = new Frame("플로우 배치방식");
		
		// 아무것도 명시하지 않으면, 컨테이너는 default 배치 관리자가 적용됨. Frame의 default는 BorderLayout
		// BorderLayout 안에 배치된 컴포넌트는 Framd의 경계선까지 확장
		// FlowLayout은 컴포넌트 본연의 크기를 유지해줌
		f.setLayout(new FlowLayout());
		
		for (int i=0; i<20; i++){
			f.add(new Button(i+"번 버튼"));
		}
		
		f.setSize(200, 250);
		f.setVisible(true);
	}
}
