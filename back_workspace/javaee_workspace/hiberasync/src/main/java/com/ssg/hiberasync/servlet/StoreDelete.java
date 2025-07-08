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
import com.ssg.hiberasync.repository.StoreDAO;
import com.ssg.hiberasync.util.Message;

public class StoreDelete extends HttpServlet{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	StoreDAO storeDAO = new StoreDAO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String store_id = request.getParameter("store_id");
//		logger.debug("store_id는 "+store_id);
		
		// 응답 정보 생성
		Message message = new Message();
		Gson gson = new Gson();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		try {
			storeDAO.delete(Integer.parseInt(store_id));
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (NumberFormatException | StoreException e) {
			e.printStackTrace();
			
			message.setMsg(e.getMessage());
			message.setResult("del fail");
			
			out.print(gson.toJson(message));
		}
	}
}
