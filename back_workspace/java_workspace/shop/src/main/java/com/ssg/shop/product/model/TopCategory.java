package com.ssg.shop.product.model;

/**
 * 로직을 담기 위한 객체가 아니라 테이블의 레코드를 담기 위한 모델 객체
 * 은닉화시켜 정의해야 함
 */
public class TopCategory {
	private int top_category_id;
	private String top_category_name;
	
	public int getTop_category_id() {
		return top_category_id;
	}
	public void setTop_category_id(int top_category_id) {
		this.top_category_id = top_category_id;
	}
	public String getTop_category_name() {
		return top_category_name;
	}
	public void setTop_category_name(String top_category_name) {
		this.top_category_name = top_category_name;
	}
	
	// 이 클래스를 String 취급하면서 출력하려고 한다면, 자동을 호출되는 메서드인 Object로부터 상속 받은 toString() 메서드가 호출되므로, 이 객체의 데이터 중 원하는 데이터를 출력하게 하고자 오버라이딩
	@Override
	public String toString() {
		return top_category_name;
	}
	
}
