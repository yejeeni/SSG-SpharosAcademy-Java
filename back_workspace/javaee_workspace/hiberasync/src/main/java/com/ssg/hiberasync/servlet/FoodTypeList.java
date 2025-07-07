package com.ssg.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ssg.hiberasync.model.FoodType;
import com.ssg.hiberasync.repository.FoodTypeDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 음식 유형 목록 요청을 받는 서블릿
 */
public class FoodTypeList extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(FoodTypeList.class);
	FoodTypeDAO foodTypeDAO = new FoodTypeDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FoodType> list = foodTypeDAO.selectAll();

		Gson gson = new Gson();
		String result = gson.toJson(list);

		logger.debug("문자열로 변환 후의 데이터: " + result);

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}
}
