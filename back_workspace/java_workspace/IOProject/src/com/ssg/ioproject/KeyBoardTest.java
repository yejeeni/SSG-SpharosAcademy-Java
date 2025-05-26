package com.ssg.ioproject;

import java.io.IOException;
import java.io.InputStreamReader;

public class KeyBoardTest {

	public static void main(String[] args) {
		//모든 프로그래밍 언어에서는 os가 이미 제공하는 표준 스트림에 대해서는 
		//개발자가 직접 생성 및 관리하지 않아도 된다. => os 부팅 때 부터 준비가 되어있으므로
		//자바에서의 우리가 지금까지 써왔던 System.out.은 사실.. 표준출력스트림이므로 그냥 가져다가 사용하면 된다.
		//System.in을 이용하면 키보드로부터 들어온 데이터를 입력 받을 수 있다.
		//주의: 사용 후, 닫지 말라.. not close!
		InputStreamReader is = new InputStreamReader(System.in);
		int count = 0;
		try {
			int data = -1;
		
			while(true) {
			count++;
			data=is.read();
			System.out.print((char)data);
			}
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
