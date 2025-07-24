package mall.domain;

import java.util.List;

import lombok.Data;

@Data
public class Cart {
	private int cart_id;
	private Product product;
	private int ea;
	private int member_id;

	// 상품 커스텀 옵션
	private String selectedColor;
	private String selectedSize;
	
}
