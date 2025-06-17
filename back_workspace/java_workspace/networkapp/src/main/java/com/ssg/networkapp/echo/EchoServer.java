package com.ssg.networkapp.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//접속한 클라이언트의 메시지를 그대로 보내주는 에코서버를 구축해본다
public class EchoServer {
	ServerSocket server; //소켓이라는 단어가 포함되어 있지만, 대화용 소켓이 아니라, 어떤 유저가
									//접속하는지를 감지하는 접속 감지용 서버이다...
	
	public EchoServer() {
		try {
			server=new ServerSocket(9999);
			Socket socket=server.accept(); //접속자가 발생할때까지 무한대기...
			String ip=socket.getInetAddress().getHostAddress();
			
			System.out.println(ip+" 접속 발견");
			
			//소켓을 통해 데이터를 주고 받을 수있는 스트림을 얻자!!
			//방향에 따라 -  입력, 출력 
			//데이터 처리 방식 - 바이트 ~~Stream , 문자  ~~ Reader, Writer , 버퍼
			InputStream is=socket.getInputStream();//바이트 기반의 입력스트림을 얻어옴..
			OutputStream out=socket.getOutputStream();//바이트 기반의 출력스트림을 얻어옴 
			
			//무한루프는 엄청난 속도이므로, 프로그램에서 사용 시 주의해야 하지만, 
			//단, 스트림 처리에서는 read() 메서드 자체 가 상대방의 메시지를 받을때까지 대기상태에 빠지기
			//때문에 부하를 일으키지 않음..
			while(true) {
				int data=is.read();// 1byte 읽어들임...  yahoo 중 y만 읽을 수 있음.. 듣기!!
				System.out.println((char)data);
				out.write(data); //읽어들인 1바이트 데이터를 그대로 보내버림(출력) - 말하기!!
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
