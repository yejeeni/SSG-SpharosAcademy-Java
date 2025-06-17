package com.ssg.networkapp.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	//서버란, 통신의 양 당사자 중, 접속을 기다리는 쪽이다. 
	//자바se 소켓서버를 구축하려면 ServerSocket 이라는 클래스를 이용한다 
	ServerSocket server;
	public EchoServer() {
		//0~1023 시스템 점유 포트
		try {
			server = new ServerSocket(9999);
			System.out.println("서버 객체 생성");
			Socket socket=server.accept();//사용자의 접속을 청취하는 메서드, 접속이 발생할때까지
			//네트워크 접속자가 발생하면, 그 접속자에 대응되는 소켓 객체를 반환받는다.. 
			//이 소켓이 있어야 통신 모든 구현이 가능..
			InetAddress addr=socket.getInetAddress();
			String ip=addr.getHostAddress();//접속자의 ip
			
			//무한정 대기상태에 빠짐 
			System.out.println("접속자 발견!!!"+ip);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	public static void main(String[] args) {
		new EchoServer();
	}
	
}
