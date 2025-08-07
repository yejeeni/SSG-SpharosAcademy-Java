package mall.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Cart;
import mall.domain.Member;

@Slf4j
@Controller
public class CartController {
	
	// 장바구니 목록 요청
	@GetMapping("/cart/list")
	public String getList(Model model) {
		return "shop/order/cart_list";
	}

	// 장바구니 담기
	@PostMapping("/cart/regist")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> regist(Cart cart, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		cart.setMember(member);
		
		log.debug("product_id = "+cart.getProduct().getProduct_id());
		log.debug("ea = "+cart.getEa());
		log.debug("member id " + member.getId());
		log.debug("selectedColors "+cart.getSelectedColor());
		log.debug("selectedSize "+cart.getSelectedSize());
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
