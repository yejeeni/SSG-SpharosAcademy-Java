package mall.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminMainController")
public class MainController {

	/**
	 * 관리자 모드 메인 요청
	 * @return
	 */
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public ModelAndView getMain() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("secure/index");
		return modelAndView;
	}
}
