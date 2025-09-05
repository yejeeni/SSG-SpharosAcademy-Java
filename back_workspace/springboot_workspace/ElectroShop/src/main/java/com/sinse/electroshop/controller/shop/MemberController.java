package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.controller.dto.MemberDTO;
import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import com.sinse.electroshop.model.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/shop/loginform")
    public String loginForm() {
        return "electro/login";
    }

    //로그인 요청 처리
    @PostMapping("/member/login")
    public ResponseEntity<MemberDTO> login(MemberDTO memberDTO, HttpSession session) {
        //DTO의 데이터를 Model 로 옮김
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        log.debug(member.toString());

        //로그인 검증
        Member result=memberService.authenticate(member);
        session.setAttribute("member", result);

        //DTO정보를 클라이언트에게 보내주자(id, name)
        memberDTO.setPwd(null);
        memberDTO.setName(result.getName());

        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }


    //@ExceptionHandler 를 이용하면 이 컨트롤러 내에서 발생하는 모든 예외를 처리할 수 있다.
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
