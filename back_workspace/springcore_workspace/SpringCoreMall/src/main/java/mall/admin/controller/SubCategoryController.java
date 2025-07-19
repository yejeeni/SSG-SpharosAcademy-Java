package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.domain.SubCategory;
import mall.service.SubCategoryService;

@Controller
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	/**
	 * 선택된 상위 카테고리에 소속된 하위 카테고리 목록 가져오는 메서드
	 */
	@GetMapping("/subcategory/list")
	@ResponseBody
	public List<SubCategory> getList(int topcategory_id){ // 클라이언트의 파라미터명과 컨트롤러 메서드의 매개변수명이 일치하면 자동으로 매핑
		return subCategoryService.selectByTopCategoryId(topcategory_id);
	}
	
}
