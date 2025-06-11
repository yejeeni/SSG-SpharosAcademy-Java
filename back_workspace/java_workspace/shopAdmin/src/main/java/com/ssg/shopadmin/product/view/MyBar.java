package com.ssg.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

/**
 * API 자체적으로 지원하는 진행바 대신 원하는 스타일로 구현한 진행바
 */
public class MyBar extends JProgressBar implements Runnable{ // Runnable 인터페이스란? run() 추상 메서드르 보유한 인터페이스
	
	File originFile;
	File destFile;
	FileInputStream fileInputStream; // 파일을 대상으로 한 바이트 기반의 입력 스트림
	FileOutputStream fileOutputStream; // 파일을 대상으로 한 바이트 기반의 출력 스트림
	int n; // 파일을 읽어들인 횟수
	
	public MyBar(File originFile, File destFile) {
		this.originFile = originFile;
		this.destFile = destFile;
		
		System.out.println("새 디렉토리" + destFile.getParent());
		System.out.println("새 파일명" + destFile.getName());
		
		try {
			fileInputStream = new FileInputStream(originFile); // 원본 파일을 대상으로 스트림 생성
			fileOutputStream = new FileOutputStream(destFile); // 파일이 복사될 대상에 대한 출려 스트림 생성
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(680, 50));
		setBorder(new TitledBorder(""));
		
		// 진행바 중앙에 텍스트 띄우기
		setStringPainted(true);
		
		setFont(new Font("Verdana", Font.BOLD, 17));
		setForeground(Color.BLUE);
		this.setValue(30);
	}

	/**
	 * 진행바에 진행률을 표시하는 메서드
	 * @param n 
	 */
	public void showRate(int n) {
		// (읽은양/용량) * 100
		this.n += n;
		this.setValue((int)(this.n*100 / originFile.length()));
	}
	
	/*
	 * 1바이트 단위로 파일을 복제하는 메서드
	 */
	public void copySlow() {
		while(true) {
			int data = -1;
			try {
				data = fileInputStream.read();
				if (data == -1) {
					break;
				} else {
					// 진행바에 진행률을 표시 ((읽은양/용량) * 100)
					n++; // 파일을 읽었으므로 읽은 양을 누적하는 n을 증가
					showRate(n);
					fileOutputStream.write(data);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 			
	}
	
	/**
	 * 버퍼를 만들어 한 번에 파일을 읽어들이는 메서드
	 */
	public void copyFast() {
		int n = 0;
		byte[] buff = new byte[1024];
		int read = 0;
		
		while(true) {
		try {
			read = fileInputStream.read(buff);
			if (read == -1) {
				break;
			}
			fileOutputStream.write(buff, 0, read); // buff의 0번째부터 read(읽은 양)만큼 출력
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			showRate(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}
	
	/**
	 * 파일에 생성된 스트림을 통해 읽고 출력하기
	 */
	@Override
	public void run() {
		copyFast();
		System.out.println("파일 복사 완료");
		release();
	}
	

	/**
	 * 파일 입출력 스트림을 닫는 메서드
	 */
	public void release() {
		if (fileOutputStream != null) {
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fileInputStream != null) {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
