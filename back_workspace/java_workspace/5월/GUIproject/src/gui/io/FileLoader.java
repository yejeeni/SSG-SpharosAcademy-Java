/* 실행중인 java 프로그램에서 디스크의 파일에 접근하여 데이터를 읽고 출력 */

package gui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class FileLoader{
	public static void main(String[] args) {
		// 실행 중인 프로그램이 파일 등의 자원의 데이터를 읽어들이려면 스트림 객체가 필요함
		/*
		Stream이란? 현실에서의 물줄기, 물의 흐름을 의미함. 전산에서는 대상이 물이 아닌 데이터임.
		IO(입출력)
		흐름의 방향은 2가지가 존재하는데, 1) 실행중인 프로그램으로 들어오는 흐름: input(입력)
													2) 실행중인 프로그램에서 나가는 흐름: output(출력)
		*/
		
		// 파일을 대상으로 한 입력 객체. 파일을 읽어들일 수 있음
		// 아래 코드는 문법상 문제는 없지만, FileNotFoundException 예외처리를 하지 않아 컴파일러가 컴파일을 거부
		// FileInputStream fis = new FileInputStream("C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/memo.txt");
		
		FileInputStream fis = null;
		
		try{	
			fis = new FileInputStream("C:/lecture_workspace/back_workspace/java_workspace/5월/GUIproject/res/memo.txt");
			int data;
			
			while(true){
				data = fis.read();
				if (data == -1){
					break;
				}
				System.out.print((char)data);					
			}		
		} catch(FileNotFoundException e){ // 파일이 없다면 catch() 수행. 소괄호 안에 에러의 원인이 되는 객체의 인스턴스를 생성하여 전달해줌.
			System.out.println("FileNotFoundException. 파일을 찾을 수 없음.");
		} catch(IOException e){
			System.out.println("IOException. 입출력 중 문제 발생.");
		} finally{
			// 실행부가 try문을 수행하든 catch문을 수행하든 반드시 지나가는 영역
			// 성공한 경우 try->finally. 예외가 발생한 경우 catch->finally
			// db, stream은 사용 후 반드시 닫아야 함
			if (fis != null){ // FileInputStream이 null이 아닐 때만 수행해야 함. null일 경우 예외 발생
				try{
					fis.close();	
				} catch(IOException e){
					// 로그 출력
					e.printStackTrace();
				}	
			}
		}
	}
}
