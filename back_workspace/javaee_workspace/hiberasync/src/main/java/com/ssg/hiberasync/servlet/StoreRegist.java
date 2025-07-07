package com.ssg.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ssg.hiberasync.exception.StoreException;
import com.ssg.hiberasync.model.FoodType;
import com.ssg.hiberasync.model.Store;
import com.ssg.hiberasync.repository.StoreDAO;
import com.ssg.hiberasync.util.Message;

/**
 * 맛집 등록 요청을 처리하는 서블릿
 */
public class StoreRegist extends HttpServlet{
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 필터가 제대로 동작했다면 한글 처리를 따로 하지 않아도 인코딩 처리가 되어있어야 함
		String food_type_id = request.getParameter("food_type_id");
		String store_name = request.getParameter("store_name");
		String tel = request.getParameter("tel");
		
		logger.debug("food_type_id = " + food_type_id);
		logger.debug("store_name = " + store_name);
		logger.debug("tel = " + tel);
		
		FoodType foodType = new FoodType();
		foodType.setFood_type_id(Integer.parseInt(food_type_id));
		Store store = new Store();
		store.setStore_name(store_name);
		store.setTel(tel);		
		store.setFoodType(foodType);
		
		// 응답 정보를 json으로 생성하여 전송
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		Message message = new Message();
		Gson gson = new Gson(); 
		
		try {
			// 등록
			storeDAO.insert(store);
			// HTTP Status 코드 설정 (평소에 보던 404, 500 등)
			response.setStatus(HttpServletResponse.SC_CREATED); // 201
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
			out.print(gson.toJson(message));
		}
		
	}
	
}
