package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {
	String path="C:/public/data.txt";
	
	//바이트 기반의 스트림으로 읽기, 1byte 바이트씩 처리하는 스트림, 영문 안깨짐, 한글깨짐.. 
	public void readByte() {
		//바이트 기반 스트림을 이용하여, 문서 파일을 읽고 그 내용을 콘솔에!!!
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(path);
			while(true) {
				int data=-1;
				data=fis.read(); //1byte 읽기
				if(data==-1)break;
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//문자 기반의 스트림으로 읽기 
	public void readReader() {
		FileInputStream fis=null; //바이트 기반 
		InputStreamReader reader=null;//문자 기반 ( 바이트 기반 위에 생성해야 함...따랏 덧씌워야함)
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			
			//주의) 문자기반 스트림은 데이터를 읽어들일때 1문자씩 읽어들임 
			//오해말기)  한글이 깨지지 않는다고 하여 2byte씩 읽어들이는거 아님. 2byte를 묶어서
			//				문자로 해석할 수있는 능력이 있는거 뿐임..
			int data=-1;
			data=reader.read();System.out.println((char)data);
			data=reader.read();System.out.println((char)data);
			data=reader.read();System.out.println((char)data);
			data=reader.read();System.out.println((char)data);
			data=reader.read();System.out.println((char)data);
			data=reader.read();System.out.println((char)data);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//버퍼 기반의 스트림으로 읽기
	public void readBuffer() {
		FileInputStream fis=null; //영문이 깨지지 않는 스트림 
		InputStreamReader reader=null; //한글까지 깨지지 않는 스트림 
		BufferedReader buffr=null; //한글까지 깨지지 않으면서, 한자씩 읽는 것이 아니라
												//줄바꿈문자를 만날때까지 읽지않고, 버퍼에 문자를
												//모음 (문자열)
		
		try {
			fis = new FileInputStream(path); //바이트 기반 스트림 
			reader = new InputStreamReader(fis); //문자 기반 스트림
			buffr = new BufferedReader(reader); //버퍼 기반 스트림 
			
			while(true) {
				String data=null;
				data=buffr.readLine();
				if(data==null)break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffr !=null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		StreamStudy ss = new StreamStudy();
		//ss.readByte();
		//ss.readReader();
		ss.readBuffer();

	}

}
