package com.ssg.chatroomapp.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    @GetMapping("/chat/main")
    public String main(HttpSession session){
        // 로그인하지 않고 접근한 경우, 로그인폼으로 이동
        String viewName = "chat/main";
        if (session.getAttribute("member") == null){
            viewName = "member/login";
        }

        return viewName;
    }

    @GetMapping("/chat/joinRoom")
    public String joinRoom(HttpSession session, String UUID){
        return "chat/chatting-room";
    }

}
