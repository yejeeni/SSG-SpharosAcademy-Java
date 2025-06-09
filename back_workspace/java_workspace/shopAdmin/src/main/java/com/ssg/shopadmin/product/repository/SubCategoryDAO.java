package com.ssg.shopadmin.product.repository;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.SubCategory;
import com.ssg.shopadmin.product.model.TopCategory;

public class SubCategoryDAO {
	DBManager dbManager = DBManager.getInstance();

	
	// 모든 레코드 가져오기
	public void selectAll() {
		
	}
	
	/**
	 * 하위 카테고리 중, 특정 상위 카테고리 id에 소속된 목록만 가져오기
	 */
	public List selectByTop(TopCategory topCategory) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		
		ArrayList<SubCategory> list = new ArrayList<SubCategory>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sub_category where top_category_id = ?");
		
		connection = dbManager.getConnection();
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, topCategory.getTop_category_id());
			rSet = pStatement.executeQuery();
			
			while(rSet.next()){
				SubCategory subCategory = new SubCategory();
				
				subCategory.setSub_category_id(rSet.getInt("sub_category_id"));
				subCategory.setSub_category_name(rSet.getString("sub_category_name"));
				subCategory.setTop_category(topCategory);
				list.add(subCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, rSet);
		}
		
		return list;
	}
	
	// 레코드 한 건 가져오기
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
