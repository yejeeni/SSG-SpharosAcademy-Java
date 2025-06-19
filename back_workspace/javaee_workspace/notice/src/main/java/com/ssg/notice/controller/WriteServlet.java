package com.ssg.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 클라이언트의 글쓰기 요청을 처리하는 서버측의 서블릿 정의
 */
public class WriteServlet extends HttpServlet {
	/**
	 * 클라이언트의 요청이 post 방식인 경우, doXXX() 메서드 중, doPost()로 받아야 함
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 전송된 데이터 언어 인코딩
		response.setContentType("text/html;charset=utf-8"); // response 인코딩
		// text/html 같은 파일의 종류, 유형을 가리켜 마임타입(MIMEType)이라고 함. ex) application/json, text/xml, ...
		
		String title = request.getParameter("title"); // 제목
	 	String writer = request.getParameter("writer"); // 작성자
		String content = request.getParameter("content"); // 내용
		System.out.println(title + " - " + writer + " - " + content);
		
		Connection con = null;
		PreparedStatement pStatement = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "shop", "1234");
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notice(title, writer, content) values(?, ?, ?)");
			
			PrintWriter out = response.getWriter();
			
			pStatement = con.prepareStatement(sql.toString());
			pStatement.setString(1, title);
			pStatement.setString(2, writer);
			pStatement.setString(3, content);
			
			int result = pStatement.executeUpdate();
			if (result > 0) {
				// 문자열을 적어두면 응답정보를 만들 때 톰캣이 참고
				out.print("<script>");
				out.print("alert('등록 완료');");				
				out.print("location.href='notice/list';");
				out.print("</script>");
			} else {
				out.print("<script>");
				out.print("alert('등록 실패');");				
				out.print("history.back();");
				out.print("</script>");		
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (pStatement != null) {
				try {
					pStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		// 응답 정보 생성
	}
	
}
