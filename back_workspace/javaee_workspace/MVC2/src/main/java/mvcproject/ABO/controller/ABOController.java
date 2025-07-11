package mvcproject.ABO.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvcproject.ABO.model.ABOManager;
import mvcproject.web.servlet.Controller;

public class ABOController implements Controller{
	Logger logger = LoggerFactory.getLogger(getClass()); 
	ABOManager aboManager = new ABOManager();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		logger.debug("execute() 동작");
		String abo = request.getParameter("abo");
		String result = aboManager.getAdvice(abo);
		
		// 결과 저장
		// Session에 담으면 데이터를 사용할 수는 있지만, .do로 새롭게 들어오지 않을 경우 갱신되지 않은 과거의 데이터를 보게 되므로, request 객체에 담아줌
		request.setAttribute("msg", result);
		
	}

	/**
	 * 컨트롤러에게 뷰를 전달
	 */
	@Override
	public String getViewName() {
		return "/ABO/view";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}

}
