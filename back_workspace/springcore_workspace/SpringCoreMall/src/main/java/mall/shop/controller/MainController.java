package mall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mall.domain.TopCategory;
import mall.service.TopCategoryService;

@Controller("shopMainController")
public class MainController {

	@Autowired
	private TopCategoryService topCategoryService;
	
	@RequestMapping(value="/shop", method = RequestMethod.GET)
	public ModelAndView getMain() {
		// 서비스
		List<TopCategory> topCategories = topCategoryService.selectAll();
		
		// 결과 저장
		ModelAndView modelAndView = new ModelAndView("shop/index");
		modelAndView.addObject("topCategories", topCategories);
		
		return modelAndView;
	}
}
