package com.ssg.chatroomapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역 예외 처리
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * MemberException 처리
     */
    @ExceptionHandler(MemberException.class)
    public String handlerMemberException(MemberException e, Model model, HttpServletRequest request){
        log.error("회원 관련 오류: ", e);
        model.addAttribute("error", e.getMessage());

        // 요청 uri에 따른 분기
        String requestURI = request.getRequestURI(); // 요청한 URI 꺼내기
        if (requestURI.contains("/login")){
            return "member/login";
        } else if(requestURI.contains("/register")){
            return "member/register";
        }
        
        return "error/500"; // 위에 포함되지 않는 기본 에러 페이지
    }
}
