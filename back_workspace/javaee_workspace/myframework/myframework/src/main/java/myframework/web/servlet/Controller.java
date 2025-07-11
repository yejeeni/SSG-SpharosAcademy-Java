package myframework.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 하위 컨트롤러의 최상위 객체이다.
public interface Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
	public String getViewName();
	public boolean isForward();
}
