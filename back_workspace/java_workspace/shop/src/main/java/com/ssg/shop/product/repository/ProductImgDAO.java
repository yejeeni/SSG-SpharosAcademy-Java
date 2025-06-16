package com.ssg.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssg.shop.common.exception.ProductImgException;
import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.product.model.ProductImg;


public class ProductImgDAO {

	/**
	 * 하나의 제품의 이미지들 등록
	 */
	public void insert(ProductImg productImg) throws ProductImgException {
		DBManager dbManager = DBManager.getInstance();
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=dbManager.getConnection();
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			
			int result =pstmt.executeUpdate();
			if (result < 1) {
				throw new ProductImgException("상품 등록에 문제가 발생했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductImgException("상품 이미지 등록 중 문제가 발생했습니다.", e);
		}finally {
			dbManager.release(pstmt);
		}
	}
}
