package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 로그인에 성공한 클라이언트(=인증 성공)만 접근 가능한 서블릿
 */
@WebServlet("/api/protected")
public class ProtectedApiServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인한 유저는 request 객체에 심어진 데이터를 가져감
		String username = (String)request.getAttribute("username");
		
		// json 처리
		response.setContentType("application/json; charset=utf-8");
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "접근 성공");
		map.put("user", username);
		
		response.getWriter().print(new Gson().toJson(map));
		
	}
	
}
