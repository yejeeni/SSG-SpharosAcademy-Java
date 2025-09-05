package com.ssg.jwttokenlogin.controller;

import com.ssg.jwttokenlogin.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/member/login")
    public ResponseEntity<?> login(String username, String password) {
        log.debug("login() called");

        // 사용자 인증 성공 시 토큰 발급
        // 아이디, 패스워드를 검증하는 시큐리티의 객체는 DaoAuthenticationProvider
        // 이 객체가 검증을 시도하게 하려면 이 객체를 호출하는 AuthenticationManager의 authenticate() 호출
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);

        // AccessToken 발급
        String accessToken = jwtUtil.generateAccessToken(authentication.getName());

        // RefreshToken 발급
        String refreshToken = jwtUtil.generateRefreshToken(authentication.getName());

        return ResponseEntity.ok(Map.of("accessToken", accessToken, "refreshToken", refreshToken));
    }

    @GetMapping("/member/mypage")
    public ResponseEntity<?> mypage() {
        return null;
    }
}
