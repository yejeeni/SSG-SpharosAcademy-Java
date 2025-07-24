package mall.service;

import java.util.List;

import mall.domain.Product;

public interface ProductService {
	
	public void regist(Product product, String savePath);
	
	public List selectAll();
	
	public Product select(int product_id);
	
	public void update(Product product);
	
	public void delete(Product product);
	
	public void remove(Product product, String savePath);
}
