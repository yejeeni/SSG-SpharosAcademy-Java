package mall.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private String introduce;
	private String detail;
	private MultipartFile photo; // 상품 이미지
	
	private SubCategory subCategory;
	
}
