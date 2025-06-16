package com.ssg.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shop.common.exception.ProductColorException;
import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.product.model.ProductColor;


public class ProductColorDAO {

	DBManager dbManager = DBManager.getInstance();
	
	/**
	 * 상품이 보유한 색상들을 입력
	 * @return
	 */
	public void insert(ProductColor productColor) throws ProductColorException{
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		connection = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_color(product_id, color_id) values(?, ?)");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, productColor.getProduct().getProduct_id());
			pStatement.setInt(2, productColor.getColor().getColor_id());
			
			int result = pStatement.executeUpdate(); // DML 실행
			if (result < 1) {
				throw new ProductColorException("상품 색상이 등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductColorException("상품 색상 등록 중 문제가 발생했습니다.", e);
		} finally {
			dbManager.release(pStatement);
		}
	}
}
