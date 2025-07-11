package com.ssg.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ResponseCache;

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

public class StoreEdit extends HttpServlet {

	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String store_id = req.getParameter("store_id");
		String store_name = req.getParameter("store_name");
		String tel = req.getParameter("tel");
		String food_type_id = req.getParameter("food_type_id");

		logger.debug("수정 요청: id={}, name={}, tel={}, type={}", store_id, store_name, tel, food_type_id);

		// 파라미터를 Store에 적재
		Store store = new Store();
		store.setStore_id(Integer.parseInt(store_id));
		store.setStore_name(store_name);
		store.setTel(tel);
		FoodType foodType = new FoodType();
		foodType.setFood_type_id(Integer.parseInt(food_type_id));
		store.setFoodType(foodType);
		
		// 응답정보 생성
		Message message = new Message();
		Gson gson = new Gson();
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		try {
			storeDAO.update(store);
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
			String responseMsg = gson.toJson(message);
			out.print(responseMsg);
		}
	}
}
