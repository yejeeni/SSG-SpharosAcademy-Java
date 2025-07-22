package mall.domain;

import lombok.Data;

@Data
public class ProductImg {
	private int product_img_id;
	private Product product;
	private String filename;
}
