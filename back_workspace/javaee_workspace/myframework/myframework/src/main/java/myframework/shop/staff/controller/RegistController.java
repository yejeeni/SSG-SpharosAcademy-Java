package myframework.shop.staff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import myframework.staff.model.domain.Bio;
import myframework.staff.model.domain.Staff;
import myframework.staff.model.service.StaffService;
import myframework.web.servlet.Controller;

/*
 * 사원 등록 요청을 처리하는 하위 컨트롤러
 */
public class RegistController implements Controller {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	StaffService service = new StaffService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String sal = request.getParameter("sal");
		String email = request.getParameter("email");

		String blood = request.getParameter("blood");
		int height = Integer.parseInt(request.getParameter("height"));
		int weight = Integer.parseInt(request.getParameter("weight"));

		Staff staff = new Staff();
		staff.setName(name);
		staff.setSal(Integer.parseInt(sal));
		staff.setEmail(email);

		Bio bio = new Bio();
		bio.setBlood(blood);
		bio.setWeight(weight);
		bio.setHeight(height);
		bio.setStaff(staff);

		// service에서 비즈니스 로직 수행
		service.regist(bio);
	}

	@Override
	public String getViewName() {
		return "/shop/staff/regist/view";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
