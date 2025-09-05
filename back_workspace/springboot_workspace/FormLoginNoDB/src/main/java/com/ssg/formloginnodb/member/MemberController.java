package com.ssg.formloginnodb.member;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    // 스프링 시큐리티가 기본적으로 제공하는 플러그인 기능에서는 로그인 성공 시 '/'로 redirect
    /**
     * 세션에서 사용자 정보를 직접 추출
     * @return
     */
    @GetMapping("/")
    public String UserInfoGetSession(HttpSession session, Model model){
        SecurityContext springSecurityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication authentication = springSecurityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        model.addAttribute("id", "session "+username);

        return "member/index";
    }

    /**
     * Authentication에서 사용자 정보 꺼내기
     * @param authentication
     * @param model
     * @return
     */
    @GetMapping("/idOfAuth")
    public String UserInfoGetAuthentication(Authentication authentication, Model model){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("id", "auth "+userDetails.getUsername());
        
        return "member/index";
    }

    /**
     * SecurityContextHolder에서 꺼내기
     * @param model
     * @return
     */
    @GetMapping("/idOfSecurityContextHolder")
    public String UserInfoGetSecurityContext(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("id", "security context holder " + userDetails.getUsername());

        return "member/index";
    }

    /**
     * @AuthenticationPricipal에서 꺼내기
     */
    @GetMapping("/idOfAuthAnnotation")
    public String UserInfoGetAutenAnnotation(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("id", "@AuthenticationPrincipal " + userDetails.getUsername());
        return "member/index";
    }
}
