package myframework.web.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import myframework.web.servlet.Controller;

public class SimpleUrlHandlerMapping implements HandlerMapping{
	Logger logger = LoggerFactory.getLogger(getClass());
	JsonObject root; //DispatcherServlet이 보유하고 있는 Json 매핑 파일을 해석한 결과 객체
	
	//하위 컨트롤러들을 key와 value의 쌍으로 보관해놓자
	//그래야 요청이 들어올 때, 해당 요청에 동작할 하위 컨트롤러를 DispatcherSerlvet에 반환할 것이기 때문
	HashMap<String,Controller> controllerMap = new HashMap<>();
	
	@Override
	public void setRoot(JsonObject root) {
		this.root = root;
		logger.debug("Dispatcher서블릿으로 부터 넘겨받은 root = "+ root);
		
		
		//반복문으로 객체의 모든 키 값에 매핑된 클래스명을 대상으로 인스턴스화 작업을 시도하고,
		//명단을 ControllerMap에 수집해야한다.
	}

	@Override
	public void initialize() {	
		JsonObject controllerMappings = root.getAsJsonObject("controllerMappings");
		logger.debug("controllerMappings의 결과" + controllerMappings);
		
		Set set = controllerMappings.keySet();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			String uri = it.next(); // 예: "/notice/list.do"
			String controllerName = controllerMappings.get(uri).getAsString(); // 예: "newmvcproject.notice.controller.NoticeListController"
			logger.debug("URI: " + uri + ", 컨트롤러 명: " + controllerName);
			
			Controller controller;
			try {
				controller = (Controller)Class.forName(controllerName).newInstance();
				controllerMap.put(uri, controller);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Controller getController(String uri) {
		return controllerMap.get(uri);
	}
	
}
