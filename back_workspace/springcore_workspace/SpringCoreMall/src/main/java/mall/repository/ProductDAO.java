package mall.repository;

import java.util.List;

import mall.domain.Product;

public interface ProductDAO {
	
	public void insert(Product product);
	public List selectAll();
	public Product select(int product_id);
	public void update(Product product);
	public void delete(Product product);		
	
}
