/*
메모장같은 텍스트 파일이 아니라, 이미지와 동영상 같은 바이너리 파일을 읽어오기
*/

package gui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

class BinaryLoader {
	// 실행 중인 프로그램으로 데이터를 읽어들여야 하므로, 입력 스트림 객체가 필요
	FileInputStream fis; // 파일을 대상으로 한 입력 스트림
	FileOutputStream fos; // 파일을 대상으로 한 출력 스트림
	
	String name = "C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/animal1.jpg";
	String target = "C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/animal_copy.jpg";
	
	public BinaryLoader(){
		// 컴파일 시 예외 처리를 강요하는 예외방식을 가리켜, 강제된 예외 CheckedException라고 함
		try{
			fis = new FileInputStream(name);		
			fos = new FileOutputStream(target); // 저장할 경로와 파일
			
			int data;
			
			while(true){
				data = fis.read(); // 1byte 읽음. 호출 시마다 다음 데이터로 넘어감
				if (data == -1){
					break;
				}
				System.out.print(data);
				
				// 읽어들인 바이트 데이터를 바로 출력
				fos.write(data); // 1byte를 출력함
			}
		}catch(FileNotFoundException e){
			System.out.println("파일이 존재하지 않음");	
		}catch(IOException e){
			System.out.println("입출력 오류");	
		}finally{
			if (fis != null){
				try{
					fis.close();	
				} catch(IOException e){
					e.printStackTrace();
				}
			}	
			if (fos != null){
				try{
					fos.close();	
				} catch(IOException e){
					e.printStackTrace();
				}
			}	
		}
	}
	public static void main(String[] args) {
		new BinaryLoader();	
	}
}
