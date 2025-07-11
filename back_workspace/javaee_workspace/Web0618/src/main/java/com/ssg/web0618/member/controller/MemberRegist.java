package com.ssg.web0618.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssg.web0618.member.model.Member;
import com.ssg.web0618.member.repository.MemberDAO;

/**
 * 클라이언트의 가입 요청을 처리하는 회원 관련 서블릿(컨트롤러)
 */
public class MemberRegist extends HttpServlet{
	
	MemberDAO memberDAO = new MemberDAO();
		
	/**
	 * POST로 전송했을 때의 요청을 처리하는 메서드
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 정보를 가진 request 객체를 통해 파라미터와 값을 받아서 처리
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		
		System.out.println(id+pwd+name);
		
		
		// db insert
		Member member = new Member();
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		memberDAO.insert(member);
		
		// response 객체에는 클라이언트가 보게 될 응답 정보를 담은 객체
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter(); // 문자 기반 출력 스트림
		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.print("<title>Document</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("축하드립니다.");
		out.print("</body>");
		out.print("</html>");
	}
}
