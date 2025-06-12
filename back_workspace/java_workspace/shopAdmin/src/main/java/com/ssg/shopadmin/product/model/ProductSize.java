package com.ssg.shopadmin.product.model;

public class ProductSize {
	private int product_size_id;
	private Product product;
	private Size size;
	
	public int getProduct_size_id() {
		return product_size_id;
	}
	public void setProduct_size_id(int product_size_id) {
		this.product_size_id = product_size_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	
	
}
