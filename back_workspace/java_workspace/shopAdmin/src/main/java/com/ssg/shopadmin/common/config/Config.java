package com.ssg.shopadmin.common.config;

/**
 * 유저용 쇼핑몰에서 사용되는 모든 상수를 관리하는 클래스
 */
public class Config {
	/*-------------------------------------------
	 * 데이터베이스 접속 정보
	 -------------------------------------------*/
	public static final String url = "jdbc:mysql://localhost:3306/shop";
	public static final String user = "shop";
	public static final String pw = "1234";
	
	public static final String PRODUCT_IMAGE_PATH = "C:\\public";
	
	/*-------------------------------------------
	 * 페이지 번호
	 -------------------------------------------*/
	public static final int LOGIN_PAGE = 0; // 로그인 페이지
	public static final int MAIN_PAGE = 1; // 메인 페이지
	public static final int PRODUCT_PAGE = 2; // 상품등록 페이지
	public static final int ORDER_PAGE = 3; // 주문관리 페이지
	public static final int MEMBER_PAGE = 4; // 회원관리 페이지
	public static final int CUSTOMER_PAGE = 5; // 고객센터 관리 페이지
	public static final int CONFIG_PAGE = 6; // 관리 페이지
	public static final int JOIN_PAGE = 7; // 관리자 회원가입 페이지
	public static final int PRODUCT_LIST_PAGE = 8; // 상품 목록 페이지
	
	/*-------------------------------------------
	 * 관리자 앱 메인 설정
	 -------------------------------------------*/	
	public static final int ADMIN_MAIN_WIDTH = 1300;
	public static final int ADMIN_MAIN_HEIGHT = 900;
	
	public static final int UTIL_WIDTH = ADMIN_MAIN_WIDTH;
	public static final int UTIL_HEIGHT = 50;
	
	public static final int SIDE_WIDTH = 200;
	public static final int SIDE_HEIGHT = ADMIN_MAIN_HEIGHT - UTIL_HEIGHT;

}
