package fileServer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{

   //클라이언트의 요청이 Post방식이므로, doxx형 중 doPost()를 재정의 해야 함 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      //response.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      
      PrintWriter out = response.getWriter();
      out.print("post 요청 받음");
      
      //직접 짜려면 소켓, 스트림을 이용해서 파일을 제어할 줄 알아야 함
      //대신 파일 업로드 컴포넌트 중 cos.jar를 쓸 거임
      //request 는 위에 request(사진을 보낸다는 요청을 함) 파일 경로는 업로드할 파일 경로
      int maxLimit = 1*1024*1024; // 1mb
      MultipartRequest multi = new MultipartRequest(
    		  request,
    		  "C:\\lecture_workspace\\back_workspace\\javaee_workspace\\fileServer\\src\\main\\data", 
    		  maxLimit,
    		  "utf-8"
    		  );
      
   }
}
