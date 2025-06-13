package com.ssg.shop.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.ssg.shop.common.exception.EmailExpection;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * 이메일을 보내는 객체
 */
public class MailSender {
	String account_user = "your email"; // 구글 이메일 계정
	String app_pwd = "your app pw"; // 구글 앱 비밀번호
	Session session;

	public MailSender() {
		// key-value map의 자식 객체
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // tls: transport layer security. 데이터를 암호화하여 안전하게 전송하는 프로토콜
		props.put("mail.smtp.host", "smtp.gmail.com"); // 구글에 보내는 메일 서버
		props.put("mail.smtp", "587"); // 포트번호

		session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account_user, app_pwd);
			}
		});
	}

	public void send(String targetMail, String title, String content) throws EmailExpection {
		
		try {
			// 메일의 내용 구성하기
			Message message = new MimeMessage(session);

			// 보내는 사람의 메일
			message.setFrom(new InternetAddress(account_user));

			// 받는 사람의 메일
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetMail));

			// 제목과 내용
			message.setSubject(title);
			message.setText(content);

			// 메일 전송
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailExpection("이메일 찾기 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailExpection("메일 발송 실패", e);
		}
	}

	public void sendHtml(String targetMail, String title, String content) throws EmailExpection {
		
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			fileInputStream = new FileInputStream("C:/lecture_workspace/back_workspace/java_workspace/shop/data/mailForm.html");
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			
			while(true) {
				String tag = bufferedReader.readLine(); // 한줄 읽기
				if (tag == null) {
					break;
				}
				
				stringBuffer.append(tag); // 한줄씩 읽은 문자열을 누적
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
		// 메일의 내용 구성하기
		Message message = new MimeMessage(session);

		try {
			// 보내는 사람의 메일
			message.setFrom(new InternetAddress(account_user));
			// 받는 사람의 메일
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetMail));
			// 제목
			message.setSubject(title);

			StringBuffer tag = new StringBuffer();
			tag.append("<h1>가입을 축하드립니다</h1>");
			tag.append("<p>본 메일은 회원가입 시 자동으로 발송되는 메일입니다.</p>");

			message.setContent(stringBuffer.toString(), "text/html; charset=utf-8");
			Transport.send(message);

		} catch (AddressException e) {
			e.printStackTrace();
			throw new EmailExpection("이메일 찾기 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailExpection("메일 발송 실패", e);
		}
	}
}
