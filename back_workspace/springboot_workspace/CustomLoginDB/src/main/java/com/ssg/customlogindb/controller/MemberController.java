package com.ssg.customlogindb.controller;

import com.ssg.customlogindb.util.PasswordCreater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final PasswordCreater passwordCreater;

    @GetMapping("/")
    public String index(){
        String cryptPassword = passwordCreater.getCryptPassword("apple");
        log.debug("cryptPassword: {}", cryptPassword);
        return "member/index";
    }

    @GetMapping("/loginform")
    public String loginForm(){
        return "member/login";
    }

    @GetMapping("/main")
    public String getIndex(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("userDetails", userDetails);
        return "member/index";
    }
}
