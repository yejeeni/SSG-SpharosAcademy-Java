package com.ssg.hiberasync.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 요청이 들어오면 서블릿보다 앞선 단계에서 업무를 처리할 수 있도록 지원되는 인터페이스.
 * 주 용도: 웹애플리케이션 전반적으로 공통된 설정, 사용자 로그, 로그인 처리 등
 */
public class CharacterEncodingFilter implements Filter {

	String encoding;
	
	/**
	 * 필터 인스턴스가 생성되자마자 호출되는 필터 초기화 메서드
	 * web.xml에 초기값을 전달하는 데에 사용 가능
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		// 이 요청의 흐름이 그대로 흘러갈 수 있게 처리해야 함
		chain.doFilter(request, response); // 다음 필터나 서블릿으로 요청을 전달
		
	}

	@Override
	public void destroy() {
	}
}
