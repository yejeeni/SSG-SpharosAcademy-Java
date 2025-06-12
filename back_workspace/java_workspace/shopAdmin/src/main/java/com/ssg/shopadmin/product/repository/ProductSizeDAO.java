package com.ssg.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.ProductSize;

public class ProductSizeDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(ProductSize productSize) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		int result = 0;
		
		connection = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_size(product_id, size_id) values(?, ?)");
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, productSize.getProduct().getProduct_id());
			pStatement.setInt(2, productSize.getSize().getSize_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
