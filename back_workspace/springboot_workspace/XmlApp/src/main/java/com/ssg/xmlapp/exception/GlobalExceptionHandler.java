package com.ssg.xmlapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestaurantException.class)
    public ModelAndView handleRestaurantException(RestaurantException e) {
        ModelAndView mv = new ModelAndView("error/result"); // 이동할 뷰 이름
        mv.addObject("errorCode", "RESTAURANT_ERROR");
        mv.addObject("message", e.getMessage());
        return mv;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException e) {
        ModelAndView mv = new ModelAndView("error/result");
        mv.addObject("errorCode", "RUNTIME_ERROR");
        mv.addObject("message", e.getMessage());
        return mv;
    }
}
