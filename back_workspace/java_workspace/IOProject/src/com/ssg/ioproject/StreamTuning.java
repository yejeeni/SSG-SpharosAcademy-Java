package com.ssg.ioproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//대량의 데이터를 읽어보자
public class StreamTuning {
	String name = ("C:/lecture_workspace/Back_workspace/java_workspace/IOProject/res/memo.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public StreamTuning() {
		try {
			
			int count =0;
			String data =null;
			while(true) {
				data = br.readLine();
				count++;
				if(data == null)break;
				System.out.println(data + count);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new StreamTuning();	
	}
}
