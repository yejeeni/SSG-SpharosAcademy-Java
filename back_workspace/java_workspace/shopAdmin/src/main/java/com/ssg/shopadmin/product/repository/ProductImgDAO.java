package com.ssg.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.ProductImg;

public class ProductImgDAO {

	/**
	 * 하나의 제품의 이미지들 등록
	 */
	public int insert(ProductImg productImg) {
		DBManager dbManager = DBManager.getInstance();
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=dbManager.getConnection();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			result =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(pstmt);
		}
		return result;
	}
}
