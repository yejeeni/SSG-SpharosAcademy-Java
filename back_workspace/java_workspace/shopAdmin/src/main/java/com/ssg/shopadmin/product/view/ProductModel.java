package com.ssg.shopadmin.product.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.ssg.shopadmin.product.model.Product;
import com.ssg.shopadmin.product.repository.ProductDAO;

public class ProductModel extends AbstractTableModel{

	ProductDAO productDAO;
	List<Product> list;	
	String[] column = {
			"top_category_id", 
			"top_category_name", 
			"sub_category_id", 
			"sub_category_name", 
			"product_id", 
			"product_name", 
			"brand", 
			"price", 
			"discount", 
			"introduce", 
			"detail"
			};
	
	public ProductModel() {
		productDAO = new ProductDAO();
		list = productDAO.selectAll();
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {// rowIndex는 행, colIndex는 0번째 행, 1번째 행, ... 의 어느 컬럼값을 가져올지?
		String value = null;
		Product product = list.get(rowIndex); // 레코드 값 불러오기
		
		// 이부분 지저분하니까 다으부터는 switch-case로 바꾸기
		if (columnIndex == 0) {
			value = Integer.toString(product.getSubCategory().getTop_category().getTop_category_id());
		}
		if (columnIndex == 1) {
			value = product.getSubCategory().getTop_category().getTop_category_name();
		}
		if (columnIndex == 2) {
			value = Integer.toString(product.getSubCategory().getSub_category_id());
		}
		if (columnIndex == 3) {
			value =product.getSubCategory().getSub_category_name();
		}
		if (columnIndex == 4) {
			value = Integer.toString(product.getProduct_id());
		}
		if (columnIndex == 5) {
			value = product.getProduct_name();
		}
		if (columnIndex == 6) {
			value = product.getBrand();
		}
		if (columnIndex == 7) {
			value = Integer.toString(product.getPrice());
		}
		if (columnIndex == 8) {
			value = Integer.toString(product.getDiscount());
		}
		if (columnIndex == 9) {
			value = product.getIntroduce();
		}
		if (columnIndex == 10) {
			value = product.getDetail();
		}
		
		return value;
	}
	
}
