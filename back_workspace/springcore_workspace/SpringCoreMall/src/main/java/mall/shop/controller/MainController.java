package mall.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("shopMainController")
public class MainController {

	@RequestMapping(value="/shop", method = RequestMethod.GET)
	public ModelAndView getMain() {
		ModelAndView modelAndView = new ModelAndView("shop/index");
		return modelAndView;
	}
}
