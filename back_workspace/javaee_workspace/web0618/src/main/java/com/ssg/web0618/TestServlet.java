package com.ssg.web0618;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 학습
 * 자바의 클래스 중 javaEE 기반의 서버에서만 해석 및 실행
 * 서블릿은 인스턴스를 생성할 필요가 없고, 클래스 작성 후 서버에 올려두기만 하면, 최초 요청에 의해 톰캣이 인스턴스를 한 번 올린다
 */

/**
 * 서블릿의 생명주기 관련 메서드
 * 1) init(): 서블릿이 태어날 때 서버에 의해 초기화를 위해 호출되는 메서드
 * 2) service(): 서블릿이 클라이언트의 모든 요청을 처리할 때 쓰레드에 의해 호출되는 메서드
 * 3) destroy(): 서블릿이 소멸될 때 서버에 의해 호출되는 메서드
 */

public class TestServlet extends HttpServlet{
	public void init() throws ServletException{
		System.out.println("init()");
	}
	
	/**
	 * service(): 요청 처리 메서드. 요청 정보와 응답 정보를 가지고 있다.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() called. 요청 처리");
	}

	/**
	 * destroy(): 서블릿이 소멸하는 단계에서 호출되는 메서드.
	 */
	@Override
	public void destroy() {
		System.out.println("destroy() called. 서블릿 소멸");
	}
	
	
	
	
}
