package com.ssg.ioproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 스트림의 분류 
 * 1) 방향
 * 	   - 입력
 * 	   - 출력
 * 2)데이터  처리방법에 따른 처리 방법 
 * 		-바이트(근본) : 1byte 씩 처리
 * 		-문자 : 1문자씩(문자 이해 스트림)
 * 		- 버퍼처리
 * 		
 */
public class CharacterStream {
	//바이트 기반 스트림의 특징: 입력 Input Stream 출력 OutputStream
	//문자 기반 스트림: ~~Reader , ~~Writer
	FileInputStream fis;
	FileOutputStream fos;
	
	InputStreamReader is; //기존에 존재하는 바이트 기반의 입력 스트림에 덧붙여 사용
	OutputStreamWriter os; //기존에 존재하는 바이트 기반의출력 스트림에 덧붙여 사용한다. 
	
	FileReader reader; //파일을 대상으로 한 문자 기반 입력 스트림(문자를 이해한다)
							  //한 바이트씩 읽어들이는 것이 아니라, 한 문자씩 읽는다.(문자 안깨짐)
	FileWriter writer; 
	
	String name = "C:/lecture_workspace/Back_workspace/java_workspace/IOProject/res/memo.txt";
	String O_name = "C:/lecture_workspace/Back_workspace/java_workspace/IOProject/res/memo_copy2.txt";
	public CharacterStream() {
		try {
			fis = new FileInputStream(name);
			fos = new FileOutputStream(O_name);
			
			is = new InputStreamReader(fis);//빨대가 2단계로 업그레이드 되는 것.!!!!!!!!!!!!!!!!!!!!
			os = new OutputStreamWriter(fos);
			int data =-1;
			
			while(true) {
				data =is.read(); //한 문자!
				if(data == -1) {
					break;
				}
				//읽어마신 데이터를 다시 내뱉어야한다 빈 파일을 대상으로
				os.write(data);				
				System.out.print(data);
			}
		}
		catch (IOException e) {
				e.printStackTrace();
		}  
		finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(os != null) {
				try {
					os.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
	
	public static void main(String[] args) {
		new CharacterStream();
		
	}
}
