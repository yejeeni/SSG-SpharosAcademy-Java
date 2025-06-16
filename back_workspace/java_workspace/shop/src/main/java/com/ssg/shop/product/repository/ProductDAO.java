package com.ssg.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssg.shop.common.exception.ProductException;
import com.ssg.shop.common.util.DBManager;
import com.ssg.shop.product.model.Color;
import com.ssg.shop.product.model.Product;
import com.ssg.shop.product.model.Size;
import com.ssg.shop.product.model.SubCategory;
import com.ssg.shop.product.model.TopCategory;


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
	
	public List selectRecentList(int limit) {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		PreparedStatement pStatement2 = null; // size용
		ResultSet rSet2 = null;
		PreparedStatement pStatement3 = null; // color용
		ResultSet rSet3 = null;
		PreparedStatement pStatement4 = null; // file용
		ResultSet rSet4 = null;
		List<Product> list = new ArrayList<>();
		
		connection = dbManager.getConnection();
		
		StringBuffer sql = new StringBuffer();
		// 상품과 서브 카테고리를 조인하고, 1 대 다 관계의 이미지, 사이즈 컬러를 서브 쿼리로 가져와서 모두 Product모델에 저장
		sql.append("select product_id, product_name, brand, price, discount, introduce, detail");
		sql.append(", s.sub_category_id, sub_category_name");
		sql.append(" from product p");
		sql.append(" join sub_category s");
		sql.append(" on p.sub_category_id = s.sub_category_id");
		sql.append(" order by product_id desc limit ?");
//		System.out.println(sql.toString());
		
		try {
			pStatement = connection.prepareStatement(sql.toString());
			pStatement.setInt(1, limit);
			
			rSet = pStatement.executeQuery();
			
			while(rSet.next()) { // 상품의 수만큼 반복
				Product product = new Product();
				product.setProduct_id(rSet.getInt("product_id"));
				product.setProduct_name(rSet.getString("product_name"));
				product.setBrand(rSet.getString("brand"));
				product.setPrice(rSet.getInt("price"));
				product.setDiscount(rSet.getInt("discount"));
				product.setIntroduce(rSet.getString("introduce"));
				product.setDetail(rSet.getString("detail"));
				
				SubCategory subCategory = new SubCategory();
				subCategory.setSub_category_id(rSet.getInt("s.sub_category_id"));
				subCategory.setSub_category_name(rSet.getString("sub_category_name"));
				product.setSubCategory(subCategory);
				
				// 상품이 보유한 사이즈들
				sql.delete(0, sql.length()); // 스트링 버퍼의 문자열 지우기
				sql.append("select size_name");
				sql.append(" from product_size ps");
				sql.append(" join size s");
				sql.append(" where ps.size_id = s.size_id");
				sql.append(" and ps.product_id = ?");
				
				pStatement2 = connection.prepareStatement(sql.toString());
				pStatement2.setInt(1, product.getProduct_id());
				
				rSet2 = pStatement2.executeQuery();
				
				List sizeList = new ArrayList<>();
				while(rSet2.next()) {
					Size size = new Size();
					size.setSize_name(rSet2.getString("size_name"));
					sizeList.add(size);
				}
				product.setSizeList(sizeList);
				
				// 상품이 보유한 컬러들
				sql.delete(0, sql.length()); // 스트링 버퍼의 문자열 지우기
				sql.append("select color_name");
				sql.append(" from product_color pc");
				sql.append(" join color c");
				sql.append(" where pc.color_id = c.color_id");
				sql.append(" and pc.product_id = ?");
				
				pStatement3 = connection.prepareStatement(sql.toString());
				pStatement3.setInt(1, product.getProduct_id());
				
				rSet3 = pStatement3.executeQuery();
				
				List colorList = new ArrayList<>();
				while(rSet3.next()) {
					Color color = new Color();
					color.setColor_name(rSet3.getString("color_name"));
					colorList.add(color);
				}
				product.setColorList(colorList);
				
				// 상품이 보유한 이미지 파일들
				sql.delete(0, sql.length()); // 스트링 버퍼의 문자열 지우기
				sql.append("select filename");
				sql.append(" from product_img");
				sql.append(" where product_id = ?");
				
				pStatement4 = connection.prepareStatement(sql.toString());
				pStatement4.setInt(1, product.getProduct_id());
				
				rSet4 = pStatement4.executeQuery();
				
				List filenameList = new ArrayList<>();
				while(rSet4.next()) {
					String filename = new String(rSet4.getString("filename"));
					filenameList.add(filename);
				}
				product.setFilenames(filenameList);
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pStatement, rSet);
			dbManager.release(pStatement2, rSet2);
			dbManager.release(pStatement3, rSet3);
			dbManager.release(pStatement4, rSet4);
		}
		
		return list;
	}
}
