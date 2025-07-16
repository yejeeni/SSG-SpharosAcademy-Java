package springMvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 빈: 스프링에서 관리하는 클래스
 * 컴포넌트: 스프링 빈으로 관리되는 구성요소. 빈으로 등록된 클래스. 즉, 빈의 대상
 */
@Controller
public class NoticeController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @return ModelAndView Model에는 저장할 데이터 (request.setAttribute(키, 값)), View에는 DispatcherServlet이 조합할 뷰의 이름
	 */
	@RequestMapping("/notice/list") // 여기 해당하는 컨트롤러 메서드를 찾아서 실행
	public ModelAndView selectAll() {
		/* 컨트롤러 실행 */
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", "게시물 목록"); // 전달할 데이터. (=request.setAttribute("list", "게시물 목록"))
		modelAndView.setViewName("notice/list"); // View 이름 설정
		return modelAndView; // DispatcherServlet에 반환 -> modelAndView에서 뷰 이름 추출 -> ViewResolver에게 실제 파일 경로 요청
	}
	
	/**
	 * 글쓰기 폼 요청 처리 메서드
	 */
	@RequestMapping("/notice/registform")
	public String registForm() {
		return "notice/write";
	}
	
	/**
	 * 저장할 데이터, 반환값이 없을 경우 ModelAndView 중 View만 반환하면 되므로, String을 사용 가능
	 * @return 
	 */
	@RequestMapping(value="/notice/regist", method=RequestMethod.POST)
	public String regist() {
		logger.debug("글쓰기 요청 받음");
		return "redirect:/shop/notice/list";
	}
	
	@RequestMapping("/notice/detail")
	public ModelAndView getDetail() {
		logger.debug("상세보기 요청 받음");
		return null;
	}
	
	@RequestMapping(value="/notice/update", method = RequestMethod.POST)
	public String update() {
		logger.debug("수정 요청 받음");
		int id = 3;
		return "redirect:/shop/notice/detail?notice_id="+id;
	}
	
	@RequestMapping(value="/notice/delete", method = RequestMethod.GET)
	public String delete() {
		logger.debug("삭제 요청 받음");
		return "redirect:/shop/notice/list";
	}
	
	
//	// SpringBoot
//	@Controller
//	public class NoticeController {
//	    @RequestMapping("/notice/list")
//	    public String list() {
//	        return "notice/list";
//	    }
//	}

}
