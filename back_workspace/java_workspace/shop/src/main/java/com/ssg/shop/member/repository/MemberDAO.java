package com.ssg.shop.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shop.common.exception.MemberExpection;
import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.member.model.Member;

public class MemberDAO {

	DBManager dbManager = DBManager.getInstance();
	
	/**
	 * 회원 가입 insert
	 */
	
	public void insert(Member member) throws MemberExpection{
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		connection = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member(id, pwd, name, email) values(?, ?, ?, ?)");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setString(1, member.getId());
			pStatement.setString(2, member.getPwd());
			pStatement.setString(3, member.getName());
			pStatement.setString(4, member.getEmail());

			int result = pStatement.executeUpdate();
			if (result < 1) { // 가입이 실패한 경우 외부 영역에 예외 전달
				throw new MemberExpection("가입 등록 중 문제가 발생했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			{
				dbManager.release(pStatement);
			}
		}
		
	}
}
