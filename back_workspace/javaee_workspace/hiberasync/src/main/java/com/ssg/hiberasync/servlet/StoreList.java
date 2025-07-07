package com.ssg.hiberasync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ssg.hiberasync.exception.StoreException;
import com.ssg.hiberasync.repository.StoreDAO;
import com.ssg.hiberasync.util.Message;

public class StoreList extends HttpServlet{
	StoreDAO storeDAO=new StoreDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		Message message = new Message();
		
		try {
			List list=storeDAO.selectAll();
			out.print(gson.toJson(list));// 클라이언트가 받을 json 문자열 스트림에 넣기 
		} catch (StoreException e) {
			e.printStackTrace();
			message.setResult("fail");
			message.setMsg(e.getMessage());
			out.print(gson.toJson(message));
		}
	}
}
