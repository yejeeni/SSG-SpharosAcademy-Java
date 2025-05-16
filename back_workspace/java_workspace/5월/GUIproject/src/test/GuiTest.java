/*
java의 그래픽 관련 API는 java.awt 패키지에 존재
모든 데스크탑 기반 GUI 프로그램에서 최상단에 존재하는 그래픽 컴포넌트는 윈도우
자바에서는 윈도우는 상징적인 존재에 불과하고, 그 하위의 Frame으로 대신함
*/
package test;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;

class  GuiTest{
	public static void main(String[] args){
		// html에서 요소들을 부모 컨테이너에 추가하듯이 자바에서도 gui적 요소들을 윈도우라는 컨테이너에 부착해야 눈에 보임
		Frame f = new Frame(); // 윈도우 생성
		f.setLayout(new FlowLayout()); // 버튼이 크게 나오는 이유는 아직 레이아웃 방법을 지정하지 않았기 때문. default 레이아웃이 적용되어 있음. 
		
		Button bt = new Button("hello"); // 버튼 생성
		f.add(bt); // 프레임에 버튼 부착
		
		TextField t = new TextField(20); // 텍스트필드
		f.add(t);
		
		Choice ch1 = new Choice();
		ch1.add("@gmail.com");
		ch1.add("@naver.com");
		f.add(ch1);
		
		TextArea area = new TextArea(12, 20);
		f.add(area);
		
		Checkbox[] cbArray = new Checkbox[3];
		cbArray[0] = new Checkbox("Java", true);
		cbArray[1] = new Checkbox("JSP", true);
		cbArray[2] = new Checkbox("Oracle", true);
		for (int i=0; i<cbArray.length; i++){
			f.add(cbArray[i]);
		}
		
		Label la = new Label("label");
		f.add(la);
		
		// 메뉴 바와 메뉴 만들기
		Menu m_file, m_edit, m_style, m_view, m_help;
		m_file = new Menu("파일");
		m_edit = new Menu("편집");
		m_style = new Menu("서식");
		m_view = new Menu("보기");
		m_help = new Menu("도움말");
		
		MenuBar bar = new MenuBar(); // 메뉴들이 얹혀질 막대기 컨테이너
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		f.setMenuBar(bar);
		
		
		// 윈도우를 사용하려면 너비, 높이를 지정해야 함. 또한 윈도우의 디폴트 보기 옵션은 비활성화되어 있음. 따라서 활성화시켜야 함
		f.setSize(600, 500);
		f.setVisible(true);
	}
}
