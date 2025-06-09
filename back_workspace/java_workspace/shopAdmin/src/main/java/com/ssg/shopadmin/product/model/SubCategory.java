package com.ssg.shopadmin.product.model;

public class SubCategory {
	private int sub_category_id;
	private String sub_category_name;
	private TopCategory top_category;
	
	public int getSub_category_id() {
		return sub_category_id;
	}
	public void setSub_category_id(int sub_category_id) {
		this.sub_category_id = sub_category_id;
	}
	
	public String getSub_category_name() {
		return sub_category_name;
	}
	public void setSub_category_name(String sub_category_name) {
		this.sub_category_name = sub_category_name;
	}
	public TopCategory getTop_category() {
		return top_category;
	}
	public void setTop_category(TopCategory top_category) {
		this.top_category = top_category;
	}
	
	@Override
	public String toString() {
		return sub_category_name;
	}
	
}
