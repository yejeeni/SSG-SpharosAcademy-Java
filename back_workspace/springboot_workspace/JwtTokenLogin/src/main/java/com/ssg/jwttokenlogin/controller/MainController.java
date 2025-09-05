package com.ssg.jwttokenlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 컨트롤러
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
