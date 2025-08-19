package com.ssg.chatroomapp.controller;

import com.ssg.chatroomapp.domain.Member;
import com.ssg.chatroomapp.dto.MemberLoginDTO;
import com.ssg.chatroomapp.model.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(MemberLoginDTO loginDTO, HttpSession session){
        Member member = memberService.login(loginDTO);
        session.setAttribute("member", member);
        return "redirect:../chat/main";
    }

    @GetMapping("/member/register")
    public String registerForm(){
        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(Member member, Model model){
        // 테스트용 예외
//        if (true) {
//            throw new MemberException("테스트용 회원가입 실패 예외");
//        }

        memberService.insert(member);
        model.addAttribute("id", member.getId());
        return "member/register-success";
    }
}
