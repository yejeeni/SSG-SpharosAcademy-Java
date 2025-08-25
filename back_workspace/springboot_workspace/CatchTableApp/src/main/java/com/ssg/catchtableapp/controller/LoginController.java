package com.ssg.catchtableapp.controller;

import com.ssg.catchtableapp.dto.LoginForm;
import com.ssg.catchtableapp.dto.MemberForm;
import com.ssg.catchtableapp.model.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    /**
     * 로그인 폼
     * @param model
     * @return
     */
    @GetMapping("/members/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "members/login";
    }

    /**
     * 로그인 처리
     * @param form
     * @param result
     * @param model
     * @return
     */
    @PostMapping("/members/login")
    public String login(@Valid LoginForm form, BindingResult result, Model model) {
        log.debug("login post");

        if (result.hasErrors()) {
            return "members/login";
        }

        try {
            memberService.login(form.getId(), form.getPassword());
            return "redirect:/"; // 홈으로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "members/login";
        }
    }

    /**
     * 회원가입 폼
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/sign-up";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result, Model model) {

        // 에러가 있으면 가입창에 돌려보내기
        if (result.hasErrors()) {
            return "members/sign-up";
        }

        try {
            int memberId = memberService.join(form.toEntity());
            log.info("회원가입 성공: memberId={}", memberId);

            model.addAttribute("id", form.getId()); // 사용자가 가입한 id
            return "members/sign-up-success";
        } catch (IllegalStateException e) {
            result.reject("duplicateError", e.getMessage());
            return "members/sign-up";
        }
    }
}
