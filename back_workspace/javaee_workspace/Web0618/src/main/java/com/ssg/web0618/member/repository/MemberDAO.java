package com.ssg.web0618.member.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.web0618.exception.MemberException;
import com.ssg.web0618.member.model.Member;

public class MemberDAO {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";
	
	public void insert(Member member) throws MemberException{
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			StringBuffer sql = new StringBuffer();
			sql.append("insert into member(id, pwd, email) values(?, ?, ?)");
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setString(1, member.getId());
			pStatement.setString(2, member.getPwd());
			pStatement.setString(3, member.getName());
			
			
			int result = pStatement.executeUpdate();
			if (result == 0) {
				throw new MemberException("회원가입 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new MemberException("회원가입 실패", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MemberException("회원가입 실패", e);
		} finally {
			if (pStatement != null) {
				try {
					pStatement.close();
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
}
