package com.ssg.jwttokenlogin.filter;

import com.ssg.jwttokenlogin.domain.CustomUserDetails;
import com.ssg.jwttokenlogin.model.member.CustomUserDetailsService;
import com.ssg.jwttokenlogin.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Spring Web에서 제공하는 OncePerRequestFilter을 상속하여, Spring Security의 필터 체인에서 동작하는 JWT 인증 필터
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

//    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("OncePerRequestFilter 구현 검증 필터 동작");

        // 클라이언트가 Authorization의 Bearer에 함께 보낸 토큰 검증
        String authorization = request.getHeader("Authorization");
        
        // 헤더에 토큰값이 있고, 세션이 비어있는 경우(세션을 STATELESS로 설정해두었으므로)
        if (authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7); // 헤더의 인덱스 7번째부터 토큰이 시작

            // 토큰 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자명 추출
                String username = jwtUtil.getUsernameFromToken(token);
                log.debug("token 추출 username - {}", username);

//                // DB에서 사용자 정보 조회
//                CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
//                // 인증 객체 생성 (사용자 정보, NULL, DB에서 가져온 권한)
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                // 간단한 기본 권한으로 인증 객체 생성 (지금은 권한이 없음. 아직 학습 전)
                Collection<GrantedAuthority> authorities = Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_USER")
                );

                // DB 조회 없이 토큰 정보만으로 인증 객체 생성
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(Map.of("error", "토큰이 유효하지 않습니다."));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
