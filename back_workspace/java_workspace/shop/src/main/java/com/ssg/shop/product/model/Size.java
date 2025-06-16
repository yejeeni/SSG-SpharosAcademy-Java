package com.ssg.shop.product.model;

/**
 * Size 모델 객체
 */
public class Size {
	private int size_id;
	private String size_name;
	
	public int getSize_id() {
		return size_id;
	}
	public void setSize_id(int size_id) {
		this.size_id = size_id;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}
	
	@Override
	public String toString() {
		return size_name;
	}

}
