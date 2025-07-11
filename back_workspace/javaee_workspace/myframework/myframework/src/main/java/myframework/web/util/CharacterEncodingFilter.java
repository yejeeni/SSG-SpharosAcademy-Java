package myframework.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	String encoding;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		encoding = arg0.getInitParameter("encoding");
		
	}
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		

		arg0.setCharacterEncoding(encoding);
		arg1.setCharacterEncoding(encoding);
		//이 메서드를 호출해야, 요청의 흐름이 끊기지 않는다. 
		arg2.doFilter(arg0, arg1);
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
