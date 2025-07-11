package imageCloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class UploadServlet extends HttpServlet{

	String savePath = "C:/lecture_workspace/back_workspace/javaee_workspace/ImageCloud/src/main/webapp/public"; // 바이너리파일이 저장될 서버의 경로
	int maxLimit = 2 * 1024 * 1024;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 전송되어지는 파라미터의 값이 깨지지 않도록 인코딩 지정
		response.setContentType("text/html;charset=utf-8"); // JSP 에서의 Page 지시영역과 동일
		PrintWriter out = response.getWriter();
				
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxLimit, "utf-8");			
			System.out.println("업로드 성공");
			
			// 텍스트 파라미터 추출
			// 파일 업로드 컴포넌트를 이용하면 파라미터 추출도 가능
			String title = request.getParameter("title");
			System.out.println("전송된 제목 "+title);
			out.print(title);
			// 서버에 저장된 이미지를 원하는 이미지로 교체. 클라이언트측에서 파일명을 결정짓고 업로드하면 필요없음
			
			/**
			 * collection framework
			 * 객체를 모아서 처리할 때, 효율적으로 처리할 수 있도록 java.util 패키지에서 지원하는 api
			 * 1) 순서 있는 모음 (단, 배열은 객체만을 다루는 게 아니라 기본 자료형도 제어하므로 collection framework가 아님)
			 * 	   List
			 * 2) 순서 없는 모음
			 *     Set, Map
			 */
			// 순서 없는 컬렉션 객체를 처리할 때 사용하는 도구: Enumeration, Iterator
			Enumeration<String> en = multipartRequest.getFileNames();
			while(en.hasMoreElements()) {
				String param = en.nextElement();
//				out.print("업로드된 파일 " + param);    
				
				// 파라미터명으로 업로드된 파일명 추출
				String filename = multipartRequest.getOriginalFileName(param);
				out.print("업로드된 파일명" + filename);
				System.out.print("업로드된 파일명" + filename);
			}
		} catch (IOException e) {
			System.out.println("업로드 실패");
			e.printStackTrace();
		}
	}
}
