package mall.admin.controller;

import java.io.File;
import java.io.IOException;
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

import mall.domain.Product;
import mall.domain.TopCategory;
import mall.service.TopCategoryService;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
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
    public String regist(Product product, HttpServletRequest request) {
    	// MultipartFile 변수와 html 이름이 동일하면 매핑됨
    	
    	// 메모리에 올라온 이미지 정보를 디스크에 저장
    	// 파일의 경로를 고정하는 것이 아니라, 애플리케이션으로부터 경로를 동적으로 받아오기
    	ServletContext context = request.getServletContext(); // jsp 어플리케이션 내장 객체. 애플리케이션과 생명을 같이함
    	String realPath = context.getRealPath("/data");
    	logger.debug(realPath);

    	
    	
    	return "redirect:/admin/product/list";
    }
}
