package mall.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import mall.domain.TopCategory;
import mall.service.TopCategoryService;

/**
 * shop에 존재하는 모든 controller의 공통 기능을 처리하는 전담 클래스
 */
@ControllerAdvice // Controller가 동작하기 전에 먼저 동작
public class ShopGlobalModelAdvice {
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 쇼핑몰의 메인 내비게이션에 출력될 최상위 카테고리는 jsp를 매핑하는 모든 컨트롤러에서 처리해야 하는 공통 코드이므로 controllerAdvice에서 해결
	@ModelAttribute("topCategories") // 모든 하위 컨트롤러에 사용할 모델 데이터 저장
	public List<TopCategory> getTopCategories(){
		return topCategoryService.selectAll();
	}
}
