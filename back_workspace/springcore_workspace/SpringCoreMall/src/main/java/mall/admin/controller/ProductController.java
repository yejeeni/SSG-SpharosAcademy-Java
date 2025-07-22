package mall.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Color;
import mall.domain.Product;
import mall.domain.ProductColor;
import mall.domain.ProductSize;
import mall.domain.Size;
import mall.domain.TopCategory;
import mall.service.ColorService;
import mall.service.ProductService;
import mall.service.TopCategoryService;

@Slf4j
@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService; // RootConfig가 연결
	@Autowired
	private TopCategoryService topCategoryService;

	@RequestMapping("/product/registform")
	public String registform(Model model) {
		// 상품 등록페이지를 보여주면서 상위 카테고리를 채우기
		System.out.println(topCategoryService.getClass());

		List<TopCategory> categories = topCategoryService.selectAll();

		model.addAttribute("topList", categories);

		System.out.println("ProductController.registform() 호출됨");
		return "secure/product/regist";
	}

	/**
	 * 상품 등록 요청 처리
	 */
	@PostMapping("/product/regist")
	@ResponseBody
	public String regist(Product product, int[] color, int[] size, HttpServletRequest request) {
		// MultipartFile 변수와 html 이름이 동일하면 매핑됨
		MultipartFile[] photo = product.getPhoto();
		logger.debug("업로드 파일 수 " + photo.length);


		// 선택한 상품 색상
		List<ProductColor> productColorList = new ArrayList<>();
		for (int c : color) {
			Color cc = new Color();
			cc.setColor_id(c);

			ProductColor productColor = new ProductColor();
			productColor.setColor(cc);

			productColorList.add(productColor);
		}

		// 선택한 상품 사이즈
		List<ProductSize> productSizeList = new ArrayList<>();
		for (int s : size) {
			Size ss = new Size();
			ss.setSize_id(s);

			ProductSize productSize = new ProductSize();
			productSize.setSize(ss);

			productSizeList.add(productSize);
		}

		// Product에 선택 옵션 대입
		product.setProductColorList(productColorList);
		product.setProductSizeList(productSizeList);

		// 파일 저장 경로 구하기
		String savePath = request.getServletContext().getRealPath("/data");

		// 상품 등록
		productService.regist(product, savePath);

		return "ok";
	}
}
