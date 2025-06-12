package com.ssg.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.Product;

/**
 * Product 테이블에 대한 CRUD
 */
public class ProductDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(Product product) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		
		connection = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product(product_name, brand, price, discount, introduce, detail, sub_category_id)");
		sql.append(" values(?, ?, ?, ?, ?, ?, ?)");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setString(1, product.getProduct_name());
			pStatement.setString(2, product.getBrand());
			pStatement.setInt(3, product.getPrice());
			pStatement.setInt(4, product.getDiscount());
			pStatement.setString(5, product.getIntroduce());
			pStatement.setString(6, product.getDetail());
			pStatement.setInt(7, product.getSubCategory().getSub_category_id());
			
			result = pStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * 가장 최근에 AUTO_INCREMENT 컬럼에 삽입된 값을 반환 (멀티유저 환경에서 유용)
	 */
	public int selectLastInsertId() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		
		connection = dbManager.getConnection();
		StringBuffer sql = new StringBuffer("select last_insert_id() as id");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			resultSet = pStatement.executeQuery();
			
			if (resultSet.next()) { // 조회 결과가 존재하는 경우
				id = resultSet.getInt("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, resultSet);
		}
		
		return id;
	}
}
