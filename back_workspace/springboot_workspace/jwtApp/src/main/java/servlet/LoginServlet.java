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

import util.MyJwtUtil;

// web.xml을 사용하지 않고, 어노테이션 기반으로 서블릿 매핑
@WebServlet("/member/login")      
public class LoginServlet extends HttpServlet{
   String id = "batman";
   String pwd = "1234";
   
   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //파라미터 받기
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      
      System.out.println("username is "+username+", password is "+password);
      
      //DB 연동하는 대신, 조건문으로 인증을 처리하자
      if(id.equals(username) && pwd.equals(password)) {
         //인증 증표를 생성하여 클라이언트를 응답 정보로 보내기
         String accessToken = MyJwtUtil.generateAccessToken(username, 15*60*1000); // 15분 유효 액세스 토큰
         
         // 클라이언트에 전송하여 토큰 발급
         
         // 응답 본문의 데이터 형식을 지정하여 브라우저가 json으로 해석하도록
         response.setContentType("application/json;charset=utf-8");
         
//         response.getWriter().print("{");
//         response.getWriter().print("accessTocken:"+accessToken);
//         response.getWriter().print("}");
         
         // Gson으로 처리
         Map<String, String> tokens = new HashMap<>();
         tokens.put("accessToken", accessToken);
         
         Gson gson = new Gson();
         String json = gson.toJson(tokens);
         
         response.getWriter().print(json);
         
      }
   }
}
