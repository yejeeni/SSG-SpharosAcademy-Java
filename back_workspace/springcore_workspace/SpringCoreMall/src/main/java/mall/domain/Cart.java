package mall.domain;

import lombok.Data;

@Data
public class Cart {
	private int cart_id;
	private Product product;
	private int ea;
	private Member member;

	// 상품 커스텀 옵션
	private Color selectedColor;
	private Size selectedSize;
	
}
