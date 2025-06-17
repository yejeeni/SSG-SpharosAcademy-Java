package mall.product;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;



/* servlet. 웹 애플리케이션 서버에서만 해석 및 실행될 수 있는 javaEE 기반 클래스로 정의*/
public class ProductController extends HttpServlet{
	
	String url = "jdbc:mysql://localhost:3306/shop";
	String user = "shop";
	String pwd = "1234";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// response 객체로부터 스트림을 얻기 전에 한글 인코딩을 지정해야 함
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// 클라이언트의 웹 브라우저에 출력될 문자열을 스트림으로 준비
		PrintWriter out = response.getWriter(); // 응답 객체로부터 스트림을 얻어오기
		out.print("my mall application<br>");
		// Product 테이블 연동하기

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			out.print("드라이버 로드 성공<br>");
			connection = DriverManager.getConnection(url, user, pwd);
			if (connection != null){
				out.print("데이터베이스 접속 성공<br>");
				
				StringBuffer sql = new StringBuffer();
				sql.append("select * from product");
				pstmt = connection.prepareStatement(sql.toString());
				rs = pstmt.executeQuery(); // select 수행 결과를 표로 반환
				
				List<Product> list = new ArrayList<>();
				
				while(rs.next()){
					Product product = new Product();
					product.setProduct_id(rs.getInt("product_id"));
					product.setProduct_name(rs.getString("product_name"));
					product.setPrice(rs.getInt("price"));
					product.setBrand(rs.getString("brand"));
					product.setDiscount(rs.getInt("discount"));
					product.setIntroduce(rs.getString("introduce"));
					product.setDetail(rs.getString("detail"));
					
					list.add(product);
				}
				
				// List에 들어있는 객체들을 html 테이블로 출력
				StringBuffer tag = new StringBuffer();
				tag.append("<table border='1px' width='800'>");
				tag.append("<tr>");
				tag.append("<td>ID</td>");
				tag.append("<td>상품명</td>");
				tag.append("<td>브랜드</td>");
				tag.append("<td>가격</td>");
				tag.append("<td>할인율</td>");
				tag.append("<td>소개</td>");
				tag.append("<td>상세설명</td>");
				tag.append("</tr>");
				
				for(Product product : list){
					tag.append("<tr>");
					tag.append("<td>").append(product.getProduct_id()).append("</td>");
					tag.append("<td>").append(product.getProduct_name()).append("</td>");
					tag.append("<td>").append(product.getBrand()).append("</td>");
					tag.append("<td>").append(product.getPrice()).append("</td>");
					tag.append("<td>").append(product.getDiscount()).append("</td>");
					tag.append("<td>").append(product.getIntroduce()).append("</td>");
					tag.append("<td>").append(product.getDetail()).append("</td>");
					tag.append("</tr>");
				
				}
				
				out.print(tag);
			} else{
				out.print("데이터베이스 접속 실패<br>");	
			}
		} catch (ClassNotFoundException e) {
			out.print("드라이버 로드 실패<br>");
			e.printStackTrace();
		} catch(SQLException e){
			out.print("데이터베이스 접속 에러");
		} finally{
			if(rs != null)try{rs.close();}catch(SQLException e){}	
			if(pstmt != null)try{rs.close();}catch(SQLException e){}	
			if(connection != null)try{rs.close();}catch(SQLException e){}	
		}
	}
}