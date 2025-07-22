package mall.domain;

import lombok.Data;

@Data
public class ProductSize {
	private int product_size_id;
	private Product product;
	private Size size;
}
