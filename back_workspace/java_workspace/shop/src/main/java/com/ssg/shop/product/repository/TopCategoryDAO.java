package com.ssg.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.product.model.TopCategory;


// DB에서 테이블이 하나 정의되면 이 테이블과 1대1 대응되는 모델 객체와 테이블에 대한 CRUD를 만듦
public class TopCategoryDAO {
	// DBManager는 싱글톤 패턴으로 정의되어 있으므로 new 불가
	DBManager dbManager = DBManager.getInstance();

	// TopCategory의 모든 레코드 가져오기
	public ArrayList<TopCategory> selectAll() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		ArrayList<TopCategory> list = new ArrayList<TopCategory>();

		connection = dbManager.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("select * from top_category");
		try {
			pStatement = connection.prepareStatement(sql.toString());
			resultSet = pStatement.executeQuery();
			
			while (resultSet.next()) {
				TopCategory topCategory = new TopCategory(); 
				topCategory.setTop_category_id(resultSet.getInt("top_category_id"));
				topCategory.setTop_category_name(resultSet.getString("top_category_name"));
				
				list.add(topCategory);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			dbManager.release(pStatement, resultSet);
		}
		
		return list;
	}

	// TopCategory의 레코드 한 건 가져오기
	public void select() {

	}

	// 한 건 입력
	public void insert() {

	}

	// 한 건 수정
	public void update() {

	}

	// 한 건 삭제
	public void delete() {

	}
}
