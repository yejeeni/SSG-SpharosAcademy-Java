package com.ssg.networkapp.multicast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame implements Runnable{

   JPanel p_north;
   JComboBox cb_ip;
   JTextField t_port;
   JButton bt;
   
   JTextArea area;
   JScrollPane scroll;
   JTextField t_input;
   
   JPanel p_south;
   
   Thread thread; // 접속 스레드
   ClientChatThread chatThread; // 채팅용 스레드
   
   public Client() {

      p_north = new JPanel();
      cb_ip = new JComboBox();
      t_port = new JTextField("9999");
      bt = new JButton("접속");
      
      area = new JTextArea();
      scroll = new JScrollPane(area);
      t_input = new JTextField();
      
      p_south = new JPanel();
      
      thread = new Thread(this);
      
      cb_ip.setPreferredSize(new Dimension(110, 30));
      t_port.setPreferredSize(new Dimension(100,30));
      bt.setPreferredSize(new Dimension(60,30));
      
      scroll.setPreferredSize(new Dimension(300,400));
      area.setBackground(Color.yellow);
      
      t_input.setPreferredSize(new Dimension(300,30));
      
      
      p_north.add(cb_ip);
      p_north.add(t_port);
      p_north.add(bt);
      
      p_south.add(t_input);
      
      add(p_north, BorderLayout.NORTH);
      add(scroll);
      add(p_south, BorderLayout.SOUTH);

      bt.addActionListener(e -> {
         //서버에 접속 시작
         thread.start();
      });
      
      t_input.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               chatThread.send(t_input.getText());
            }
         }
      });
      
      cb_ip.addItem("192.168.60.21");
      cb_ip.addItem("192.168.60.20");
      
      setBounds(200,100,350,500);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
      
   }
   
   public void connectServer() {
      String ip = (String)cb_ip.getSelectedItem();
      int port = Integer.parseInt(t_port.getText());
      try {
         Socket socket = new Socket(ip, port);
         
         // 소켓을 메시지 송수신용 쓰레드에 전달
         chatThread = new ClientChatThread(this, socket);
         Thread thread = new Thread(chatThread);
         thread.start();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public void run() {
      connectServer();
   }
   
   public static void main(String[] args) {
      new Client();
   }
   
}
