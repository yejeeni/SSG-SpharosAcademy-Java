package mall.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mall.notice.domain.Notice;
import mall.notice.service.NoticeService;


@Slf4j
@Controller
public class NoticeController {
	
	private final NoticeService noticeService;
	
	@Autowired // 스프링 컨테이너로부터 의존성 주입
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 * 목록 요청 처리
	 * @return
	 */
	@RequestMapping("/notice/list")
	public ModelAndView selectAll() {
		log.debug("목록 요청 받음");

		/**
		 * Model: JSP에서 사용할 데이터를 담음 (request.setAttribute()와 유사)
		 * View: 보여줄 JSP 파일의 이름을 설정
		 */
		
		// 서비스
		List<Notice> notices = noticeService.selectAll();
		
		// 결과 저장
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("notices", notices);
		modelAndView.setViewName("admin/notice/list");
		
		return modelAndView;
	}
	
//	@GetMapping("/notice/list")
	public String selectAll(Model model) {
	    List<Notice> notices = noticeService.selectAll();
	    model.addAttribute("notices", notices);
	    return "admin/notice/list";
	}

	// 상세보기 요청 처리
	@RequestMapping(value="/notice/registform", method=RequestMethod.GET)
	public String getRegistForm() {
		return "admin/notice/write";
	}
	
	// 글 등록 요청 처리
	@RequestMapping(value="/notice/regist", method=RequestMethod.POST)
	public ModelAndView regist(Notice notice) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			noticeService.regist(notice);
			modelAndView.setViewName("redirect:/admin/notice/list");
		} catch (Exception e) {
			log.error("등록 실패", e);
			modelAndView.addObject("e", e);
			modelAndView.setViewName("admin/error/result");
		}
		
		return modelAndView;
	}
	
	
	// 글 수정 요청 처리
	// 글 삭제 요청 처리
}
