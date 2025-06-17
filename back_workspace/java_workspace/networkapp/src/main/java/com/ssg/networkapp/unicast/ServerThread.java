package com.ssg.networkapp.unicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//접속자마다 1:1 대응하여 인스턴스가 생성될 대화용 쓰레드 
//대화가 가능하려면, 입력,출력스트림이 필요함 
public class ServerThread extends Thread{
	GUIServer guiServer;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	//소켓을 서버로부터 전달받으면 된다...접속자가 들어올때마다
	public ServerThread(GUIServer guiServer, Socket socket) {
		this.guiServer=guiServer;
		this.socket=socket;
		
		//접속과 동시에 스트림을 얻어놓아야 대화가 가능  
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//듣기 
	public void listen() {
		String msg=null;
		
		try {
			msg=buffr.readLine();
			guiServer.area.append(msg+"\n");
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//말하기
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush(); //출력스트림 중 버퍼처리된 것만..
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true) {
			listen();
		}
	}
}







