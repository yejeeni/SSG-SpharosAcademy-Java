package mall.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 체크 필터
 */
@Slf4j
public class LoginCheckFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		String uri = request.getRequestURI();
		log.debug("필터 동작 - 요청 URI: {}", uri);

		// 로그인 없이 접근 허용할 URI들
		if (
				uri.equals("/shop") || 
				uri.equals("/shop/member/loginform") || // 로그인 페이지
				uri.equals("/shop/member/login") || // 로그인 요청 처리 (POST일 경우도 대비)
				uri.equals("/shop/member/logout") || 
				
				uri.equals("/shop/member/registform") || // 회원가입 폼
				uri.equals("/shop/member/regist") || // 회원가입 요청 처리
				
				uri.equals("/shop/member/naver/authurl") ||
				uri.equals("/shop/member/google/authurl") ||
				uri.equals("/shop/member/kakao/authurl") ||
				
				uri.equals("/shop/callback/sns/naver") ||
				uri.equals("/shop/callback/sns/kakao") ||
				uri.equals("/shop/callback/sns/google") ||
				
				uri.equals("/shop/product/list") ||
				uri.equals("/shop/product/detail") ||
				
				uri.startsWith("/static/**") || 
				
				uri.equals("/favicon.ico")) {
			chain.doFilter(req, res);
			return;
		}

		// 로그인 여부 확인
		boolean isLogin = (session != null) && session.getAttribute("member") != null;

		if (!isLogin) {
			log.debug("비로그인 사용자 요청 차단: {}", uri);
			response.sendRedirect("/shop/member/loginform");
			return;
		}

		chain.doFilter(req, res); // 로그인 한 경우 계속 진행
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
