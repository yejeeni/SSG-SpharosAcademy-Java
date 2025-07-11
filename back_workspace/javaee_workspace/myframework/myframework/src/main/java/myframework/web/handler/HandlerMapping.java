package myframework.web.handler;

import com.google.gson.JsonObject;

import myframework.web.servlet.Controller;

//모든 랜들러 매핑 객체들의 최상위 객체를 정의하며, 구현을 방지하기 위해서.
public interface HandlerMapping {
	public void setRoot(JsonObject root); //DispatcherServlet이 보유한 Root JsonObject가 있어야 json 설정파일을 해석가능하므로..
	
	//각 핸들러 매핑마다 하고 싶은 초기화 작업에 사용할 메서드
	public void initialize();


	public Controller getController(String uri);
}
