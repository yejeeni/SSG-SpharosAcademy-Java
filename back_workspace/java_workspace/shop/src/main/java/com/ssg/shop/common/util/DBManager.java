package com.ssg.shop.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssg.shop.common.config.Config;

/* 
 * AppMain에서 db를 핸들링하지 않고, 중립적인 객체에서 connection을 얻고 닫는 기능을 보유한 객체 선언
 */

public class DBManager {
	
	private static DBManager instance;
	
	private Connection connection;
	
	public static DBManager getInstance() {
		// 인스턴스가 존재하지 않는 경우 생성해줌
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
		
	// 생성자 접근 제한
	private DBManager() {
		try {
			// 1) 드라이버 로드
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2) 접속
			connection = DriverManager.getConnection(Config.url, Config.user, Config.pw);
	
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
	    if (connection == null) {
	        throw new IllegalStateException(
	            "DB 연결 실패: connection 객체가 null입니다.\n" +
	            "→ URL, 계정, 비밀번호, DB 서버 상태를 먼저 확인하세요.");
	    }
	    return connection;
	}

	
	// db 관련 자원을 해제하는 메서드
	public void release(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void release(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void release(PreparedStatement preparedStatement, ResultSet resultSet) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	public void release(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
