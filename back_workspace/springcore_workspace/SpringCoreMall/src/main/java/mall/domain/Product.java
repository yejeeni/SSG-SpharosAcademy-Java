package mall.domain;

import java.util.List;

import javax.persistence.OneToMany;

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
	
	@OneToMany
	private List<ProductColor> productColorList;
	
	@OneToMany
	private List<ProductSize> productSizeList;
	
	@OneToMany
	private List<ProductImg> productImgList;
	
	private MultipartFile[] photo; // 상품 이미지
	
	private SubCategory subCategory;
	
}
