package com.ssg.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.Size;

public class SizeDAO {
	DBManager dbManager = DBManager.getInstance();
	
	// insert
	public int insert(Size size) {
		Connection connection = dbManager.getConnection();
		PreparedStatement pStatement = null;
		int result = 0; // insert 수행 결과 개수
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into size(size_name) value(?)");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			
			pStatement.setString(1, size.getSize_name());
			
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement);
		}
		
		return result;
	}
	
	public ArrayList<Size> selectAll() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Size> list = new ArrayList<>();
		
		connection = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from size");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			rSet = pStatement.executeQuery();
			
			// resultSet이 보유한 데이터를 모델 객체에 저장
			while(rSet.next()) {
				Size size = new Size(); // Color 모델 인스턴스
				size.setSize_id(rSet.getInt("size_id"));
				size.setSize_name(rSet.getString("size_name"));
				
				list.add(size);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, rSet);
		}
		
		return list;
		
	}
}
