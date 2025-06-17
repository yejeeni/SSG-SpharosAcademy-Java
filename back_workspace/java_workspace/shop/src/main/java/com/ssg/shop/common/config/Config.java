package com.ssg.shop.common.config;

/**
 * 유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스
 */
public class Config {
	/*-------------------------------------------
	 * 데이터베이스 접속 정보
	 -------------------------------------------*/
	public static final String URL = "jdbc:mysql://localhost:3306/shop";
	public static final String USER = "shop";
	public static final String PW = "1234";
	
	public static final String PRODUCT_IMAGE_PATH = "C:\\public";
	
	/*-------------------------------------------
	 * 페이지 번호
	 -------------------------------------------*/
	public static final int MAIN_PAGE = 0; // 메인 페이지
	public static final int JOIN_PAGE = 1; // 회원가입 페이지
	public static final int PRODUCT_DETAIL_PAGE = 2; // 상품 페이지
	public static final int CUSTOMER_PAGE = 3; // 고객센터 페이지
	public static final int LOGIN_PAGE = 4;
	public static final int CART_PAGE = 5;
	public static final int WISHLIST_PAGE = 6;
	
	/*-------------------------------------------
	 * 쇼핑몰 앱 메인 설정
	 -------------------------------------------*/	
	public static final int SHOPMAIN_WIDTH = 1400;
	public static final int SHOPMAIN_HEIGHT = 900;
	
	public static final int UTIL_WIDTH = SHOPMAIN_WIDTH;
	public static final int UTIL_HEIGHT = 40;
	
	public static final int NAVI_WIDTH = SHOPMAIN_WIDTH;
	public static final int NAVI_HEIGHT = 50;
	
	/*-------------------------------------------
	 * 메인 페이지 설정
	 -------------------------------------------*/
	public static final int MAIN_VISUAL_WIDTH = SHOPMAIN_WIDTH;
	public static final int MAIN_VISUAL_HEIGHT = 400;
		
}
