package com.ssg.ioproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleTest {
	public static void main(String[] args) {
		// 오라클에 접속하기
		/*
		 * 1) java 언어가 데이터베이스를 핸들링하기 위해서는 db를 제작한 벤더사에서 제공하는 구현체인 드라이버를 먼저 메모리에 로드해야 함
		 * 		단, 일반 클래스처럼 new할 순 없고, method 영역에 직접 올려야 함
		 */
		try {
			// JVM 메서드 영역에 직접 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
			
			// 원격지의 오라클에 접속
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "java", "1234");
			if (con!=null) {
				System.out.println("접속 성공");
			} else {
				System.out.println("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
