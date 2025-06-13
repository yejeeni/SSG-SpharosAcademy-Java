package com.ssg.shopadmin.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssg.shopadmin.common.exception.ProductException;
import com.ssg.shopadmin.common.util.DBManager;
import com.ssg.shopadmin.product.model.Product;
import com.ssg.shopadmin.product.model.SubCategory;
import com.ssg.shopadmin.product.model.TopCategory;

/**
 * Product 테이블에 대한 CRUD
 */
public class ProductDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public void insert(Product product) throws ProductException {
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
			if (result  == 0) {
				throw new ProductException("상품이 등록되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); // 여기서 처리할 경우 바깥쪽의 유저가 사용하는 프로그램에서는 에러 원인을 알 수 없음. 따라서 에러 발생 시 외부 영역까지 에러 원인을 전달해야 함
			throw new ProductException("상품 등록 중 문제가 발생했습니다.", e);
			
		}
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
	
	/**
	 * 모든 상품의 목록 가져오기
	 */
	public List selectAll() {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		List<Product> list = new ArrayList();
		
		connection = dbManager.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select t.top_category_id, top_category_name, s.sub_category_id, sub_category_name");
		sql.append(", product_id, product_name, brand, price, discount, introduce, detail");
		sql.append(" FROM top_category t");
		sql.append(" JOIN sub_category s ON t.top_category_id = s.top_category_id");
		sql.append(" JOIN product p ON s.sub_category_id = p.sub_category_id");
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			resultSet = pStatement.executeQuery();
			
			while(resultSet.next()) {
				Product product = new Product();
				product.setProduct_id(resultSet.getInt("product_id"));
				product.setProduct_name(resultSet.getString("product_name"));
				product.setBrand(resultSet.getString("brand"));
				product.setPrice(resultSet.getInt("price"));
				product.setDiscount(resultSet.getInt("discount"));
				product.setIntroduce(resultSet.getString("introduce"));
				product.setDetail(resultSet.getString("detail"));
			
				// 하위카테고리
				SubCategory subCategory = new SubCategory();
				subCategory.setSub_category_id(resultSet.getInt("s.sub_category_id"));
				subCategory.setSub_category_name("sub_category_name");					
				product.setSubCategory(subCategory);
				
				// 상위 카테고리
				TopCategory topCategory = new TopCategory();
				topCategory.setTop_category_id(resultSet.getInt("t.top_category_id"));
				topCategory.setTop_category_name(resultSet.getString("top_category_name"));
				subCategory.setTop_category(topCategory);
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, resultSet);
		}
		
		return list;
	}
}
