package mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.domain.TopCategory;
import mall.service.TopCategoryService;

/**
 * 컴포넌트: 특별히 용도가 정해진 빈
 * 컴포넌트 스캔의 대상이 되어 일일이 빈 등록을 할 필요가 없음
 */

@Controller
public class TopCategoryController {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/topcategory/list")
	@ResponseBody // 응답 데이터 형식이 html 문서가 아니라 json 등의 순수 데이터일 경우 사용 (=response.setContentType("application/json"))
	public List<TopCategory> selectAll() {
		return topCategoryService.selectAll(); // 스프링 프레임워크를 사용했으므로 Json 문자열이나 Gson으로 응답 정보를 만들 필요 X
	}
}
