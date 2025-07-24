package mall.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import mall.exception.ProductColorException;
import mall.exception.ProductException;
import mall.exception.ProductImgException;
import mall.exception.ProductSizeException;
import mall.exception.UploadException;
import mall.service.ProductService;
import mall.service.ProductServiceImpl;
import mall.service.TopCategoryService;
import mall.util.Paging;

@Slf4j
@Controller
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService; // RootConfig가 연결
	@Autowired
	private TopCategoryService topCategoryService;

	// 페이징 처리 객체
	@Autowired
	private Paging paging;

    ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }
	
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
	public String regist(Product product, int[] color, int[] size, HttpServletRequest request) 
			throws ProductException, ProductColorException, ProductSizeException, ProductImgException, UploadException {
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

		try {
			// 상품 등록
			productService.regist(product, savePath);
		} catch (Exception e) {
			// 등록 실패 잔여물 삭제
			productService.remove(product, savePath);
			e.printStackTrace();
		}

		return "ok";
	}
	
	/**
	 * 목록보기 요청 처리
	 * 요청이 들어오면 list.jsp를 전달
	 */
	@GetMapping("/product/list")
	public String getList(Model model, HttpServletRequest request) { // Controller -> DispatcherServlet -> ViewResolver 전달 
		
		// 3단계: 목록 가져오기
		List<Product> productList = productService.selectAll();
		paging.init(productList, request);
		
		// 4단계: 결과 저장
		model.addAttribute("productList", productList);
		model.addAttribute("paging", paging);
		
		return "secure/product/list";
	}
	
	/**
	 * 상세 페이지 요청 처리
	 */
	@GetMapping("/product/detail")
	public String getDetail(int product_id, Model model) {
		
		// 상세내용 가져오기
		Product product = productService.select(product_id);
		
		// 저장하기
		model.addAttribute(product);
		
		return "secure/product/detail";
	}
}
