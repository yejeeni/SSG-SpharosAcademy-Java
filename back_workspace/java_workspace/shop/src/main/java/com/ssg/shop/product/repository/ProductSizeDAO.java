package com.ssg.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shop.common.exception.ProductSizeException;
import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.product.model.ProductSize;


public class ProductSizeDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public void insert(ProductSize productSize) throws ProductSizeException{
		Connection connection = null;
		PreparedStatement pStatement = null;
		
		connection = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_size(product_id, size_id) values(?, ?)");
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, productSize.getProduct().getProduct_id());
			pStatement.setInt(2, productSize.getSize().getSize_id());
			int result = pStatement.executeUpdate();
			if (result < 0) {
				throw new ProductSizeException("상품 사이즈가 등록되지 않았습니다."); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductSizeException("상품 사이즈 등록 중 문제가 발생했습니다.", e);
		}
	}
}
