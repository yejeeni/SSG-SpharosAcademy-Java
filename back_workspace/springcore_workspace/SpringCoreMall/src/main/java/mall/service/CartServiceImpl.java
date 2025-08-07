package mall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mall.domain.Cart;
import mall.exception.CartException;
import mall.repository.CartDAO;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void regist(Cart cart) throws CartException {
		cartDAO.insert(cart);
	}

}
