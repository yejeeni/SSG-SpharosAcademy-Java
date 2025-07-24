package mall.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Cart;

@Slf4j
@Controller
public class CartController {

	// 장바구니 담기
	@PostMapping("/cart/regist")
	public String regist(Cart cart) {
		
		log.debug("product_id = "+cart.getProduct().getProduct_id());
		log.debug("ea = "+cart.getEa());
		log.debug("member id " + cart.getMember_id());
		log.debug("selectedColors "+cart.getSelectedColors());
		log.debug("selectedSize "+cart.getSelectedSizes());
		
		return null;
	}
}
