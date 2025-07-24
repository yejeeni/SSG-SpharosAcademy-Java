package mall.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import mall.domain.Product;
import mall.domain.TopCategory;
import mall.service.ProductService;
import mall.service.TopCategoryService;

/*
 * 일반 유저가 사용하는 쇼핑몰의 상품 쪽 요청을 처리하는 컨트롤러
 */
@Controller
public class ProductController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 상품 목록 요청
	 */
	@GetMapping("/product/list")
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("shop/list");
		
		// 최상위 카테고리 가져오기
		List<TopCategory> topCategories = topCategoryService.selectAll();
		
		// 모든 상품 가져오기
		List<Product> productList = productService.selectAll();
		
		mav.addObject("topCategories", topCategories);
		mav.addObject("productList", productList);
		
		return mav;
	}
	
	/**
	 * 상품 상세 요청
	 */
	@GetMapping("/product/detail")
	public ModelAndView getProductDetail(int product_id) {
		ModelAndView mav = new ModelAndView("shop/detail");// 최상위 카테고리 가져오기
		
		List<TopCategory> topCategories = topCategoryService.selectAll();
		Product product = productService.select(product_id);

		mav.addObject("topCategories", topCategories);
		mav.addObject("product", product);
		
		return mav;
	}
}
