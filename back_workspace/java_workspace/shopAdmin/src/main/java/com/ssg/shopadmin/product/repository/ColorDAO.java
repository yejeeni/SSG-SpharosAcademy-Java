package com.ssg.shopadmin.product.repository;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.Color;

/**
 * Color 테이블의 쿼리 전담 객채
 */
public class ColorDAO {
	DBManager dbManager = DBManager.getInstance();
	
	// insert
	public int insert(Color color) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into color(color_name) values(?)");
		
		connection = dbManager.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());
			// 바인드변수 결정
			pStatement.setString(1, color.getColor_name());
			
			// 쿼리 수행
			result = pStatement.executeUpdate(); // DML 수행 후 영향을 받은 레코드 수 반환						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement);
		}
		return result;
		
	}
	
	/**
	 * 모든 색상 가져오기
	 * @return
	 */
	public ArrayList<Color> selectAll() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<Color> list = new ArrayList<>();
		
		connection = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from color");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			rSet = pStatement.executeQuery();
			
			// resultSet이 보유한 데이터를 모델 객체에 저장
			while(rSet.next()) {
				Color color = new Color(); // Color 모델 인스턴스
				color.setColor_id(rSet.getInt("color_id"));
				color.setColor_name(rSet.getString("color_name"));
				
				list.add(color);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, rSet);
		}
		
		return list;
		
	}
}
