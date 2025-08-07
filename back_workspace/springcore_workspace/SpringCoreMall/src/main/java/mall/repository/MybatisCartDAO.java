package mall.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mall.domain.Cart;
import mall.exception.CartException;

@Repository
public class MybatisCartDAO implements CartDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(Cart cart) {
		int result = sqlSessionTemplate.insert("Cart.insert", cart);
		
		if (result < 1) {
			throw new CartException("장바구니 등록 실패");
		}
	}

}
