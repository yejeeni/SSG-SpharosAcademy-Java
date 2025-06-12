package com.ssg.shopadmin.product.model;

public class ProductColor {
	private int product_color_id;
	private Product product;
	private Color color;
	
	public int getProduct_color_id() {
		return product_color_id;
	}
	public void setProduct_color_id(int product_color_id) {
		this.product_color_id = product_color_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
