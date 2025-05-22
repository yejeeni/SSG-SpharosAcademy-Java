package com.ssg.ioproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Editor extends JFrame implements ActionListener{
	JMenuBar bar; // 눈에 보이지는 않지만, 메뉴들을 얹혀놓을 막대기. 위치는 윈도우 창 상단으로 고정되어 있음.
	JMenu[] menu = new JMenu[5]; // JMenu를 넣을 수 있는 빈 공간
	String[] menuTitle = {"파일", "편집", "서식", "보기", "도움말"};
	JMenuItem[] item = new JMenuItem[8];
	String[] itemTitle = {"새로 만들기", "새 창", "열기", "저장", "다른 이름으로 저장", "페이지 설정", "인쇄", "끝내기"};
	JTextArea area;
	
	// 메뉴의 이름에 직관성을 부여하기 위한 상수를 정의
	public static final int FILE = 0;
	public static final int EDIT = 1;
	public static final int STYLE = 2;
	public static final int VIEW = 3;
	public static final int HELP = 4;
	
	JFileChooser fileChooser; // 파일 탐색기를 컨트롤하는 객체
	
	public Editor() {
		bar = new JMenuBar();
		
		for(int i=0; i<menu.length; i++) {
			menu[i] = new JMenu(menuTitle[i]);
		}
		
		// 메뉴 아이템 만들기
		for (int i=0; i<item.length; i++) {
			item[i] = new JMenuItem(itemTitle[i]);
		}
		
		// bar.setBackground(Color.BLACK);
		area = new JTextArea();
		fileChooser = new JFileChooser("C:\\lecture_workspace\\back_workspace\\java_workspace");
		
		// 메뉴 아이템을 FILE메뉴에 부착
		for(int i=0; i<item.length; i++) {
			menu[FILE].add(item[i]);	
			if (i==4 || i==6) {
				menu[FILE].addSeparator();				
			}
		}
		
		// 메뉴바에 메뉴 부착
		for(int i=0; i<menu.length; i++) {
			bar.add(menu[i]);
		}
		
		
		// bar 부착
		this.setJMenuBar(bar);		
		add(area);
		
		// 열기 이벤트 연결
		item[2].addActionListener(this);
		// exit 이벤트 연결
		item[item.length-1].addActionListener(this);
				
		setBounds(500, 100, 800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void openFile() {
		// 어떤 파일을 대상으로 열 지 사용자가 결정해야 되므로, 새 창을 띄워야 함
		int result = fileChooser.showOpenDialog(this);
		
		File file = null;
		if (result == JFileChooser.APPROVE_OPTION) { // 열기를 눌렀을 때
			// 유저가 선택한 파일을 얻어와서 스트림을 생성
			file = fileChooser.getSelectedFile();
		}
		
		FileInputStream fis = null; // 파일을 대상으로 한 입력 스트림
		try {
			fis = new FileInputStream(file);
			// 파일의 끝까지 1byte씩 읽어가면서 area에 추가
			int data = -1;
			
			while(true) {
				data = fis.read(); // 1byte로 읽기
				if (data == -1) {
					break;
				}
				
				area.append(Character.toString((char)data)); // 한 글자를 스트링으로
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		if (e.getSource() == item[item.length-1]) { // EXIT를 누른 경우
			if (JOptionPane.showConfirmDialog(this, "종료하시겠습니까?") == JOptionPane.OK_OPTION) {
				System.exit(0);				
			}
		} else if(e.getSource() == item[2]) {
			openFile();
		}
		System.out.println("호출");
	} 
	
	public static void main(String[] args) {
		new Editor();
	}
}
