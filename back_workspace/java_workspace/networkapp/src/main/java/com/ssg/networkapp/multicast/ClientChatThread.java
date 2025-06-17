package com.ssg.networkapp.multicast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Client에서 메시지를 받는 데 쓰는 쓰레드
 */
public class ClientChatThread implements Runnable{
	Client client; // gui 클라이언트
	Socket socket;
	BufferedReader bReader;
	BufferedWriter bWriter;
	
	public ClientChatThread(Client client, Socket socket){
		this.client = client;
		this.socket = socket;
		
		try {
			bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 사용자가 보낸 경우 메시지를 송출
	public void send(String msg) {
		try {
			bWriter.write(msg+"\n");
			bWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 듣기
	public void listen() {
		String msg = null;
		try {
			msg = bReader.readLine();
			client.area.append(msg+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			listen();
		}
	}
	
	
}
