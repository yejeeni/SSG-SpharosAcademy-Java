package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 회원 로그인이 필요한 모든 서블릿이나 JSP마다 JWT 보유 여부 검증 필터
 */
@WebFilter("/api/*")
public class JwtAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		// JWT가 헤더를 통해 전송되므로 JWT 토큰이 위치한 헤더에 접근
		String authHeader = request.getHeader("Authorization");
		
		// 토큰이 없는 경우에는 필터 실행 X
		if (authHeader == null || authHeader.startsWith("Bearer ")) {
			response.setContentType("application/json;charset=utf-8");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			Map<String, String> map = new HashMap<>();
			map.put("error", "로그인 정보가 올바르지 않습니다.");
			response.getWriter().print(new Gson().toJson(map));
		
			return;
		}
		
		// 토큰이 있는 경우 값을 꺼내 검증
		String token = authHeader.substring(7);
		
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}
