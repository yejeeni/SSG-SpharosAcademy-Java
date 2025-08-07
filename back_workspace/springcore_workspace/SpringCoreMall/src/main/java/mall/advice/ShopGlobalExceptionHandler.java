package mall.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import mall.exception.CartException;

/**
 * 각 컨트롤러에 명시된 예외처리 어노테이션 @ExceptionHandler을 공통으로 처리하고 싶을 경우, @ControllerAdvice를 이용
 * 
 * @RestController: 모든 메서드에 @ResponseBody를 붙인 효과
 */

@RestControllerAdvice // 응답 정보가 responseBody일 경우.
public class ShopGlobalExceptionHandler {
	/**
	 * 장바구니 등록 관련 실패 처리
	 */
	@ExceptionHandler(CartException.class)
	public ResponseEntity<Map<String, Object>> handle(CartException e){
		
		Map<String, Object> result = new HashMap<>();
		result.put("msg", e.getMessage());
		
		return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
